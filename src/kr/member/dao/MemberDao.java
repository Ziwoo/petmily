package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.member.domain.Member;

public class MemberDao {
	/*
	 * �̱��� ������ �����ڸ� private���� �����ؼ� �ܺο���
	 * ȣ���� �� ������ ó���ϰ� static �޼��带 ȣ���ؼ�
	 * ��ü�� �� ���� �����ǰ� ������ ��ü�� ������ �� �ֵ���
	 * ó���ϴ� ����� �ǹ���
	 */
	private static MemberDao instance = new MemberDao();
	
	public static MemberDao getInstance(){
		return instance;
	}
	
	private MemberDao(){}
	
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
	private void executeClose(ResultSet rs, 
			PreparedStatement pstmt, Connection conn){
		if(rs!=null)try{rs.close();}catch(SQLException e){}
		if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
		if(conn!=null)try{conn.close();}catch(SQLException e){}
	}
	//ȸ������
	public void insertMember(Member p_member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		int cnt = 0;
		
		try{
			conn = getConnection();
			//SQL�� : id,name,passwd,cell,email,zipcode,
			//        address1,address2,reg_date
			sql = "insert into p_member (mem_id,mem_name,mem_passwd,"
				+ "mem_cell,mem_email,mem_zipcode1,mem_zipcode2,mem_addr1,mem_addr2,"
				+ "mem_register,mem_level) values (?,?,?,?,?,?,?,?,?,"
				+ "sysdate,?)";
			//PreparedStatement ��ü ����
			pstmt = conn.prepareStatement(sql);
			//SQL���� ?�� ������ ���ε�
			pstmt.setString(++cnt, p_member.getMem_id());
			pstmt.setString(++cnt, p_member.getMem_name());
			pstmt.setString(++cnt, p_member.getMem_passwd());
			pstmt.setString(++cnt, p_member.getMem_cell());
			pstmt.setString(++cnt, p_member.getMem_email());
			pstmt.setString(++cnt, p_member.getMem_zipcode1());
			pstmt.setString(++cnt, p_member.getMem_zipcode2());
			pstmt.setString(++cnt, p_member.getMem_addr1());
			pstmt.setString(++cnt, p_member.getMem_addr2());
			pstmt.setString(++cnt, p_member.getMem_level());
			
			//SQL�� ����
			pstmt.executeUpdate();
			
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			executeClose(null, pstmt, conn);
		}
	}
	
