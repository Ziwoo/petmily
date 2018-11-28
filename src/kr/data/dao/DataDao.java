package kr.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.data.domain.Data;
import kr.util.StringUtil;

public class DataDao {
	//�̱��� ����
	private static DataDao instance = new DataDao();
	
	public static DataDao getInstantce(){
		return instance;
	}
	private DataDao(){}
	
	//context.xml���� ���������� �о�鿩 Ŀ�ؼ�Ǯ�κ���
	//Ŀ�ؼ��� �Ҵ����
	private Connection getConnection()throws Exception{
		Context initCtx = new InitialContext();
		DataSource ds = 
				(DataSource)initCtx.lookup(
						"java:comp/env/jdbc/xe");
		return ds.getConnection();
	}
	
	//�ڿ�����
	private void executeClose(ResultSet rs, PreparedStatement pstmt, Connection conn){
		if(rs!=null)try{rs.close();}catch(SQLException e){}
		if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
		if(conn!=null)try{conn.close();}catch(SQLException e){}
	}
	
	//�۵��
	public void insertData(Data data) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		int cnt = 0;
		
		try {
			conn = getConnection();
			sql = "insert into p_data (pd_num,mem_id,pd_passwd,pd_content,"
				+ "pd_title,pd_regdate,pd_pic) "
				+ "values "
				+ "(pd_data_seq.nextval,?,?,?,?,sysdate,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, data.getMem_id());
			pstmt.setString(++cnt, data.getPd_passwd());
			pstmt.setString(++cnt, data.getPd_content());
			pstmt.setString(++cnt, data.getPd_title());
			pstmt.setString(++cnt, data.getPd_pic());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			executeClose(null,pstmt,conn);
		}
	}
	
	//��ü�� ����, �˻��� ����
		public int getDataCount(String keyfield, String keyword) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "";
			int count = 0;
			
			try{
				conn = getConnection();
				if(keyword == null || "".equals(keyword)){
					//��ü�� ����
					sql = "select count(*) from p_data";
					pstmt = conn.prepareStatement(sql);
				}else{
					sql = "select count(*) from p_data where " + keyfield + " like ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%"+keyword+"%");
				}
				
				rs = pstmt.executeQuery();
				if(rs.next()){
					count = rs.getInt(1);
				}
			}catch(Exception e){
				throw new Exception(e);
			}finally{
				executeClose(rs, pstmt, conn);
			}
			return count;
		}
	//���, �˻��� ���
	public List<Data> getListData(int start, int end, String keyfield, String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Data> list = null;
		String sql = "";
		int cnt = 0;
		
		try {
			conn = getConnection();
			if(keyword == null || "".equals(keyword)){
				//��ü�� ����
				sql = "select * from (select a.*, rownum rnum from "
					+ "(select * from p_data order by pd_num desc)a)"
					+ " where rnum>=? and rnum<=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);	
			}else{
				//�˻��� ����
				sql = "select * from (select a.*, rownum rnum from "
					+ "(select * from p_data where " + keyfield + " like ? "
					+ "order by pd_num desc)a)"
					+ " where rnum>=? and rnum<=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(++cnt, "%"+keyword+"%");
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
			}
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<Data>();
			while(rs.next()){
				Data data = new Data();
				data.setPd_num(rs.getInt("pd_num"));
				data.setPd_title(StringUtil.useNoHtml(rs.getString("pd_title")));
				data.setPd_pic(rs.getString("pd_pic"));
				data.setPd_regdate(rs.getDate("pd_regdate"));
				data.setMem_id(rs.getString("mem_id"));
				data.setPd_content(rs.getString("pd_content"));
				//board.setReply_cnt(rs.getInt("re_cnt"));
				
				list.add(data);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//�� ��
	public Data getData(int pd_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Data data = null;
		String sql = "";
		
		try {
			conn = getConnection();
			
			sql = "select * from p_data where pd_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pd_num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				data = new Data();
				data.setMem_id(rs.getString("mem_id"));
				data.setPd_num(rs.getInt("pd_num"));
				data.setPd_title(rs.getString("pd_title"));
				data.setPd_content(rs.getString("pd_content"));
				data.setPd_regdate(rs.getDate("pd_regdate"));
				data.setPd_pic(rs.getString("pd_pic"));
				data.setPd_passwd(rs.getString("pd_passwd")); 
			}
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			executeClose(rs, pstmt, conn);
		}
		
		return data;
	}
	
	//�� ��ȸ�� ����
	
	//�� ����
	public void updateData(Data data) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		int cnt = 0;
		
		try{
			conn = getConnection();
			
			sql = "update p_data set pd_title=?, pd_content=?, "
				+ "pd_passwd=?, pd_pic=? where pd_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, data.getPd_title());
			pstmt.setString(++cnt, data.getPd_content());
			pstmt.setString(++cnt, data.getPd_passwd());
			pstmt.setString(++cnt, data.getPd_pic());
			pstmt.setInt(++cnt, data.getPd_num());
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			executeClose(null, pstmt, conn);
		}
	}
	//�� ����
	
		public void deleteData(int pd_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "";
			
			try{
				conn = getConnection();
				
				sql = "delete from p_data where pd_num=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, pd_num);
				
				pstmt.executeUpdate();
				
			}catch(Exception e){
				throw new Exception(e);
			}finally{
				executeClose(null, pstmt, conn);
			}
		}
	}
	
	//��� ���
	
	
	//��� ����
	
	//��� ���
	
	//��� ����
	
	//��� ����

	
	

