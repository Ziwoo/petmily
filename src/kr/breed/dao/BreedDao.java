package kr.breed.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.breed.domain.Breed;
import kr.util.StringUtil;

public class BreedDao {
	//싱글톤 패턴
	private static BreedDao instance = new BreedDao();

	public static BreedDao getInstance(){
		return instance;
	}

	private BreedDao(){}

	//context.xml에서 설정정보를 읽어들여 커넥션풀로부터
	//커넥션을 할당받음
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		DataSource ds = 
				(DataSource)initCtx.lookup(
						"java:comp/env/jdbc/xe");
		return ds.getConnection();
	}

	//자원정리
	private void executeClose(ResultSet rs, 
			PreparedStatement pstmt, 
			Connection conn){
		if(rs!=null)try{rs.close();}catch(SQLException e){}
		if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
		if(conn!=null)try{conn.close();}catch(SQLException e){}
	}
	
	//글 등록
	public void insertBreed(Breed breed) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		int cnt = 0;
		
		try{
			conn = getConnection();
			sql = "insert into p_breed "
				+ "(br_num,mem_id,br_subject,br_content,"
				+ "br_passwd,br_regdate,br_pic,br_readcount) "	
				+ "values "
				+ "(breed_seq.nextval,?,?,?,?,sysdate,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, breed.getMem_id());
			pstmt.setString(++cnt, breed.getBr_subject());
			pstmt.setString(++cnt, breed.getBr_content());
			pstmt.setString(++cnt, breed.getBr_passwd());
			pstmt.setString(++cnt, breed.getBr_pic());
			pstmt.setInt(++cnt, breed.getBr_readcount());
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			executeClose(null, pstmt, conn);
		}
	}
	
	//전체 글 갯수, 검색글 갯수
	public int getBreedCount(String keyfield, String keyword)
	throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";	
		int count = 0;
		
		try{
			conn = getConnection();
			
			if(keyword==null || "".equals(keyword)){
				//전체 글 갯수
				sql = "select count(*) from p_breed";
				pstmt = conn.prepareStatement(sql);
			}else{
				//검색 글
				sql = "select count(*) from p_breed where "+keyfield+" like ?";
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
	public List<Breed> getListBreed(int start, int end,
									String keyfield, String keyword)
	throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Breed> list = null;
		String sql = "";
		int cnt = 0;
		
		try{
			conn = getConnection();
			if(keyword==null || "".equals(keyword)){
				//전체 글 보기
				sql = "select *from (select a.*, rownum rnum "
					+"from (select *from p_breed order by br_num "
					+"desc) a) where rnum>=? and rnum<=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
			}else{
				//검색 글 보기
				sql = "select *from (select a.*, rownum rnum "
					+ "from (select *from p_breed where "
					+ keyfield+" like ? order by br_num desc)"
					+ "a) where rnum>=? and rnum<=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(++cnt, "%"+keyword+"%");
				pstmt.setInt(++cnt, start);
				pstmt.setInt(++cnt, end);
			}
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<Breed>();
			while(rs.next()){
				Breed breed = new Breed();
				breed.setBr_num(rs.getInt("br_num"));
				breed.setBr_subject(StringUtil.useNoHtml(rs.getString("br_subject")));
				breed.setBr_readcount(rs.getInt("br_readcount"));
				breed.setMem_id(rs.getString("mem_id"));
				//댓글수 보여주는 코드추가 : 댓글 구현시
				
				list.add(breed);
			}
			
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//글 상세
	public Breed getBreed(int br_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		Breed breed = null;
		String sql = "";
		
		try{
			conn = getConnection();
			
			sql = "select *from p_breed where br_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, br_num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				breed = new Breed();
				breed.setBr_num(rs.getInt("br_num"));
				breed.setBr_subject(rs.getString("br_subject"));
				breed.setBr_content(rs.getString("br_content"));
				breed.setBr_readcount(rs.getInt("br_readcount"));
				breed.setBr_regdate(rs.getDate("br_regdate"));
				breed.setBr_pic(rs.getString("br_pic"));
				breed.setMem_id(rs.getString("mem_id"));
				breed.setBr_passwd(rs.getString("br_passwd"));
			}
			
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			executeClose(rs, pstmt, conn);
		}
		return breed;
	}
	
	//글 조회수 증가
	public void updateReadCount(int br_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		try{
			conn = getConnection();
			
			sql = "update p_breed set br_readcount=br_readcount+1 where br_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, br_num);
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			executeClose(null, pstmt, conn);
		}
	}
	
	//글 수정
	public void updateBreed(Breed breed) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		int cnt = 0;
		
		try{
			conn = getConnection();
			
			sql = "update p_breed set br_subject=?, br_content=?, "
					+ "br_passwd=? ,br_pic=? where br_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, breed.getBr_subject());
			pstmt.setString(++cnt, breed.getBr_content());
			pstmt.setString(++cnt, breed.getBr_passwd());
			pstmt.setString(++cnt, breed.getBr_pic());
			pstmt.setInt(++cnt, breed.getBr_num());
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			executeClose(null, pstmt, conn);
		}
	}
	
	//글 삭제
	public void deleteBreed(int br_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		//댓글 추가시 pstmt 객체 추가하고 수정
		try{
			conn = getConnection();
			
			sql = "delete from p_breed where br_num=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, br_num);
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			throw new Exception(e);
		}finally{
			executeClose(null, pstmt, conn);
		}
	}
}