	//ȸ��������
	public Member getMember(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member p_member = null;
		String sql = "";
		
		try{
			conn = getConnection();
			//SQL�� 
			sql = "select * from p_member where mem_id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				p_member = new Member();
				p_member.setMem_id(rs.getString("mem_id"));
				p_member.setMem_passwd(rs.getString("mem_passwd"));
				p_member.setMem_name(rs.getString("mem_name"));
				p_member.setMem_cell(rs.getString("mem_cell"));
				p_member.setMem_email(rs.getString("mem_email"));
				p_member.setMem_zipcode1(rs.getString("mem_zipcode1"));
				p_member.setMem_zipcode2(rs.getString("mem_zipcode2"));
				p_member.setMem_addr1(rs.getString("mem_addr1"));
				p_member.setMem_addr2(rs.getString("mem_addr2"));
				p_member.setMem_register(rs.getDate("mem_register"));
			}
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			executeClose(rs, pstmt, conn);
		}
		return p_member;
	}
	
	//ȸ��IDã��
	public String getMemberId(String user_email,String user_name)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member p_member = null;
		String sql = "";
		String user_id=null;
		
		try{
			conn = getConnection();
			//SQL�� 
			sql = "select * from p_member where mem_email=? and mem_name=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_email);
			pstmt.setString(2, user_name);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				p_member = new Member();
				p_member.setMem_id(rs.getString("mem_id"));
				user_id = p_member.getMem_id();
			}
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			executeClose(rs, pstmt, conn);
		}
		return user_id;
	}
	
	//ȸ���н�����ã��
	public String getMemberPassword(String user_email,String user_name, String user_id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member p_member = null;
		String sql = "";
		String user_password=null;
		
		try{
			conn = getConnection();
			//SQL�� 
			sql = "select * from p_member where mem_email=? and mem_name=? and mem_id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_email);
			pstmt.setString(2, user_name);
			pstmt.setString(3, user_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				p_member = new Member();
				p_member.setMem_passwd(rs.getString("mem_passwd"));
				user_password = p_member.getMem_passwd();
			}
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			executeClose(rs, pstmt, conn);
		}
		return user_password;
	}
	
	//ȸ����������
	public void updateMember(Member p_member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		int cnt = 0;
		
		try{
			conn = getConnection();
			sql = "update p_member set mem_name=?,mem_passwd=?,"
				+ "mem_cell=?,mem_email=?,mem_zipcode1=?,mem_zipcode2=?,mem_addr1=?,"
				+ "mem_addr2=? where mem_id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, p_member.getMem_name());
			pstmt.setString(++cnt, p_member.getMem_passwd());
			pstmt.setString(++cnt, p_member.getMem_cell());
			pstmt.setString(++cnt, p_member.getMem_email());
			pstmt.setString(++cnt, p_member.getMem_zipcode1());
			pstmt.setString(++cnt, p_member.getMem_zipcode2());
			pstmt.setString(++cnt, p_member.getMem_addr1());
			pstmt.setString(++cnt, p_member.getMem_addr2());
			pstmt.setString(++cnt, p_member.getMem_id());
			
			pstmt.executeUpdate();
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			executeClose(null, pstmt, conn);
		}
	}
	
	//��ü ȸ��, �˻� ȸ�� !!!!!!!!!!!!!
		public int getMemberCount(String keyfield, String keyword) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "";
			int count = 0;
			
			try{
				conn = getConnection();
				if(keyword == null || "".equals(keyword)){
					sql = "select count(*) from p_member";
					pstmt = conn.prepareStatement(sql);
				}else{
					sql = "select count(*) from p_member where " + keyfield + " like ?";
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
			
		
		//ȸ��, �˻�ȸ�� ���!!!!!!!!!!!!!
		public List<Member> getListUser(int start, int end, String keyfield, String keyword) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<Member> list = null;
			String sql = "";
			int cnt = 0;
			
			try {
				conn = getConnection();
				if(keyword == null || "".equals(keyword)){
					//��üȸ�� ����
					
					sql = "select * from (select a.*, rownum rnum from (select * from p_member)a) where rnum>=? and rnum<=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(++cnt, start);
					pstmt.setInt(++cnt, end);
				}else{
					//�˻�ȸ�� ����
					sql = "select *from (select a.*, rownum rnum from (select * from p_member where "+keyfield+" like ?)a) where rnum>=? and rnum<=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(++cnt, "%"+keyword+"%");
					pstmt.setInt(++cnt, start);
					pstmt.setInt(++cnt, end);
				}
				
				
				rs = pstmt.executeQuery();
				
				list = new ArrayList<Member>();
				while(rs.next()){
					Member p_member = new Member();
					p_member.setMem_register(rs.getDate("mem_register"));
					p_member.setMem_id(rs.getString("mem_id"));
					p_member.setMem_name(rs.getString("mem_name"));
					p_member.setMem_cell(rs.getString("mem_cell"));
					p_member.setMem_email(rs.getString("mem_email"));
					
					list.add(p_member);
				}
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				executeClose(rs, pstmt, conn);
			}
			return list;
		}
	
	//ȸ��Ż��, ȸ������ ����
	public void deleteMember(String mem_id)throws Exception{
		Connection conn = null;
		//PreparedStatement pstmt = null;
		//PreparedStatement pstmt2 = null;
		//PreparedStatement pstmt3 = null;
		//PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		ResultSet rs = null;
		String sql = "";
		int cnt = 0;
		
		try{
			conn = getConnection();
			//����Ŀ�� ����
			conn.setAutoCommit(false);
			
/*			//�ش� id�� �ۼ��� ��� �θ���� �۹�ȣ ���ϱ�
			sql = "select num from iboard where mem_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			//�ش� id�� �ۼ��� ��� �θ�ۿ� �޸� ��� ����_������ �� ��� ����
			sql = "delete from iboard_reply where num=?";
			pstmt2 = conn.prepareStatement(sql);
			while(rs.next()){
				pstmt2.setInt(1, rs.getInt(1));//�θ� �۹�ȣ
				pstmt2.addBatch();
				
				++cnt;
				//��� �߰��ϸ� outofMemory �߻�
				//1000�� ������ executeBatch() �޸𸮿� �ݿ�
				if(cnt%1000==0){
					pstmt2.executeBatch();
				}
			}
			pstmt2.executeBatch();
			
			//�ش� id�� �ۼ��� ��� ��� ����
			sql = "delete from iboard_reply where mem_id=?";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setString(1, id);
			pstmt3.executeUpdate();
			
			//�ش� id�� �ۼ��� ��� �θ�� ����
			sql = "delete from iboard where mem_id=?";
			pstmt4 = conn.prepareStatement(sql);
			pstmt4.setString(1, id);
			pstmt4.executeUpdate();
*/			
			//�ش� id�� ����ϴ� ȸ�� ���� ����
			sql = "delete from p_member where mem_id=?";
			pstmt5 = conn.prepareStatement(sql);
			pstmt5.setString(1, mem_id);
			pstmt5.executeUpdate();
			
			//���ܰ� ���� ��� Ŀ��
			conn.commit();
			
		}catch(Exception e){
			//���� �߻�
			//if(conn!=null)try{conn.rollback();}catch(SQLException se){};
			throw new Exception(e);
		}finally{
			//executeClose(null, pstmt5, null);
			//executeClose(null, pstmt4, null);
			//executeClose(null, pstmt3, null);
			//executeClose(null, pstmt2, null);
			executeClose(rs, pstmt5, conn);
		}
	}
}