package kr.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.notice.domain.Notice;
import kr.util.StringUtil;

public class NoticeDao {
	//싱글턴패턴
	private static NoticeDao instance = new NoticeDao();
	
	public static NoticeDao getInstance(){
		return instance;
	}
	
	private NoticeDao(){}
	
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
		public void insertNotice(Notice nb)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "";
			int cnt = 0;
			
			try{
				conn = getConnection();
				sql = "insert into p_noticeboard (nb_num,mem_id,nb_subject,nb_content,nb_reg_date,nb_passwd,filename)"
						+ "values (p_faq_seq.nextval,?,?,?,sysdate,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(++cnt, nb.getMem_id());
				pstmt.setString(++cnt, nb.getNb_subject());
				pstmt.setString(++cnt, nb.getNb_content());
				pstmt.setString(++cnt, nb.getNb_passwd());
				pstmt.setString(++cnt, nb.getFilename());
				
				pstmt.executeUpdate();
			}catch(Exception e){
				throw new Exception(e);
			}finally{
				executeClose(null,pstmt,conn);
			}
		}
		
		//전체글 갯수, 검색글 갯수
		public int getNoticeCount(String keyfield, String keyword) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "";
			int count = 0;
			
			try{
				conn = getConnection();
				if(keyword == null || "".equals(keyword)){
					//전체글 갯수
					sql = "select count(*) from p_noticeboard";
					pstmt = conn.prepareStatement(sql);
				}else{
					sql = "select count(*) from p_noticeboard where " + keyfield + " like ?";
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
		public List<Notice> getListNotice(int start, int end, String keyfield, String keyword) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<Notice> list = null;
			String sql = "";
			int cnt = 0;
			
			try {
				conn = getConnection();
				if(keyword == null || "".equals(keyword)){
					//전체글 보기
					sql = "select * from (select a.*, rownum rnum from (select * from p_noticeboard order by nb_num desc)a)"
							+ " where rnum>=? and rnum<=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(++cnt, start);
					pstmt.setInt(++cnt, end);	
				}else{
					//검색글 보기
					sql = "select * from (select a.*, rownum rnum from (select * from p_noticeboard where " + keyfield + " like ? order by nb_num desc)a)"
							+ " where rnum>=? and rnum<=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(++cnt, "%"+keyword+"%");
					pstmt.setInt(++cnt, start);
					pstmt.setInt(++cnt, end);
				}
				
				
				rs = pstmt.executeQuery();
				
				list = new ArrayList<Notice>();
				while(rs.next()){
					Notice nb = new Notice();
					nb.setNb_num(rs.getInt("nb_num"));
					nb.setNb_subject(StringUtil.useNoHtml(rs.getString("nb_subject")));
					nb.setNb_readcount(rs.getInt("nb_readcount"));
					nb.setNb_reg_date(rs.getDate("nb_reg_date"));
					nb.setMem_id(rs.getString("mem_id"));
					
					list.add(nb);
				}
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				executeClose(rs, pstmt, conn);
			}
			return list;
		}
		
		//글 상세
		public Notice getNotice(int num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Notice nb = null;
			String sql = "";
			
			try {
				conn = getConnection();
				sql = "select * from p_noticeboard where nb_num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					nb = new Notice();
					nb.setNb_num(rs.getInt("nb_num"));
					nb.setNb_subject(rs.getString("nb_subject"));
					nb.setNb_content(rs.getString("nb_content"));
					nb.setFilename(rs.getString("filename"));
					nb.setNb_readcount(rs.getInt("nb_readcount"));
					nb.setNb_reg_date(rs.getDate("nb_reg_date"));
					nb.setMem_id(rs.getString("mem_id"));
				}
				
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				executeClose(rs, pstmt, conn);
			}
			
			return nb;
		}
		
		//글 조회수 증가
		public void updateReadcount(int num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "";
			
			try{
				conn = getConnection();
				sql = "update p_noticeboard set nb_readcount=nb_readcount+1 where nb_num=?";
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
		public void updateNotice(Notice nb) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "";
			int cnt = 0;
			
			try {
				conn = getConnection();
				
				sql = "update p_noticeboard set nb_subject=?,nb_content=?,filename=? where nb_num=?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(++cnt, nb.getNb_subject());
				pstmt.setString(++cnt, nb.getNb_content());
				pstmt.setString(++cnt, nb.getFilename());
				pstmt.setInt(++cnt, nb.getNb_num());
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				throw new Exception(e);
			} finally {
				executeClose(null, pstmt, conn);
			}
			
		}
		//글 삭제
		public void deleteNotice(int num) throws Exception{
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "";
			
			try{
				conn = getConnection();
				
				//부모글삭제
				sql = "delete from p_noticeboard where nb_num=?";
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
