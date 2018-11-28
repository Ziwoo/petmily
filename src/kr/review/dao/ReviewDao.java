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
	//�̱��� ����
	private static ReviewDao instance = new ReviewDao();
	
	public static ReviewDao getInstantce(){
		return instance;
	}
	private ReviewDao(){}
	
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
	//��ü�� ����, �˻��� ����
	public int getReviewCount(String keyfield, String keyword) throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int count = 0;
		
		try{
			conn = getConnection();
			if(keyword==null || "".equals(keyword)){
				//��ü �� ����
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
	//���, �˻��� ���
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
				//��ü�� ����
				sql = "select * from (select a.*, "
					+ "rownum rnum from (select * from p_review "
					+ "order by rv_num desc)a) "
					+ "where rnum>=? and rnum<=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
			}else{
				//�˻��� ����
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
	
	//�� ��
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
	
	//�� ��ȸ�� ����
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
	
	//�� ����
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
	//�� ����
	public void deleteReview(int num) throws Exception{
		Connection conn = null;
		//PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = "";
		
		try{
			conn = getConnection();
			//����Ŀ�� ����
			//conn.setAutoCommit(false);
			
			//��� ����
			//sql = "delete from iboard_reply where num=?";
			//pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, num);
			//pstmt.executeUpdate();
			
			//�θ�� ����
			sql="delete from p_review where rv_num=?";
			pstmt2= conn.prepareStatement(sql);
			pstmt2.setInt(1, num);
			pstmt2.executeUpdate();
			
			//���� �߻����� ���������� SQL�� ����
			conn.commit();
			
		}catch(Exception e){
			//���ܰ� �߻�
			//if(conn!=null)try{conn.rollback();}catch(SQLException se){}
			throw new Exception(e);
		}finally{
			executeClose(null, pstmt2, conn);
			//executeClose(null, pstmt, conn);
		}
		
	}
	/*
	//��� ���
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
	//��� ����
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
	//��� ���
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
	//��� ����
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
	//��� ����
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
