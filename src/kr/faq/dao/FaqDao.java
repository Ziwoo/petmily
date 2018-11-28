package kr.faq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.faq.domain.Faq;
import kr.util.StringUtil;

public class FaqDao {
	//싱글턴패턴
	private static FaqDao instance = new FaqDao();
	
	public static FaqDao getInstance(){
		return instance;
	}
	
	private FaqDao(){}
	
	//context.xml에서 설정정보를 읽어들여 커넥션풀로부터
		//커넥션을 할당받음
		private Connection getConnection()throws Exception{
			Context initCtx = new InitialContext();
			DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/xe");
			return ds.getConnection();
		}
		
		//자원정리
		private void executeClose(ResultSet rs, PreparedStatement pstmt, Connection conn){
			if(rs!=null)try{rs.close();}catch(SQLException e){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
			if(conn!=null)try{conn.close();}catch(SQLException e){}
		}
		
		//글등록
		public void insertFaq(Faq faq)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "";
			int cnt = 0;
			
			try{
				conn = getConnection();
				sql = "insert into p_faq (faq_num,mem_id,faq_subject,faq_content,faq_reg_date,faq_passwd)"
						+ "values (p_faq_seq.nextval,?,?,?,sysdate,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(++cnt, faq.getMem_id());
				pstmt.setString(++cnt, faq.getFaq_subject());
				pstmt.setString(++cnt, faq.getFaq_content());
				pstmt.setString(++cnt, faq.getFaq_passwd());
				
				pstmt.executeUpdate();
			}catch(Exception e){
				throw new Exception(e);
			}finally{
				executeClose(null,pstmt,conn);
			}
		}
		
		//전체글 갯수, 검색글 갯수
		public int getFaqCount(String keyfield, String keyword) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "";
			int count = 0;
			
			try{
				conn = getConnection();
				if(keyword == null || "".equals(keyword)){
					//전체글 갯수
					sql = "select count(*) from p_faq";
					pstmt = conn.prepareStatement(sql);
				}else{
					sql = "select count(*) from p_faq where " + keyfield + " like ?";
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
		
		//목록, 검색글 목록
		public List<Faq> getListFaq(int start, int end, String keyfield, String keyword) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<Faq> list = null;
			String sql = "";
			int cnt = 0;
			
			try {
				conn = getConnection();
				if(keyword == null || "".equals(keyword)){
					//전체글 보기
					sql = "select * from (select a.*, rownum rnum from (select * from p_faq order by faq_num desc)a)"
							+ " where rnum>=? and rnum<=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(++cnt, start);
					pstmt.setInt(++cnt, end);	
				}else{
					//검색글 보기
					sql = "select * from (select a.*, rownum rnum from (select * from p_faq where " + keyfield + " like ? order by faq_num desc)a)"
							+ " where rnum>=? and rnum<=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(++cnt, "%"+keyword+"%");
					pstmt.setInt(++cnt, start);
					pstmt.setInt(++cnt, end);
				}
				
				
				rs = pstmt.executeQuery();
				
				list = new ArrayList<Faq>();
				while(rs.next()){
					Faq faq = new Faq();
					faq.setFaq_num(rs.getInt("faq_num"));
					faq.setFaq_subject(StringUtil.useNoHtml(rs.getString("faq_subject")));
					faq.setFaq_readcount(rs.getInt("faq_readcount"));
					faq.setFaq_reg_date(rs.getDate("faq_reg_date"));
					faq.setMem_id(rs.getString("mem_id"));
					//board.setReply_cnt(rs.getInt("re_cnt"));
					
					list.add(faq);
				}
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				executeClose(rs, pstmt, conn);
			}
			return list;
		}
		
		//글 상세
		public Faq getFaq(int num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Faq faq = null;
			String sql = "";
			
			try {
				conn = getConnection();
				sql = "select * from p_faq where faq_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					faq = new Faq();
					faq.setFaq_num(rs.getInt("faq_num"));
					faq.setFaq_subject(rs.getString("faq_subject"));
					faq.setFaq_content(rs.getString("faq_content"));
					faq.setFaq_readcount(rs.getInt("faq_readcount"));
					faq.setFaq_reg_date(rs.getDate("faq_reg_date"));
					faq.setMem_id(rs.getString("mem_id"));
				}
				
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				executeClose(rs, pstmt, conn);
			}
			
			return faq;
		}
		
		//글 조회수 증가
		public void updateReadcount(int num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "";
			
			try{
				conn = getConnection();
				sql = "update p_faq set faq_readcount=faq_readcount+1 where faq_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
			}catch(Exception e){
				throw new Exception(e);
			}finally{
				executeClose(null, pstmt, conn);
			}
		}
		
		//글 수정
		public void updateFaq(Faq faq) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "";
			int cnt = 0;
			
			try {
				conn = getConnection();
				
				sql = "update p_faq set faq_subject=?,faq_content=? where faq_num=?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(++cnt, faq.getFaq_subject());
				pstmt.setString(++cnt, faq.getFaq_content());
				pstmt.setInt(++cnt, faq.getFaq_num());
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				executeClose(null, pstmt, conn);
			}
			
		}
		//글 삭제
		public void deleteFaq(int num) throws Exception{
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "";
			
			try{
				conn = getConnection();
				
				//부모글삭제
				sql = "delete from p_faq where faq_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				
				//예외발생이 없이 정상적으로 SQL문 실행
				conn.commit();
						
			}catch(Exception e){
				throw new Exception(e);
			}finally{
				executeClose(null, pstmt, conn);
			}
		}

}
