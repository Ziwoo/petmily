package kr.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.review.domain.Review;
import kr.util.StringUtil;

public class ReviewDao {
	//싱글턴 패턴
	private static ReviewDao instance = new ReviewDao();
	
	public static ReviewDao getInstantce(){
		return instance;
	}
	private ReviewDao(){}
	
	//context.xml에서 설정정보를 읽어들여 커넥션풀로부터
	//커넥션을 할당받음
	private Connection getConnection()throws Exception{
		Context initCtx = new InitialContext();
		DataSource ds = 
				(DataSource)initCtx.lookup(
						"java:comp/env/jdbc/xe");
		return ds.getConnection();
	}
	
	//자원정리
	private void executeClose(ResultSet rs, PreparedStatement pstmt, Connection conn){
		if(rs!=null)try{rs.close();}catch(SQLException e){}
		if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
		if(conn!=null)try{conn.close();}catch(SQLException e){}
	}
	
	//글등록
	public void insertReview(Review review) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		int cnt = 0;
		
		try {
			conn = getConnection();
			sql = "insert into p_review "
				+ "(rv_num,rv_subject,rv_content,"
				+ "rv_now_date,ip,rv_pet_picture,mem_id) "
				+ "values (review_seq.nextval,?,?,sysdate,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, review.getRv_subject());
			pstmt.setString(++cnt, review.getRv_content());
			pstmt.setString(++cnt, review.getRv_pet_picture());
			pstmt.setString(++cnt, review.getIp());
			pstmt.setString(++cnt, review.getMem_id());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			executeClose(null,pstmt,conn);
		}
	}
	//전체글 갯수, 검색글 갯수
	public int getReviewCount(String keyfield, String keyword) throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int count = 0;
		
		try{
			conn = getConnection();
			if(keyword==null || "".equals(keyword)){
				//전체 글 갯수
				sql = "select count(*) from p_review";
				pstmt = conn.prepareStatement(sql);
			}else{
				sql = "select count(*) from p_review where " 
						+ keyfield + " like ? ";
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
	public List<Review> getListReview(int start, int end, 
									String keyfield, String keyword) 
									throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Review> list = null;
		String sql = "";
		int cnt = 0;
		
		try{
			conn = getConnection();
			if(keyword ==null || "".equals(keyword)){
				//전체글 보기
				sql = "select * from (select a.*, "
					+ "rownum rnum from (select * from p_review "
					+ "order by rv_num desc)a) "
					+ "where rnum>=? and rnum<=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
			}else{
				//검색글 보기
				sql = "select * from (select a.*, rownum rnum "
					+ "from (select * from p_review where "
					+ keyfield +" like ? order by rv_num desc)a) "
					+ "where rnum>=? and rnum<=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(++cnt, "%"+keyword+"%");
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
			}
			rs = pstmt.executeQuery();
			
			list = new ArrayList<Review>();
			while(rs.next()){
				Review review = new Review();
				review.setRv_num(rs.getInt("rv_num"));
				review.setRv_subject(StringUtil.useNoHtml((rs.getString("rv_subject"))));
				review.setRv_readcount(rs.getInt("rv_readcount"));
				review.setRv_now_date(rs.getDate("rv_now_date"));
				review.setMem_id(rs.getString("mem_id"));
				//board.setReply_cnt(rs.getInt("re_cnt"));
				
				list.add(review);
			}
			
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//글 상세
	public Review getReview(int num) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Review review = null;
		String sql = "";
		
		try{
			conn = getConnection();
			sql="select * from p_review where rv_num=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				review = new Review();
				review.setRv_num(rs.getInt("rv_num"));
				review.setRv_subject(rs.getString("rv_subject"));
				review.setRv_content(rs.getString("rv_content"));
				review.setRv_readcount(rs.getInt("rv_readcount"));
				review.setRv_now_date(rs.getDate("rv_now_date"));
				review.setRv_pet_picture(rs.getString("rv_pet_picture"));
				review.setIp(rs.getString("ip"));
				review.setMem_id(rs.getString("mem_id"));
			}
			
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			executeClose(rs, pstmt, conn);
		}
		return review;
	}
	
	//글 조회수 증가
	public void updateReadcount(int num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="";
		
		try{
			conn = getConnection();
			sql="update p_review set rv_readcount=rv_readcount+1 "
				+ "where rv_num=?";
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
	public void updateReview(Review review) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="";
		int cnt = 0;
		
		try{
			conn=getConnection();
			sql = "update p_review set rv_subject=?, rv_content=?, "
				+ "rv_pet_picture=?, ip=? where rv_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, review.getRv_subject());
			pstmt.setString(++cnt, review.getRv_content());
			pstmt.setString(++cnt, review.getRv_pet_picture());
			pstmt.setString(++cnt, review.getIp());
			pstmt.setInt(++cnt, review.getRv_num());
			
			pstmt.executeUpdate();
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			executeClose(null, pstmt, conn);
		}
	}
	//글 삭제
	public void deleteReview(int num) throws Exception{
		Connection conn = null;
		//PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = "";
		
		try{
			conn = getConnection();
			//오토커밋 해제
			//conn.setAutoCommit(false);
			
			//댓글 삭제
			//sql = "delete from iboard_reply where num=?";
			//pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, num);
			//pstmt.executeUpdate();
			
			//부모글 삭제
			sql="delete from p_review where rv_num=?";
			pstmt2= conn.prepareStatement(sql);
			pstmt2.setInt(1, num);
			pstmt2.executeUpdate();
			
			//예외 발생없이 정상적으로 SQL문 실행
			conn.commit();
			
		}catch(Exception e){
			//예외가 발생
			//if(conn!=null)try{conn.rollback();}catch(SQLException se){}
			throw new Exception(e);
		}finally{
			executeClose(null, pstmt2, conn);
			//executeClose(null, pstmt, conn);
		}
		
	}
	/*
	//댓글 등록
	public void replyInsertBoard(BoardReply boardReply) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt = null;
		String sql = "";
		int cnt=0;
		
		try{
			conn=getConnection();
			sql="insert into iboard_reply (re_num,re_content,re_date,re_ip,num,id) values (reply_seq.nextval,?,sysdate,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(++cnt, boardReply.getRe_content());
			pstmt.setString(++cnt, boardReply.getRe_ip());
			pstmt.setInt(++cnt, boardReply.getNum());
			pstmt.setString(++cnt, boardReply.getId());
			
			pstmt.executeUpdate();
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			executeClose(null, pstmt, conn);
		}
	}
	//댓글 갯수
	public int getReplyBoardCount(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try{
			conn = getConnection();
			sql = "select count(*) from iboard_reply where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
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
	//댓글 목록
	public List<BoardReply> getListReplyBoard(int start, int end, int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardReply> list = null;
		String sql = "";
		
		try{
			conn = getConnection();
			sql="select * from (select a.*, "
					+ " rownum rnum from (select re_num, "
					+ " to_char(re_date,'YYYY-MM-DD HH24:MI:SS') re_date, "
					+ " re_content, num, id from iboard_reply "
					+ " where num=? order by re_num desc)a) "
					+ " where rnum >=? and rnum <= ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			
			list = new ArrayList<BoardReply>();
			while(rs.next()){
				BoardReply board = new BoardReply();
				board.setRe_num(rs.getInt("re_num"));
				board.setRe_date(rs.getString("re_date"));
				board.setRe_content(rs.getString("re_content"));
				board.setNum(rs.getInt("num"));
				board.setId(rs.getString("id"));
				
				list.add(board);
			}
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			executeClose(rs, pstmt, conn);
		}
		return list;
	}
	//댓글 수정
	public void updateReplyBoard(BoardReply boardReply) throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql = "";
		int cnt = 0;
		
		try{
			conn = getConnection();
			sql = "update iboard_reply set re_content=?, re_ip=? where re_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, boardReply.getRe_content());
			pstmt.setString(++cnt, boardReply.getRe_ip());
			pstmt.setInt(++cnt, boardReply.getRe_num());
			
			pstmt.executeUpdate();
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			executeClose(null, pstmt, conn);
		}
	}
	//댓글 삭제
	public void deleteReplyBoard(int reply_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt=null;
		String sql = "";
		
		try{
			conn = getConnection();
			sql = "delete from iboard_reply where re_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reply_num);
			
			pstmt.executeUpdate();
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			executeClose(null, pstmt, conn);
		}
	}*/
}
