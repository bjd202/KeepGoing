package com.tje.repo;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tje.model.*;
import com.tje.repo.DetailBoardFreeViewDAO.DetailBoardFreeViewRowMapper;

import java.util.*;

@Repository
public class DetailBoardFreeViewDAO {
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public DetailBoardFreeViewDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class DetailBoardFreeViewRowMapper implements RowMapper<DetailBoardFree_View>{
		@Override
		public DetailBoardFree_View mapRow(ResultSet rs, int rowNum) throws SQLException {
			DetailBoardFree_View detailBoardFreeView=new DetailBoardFree_View(
					rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getInt(7),
					rs.getString(8),
					rs.getString(9),
					rs.getInt(10),
					rs.getInt(11),
					rs.getInt(12),
					rs.getDate(13));
			

			
			return detailBoardFreeView;
		}
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////
public List<DetailBoardFree_View> selectOrderByLikeCount() throws Exception{
String sql = "select * from detailboardfreeview where like_cnt >=1 order by like_cnt desc limit 10";
return this.jdbcTemplate.query(sql, new DetailBoardFreeViewRowMapper());
}
	
	
	// 게시글 조회 전체 조회
	public DetailBoardFree_View selectOne(DetailBoardFree_View model) {
		String sql = "select * from detailboardfreeview where board_id=?";
		return this.jdbcTemplate.queryForObject(sql, 
				new DetailBoardFreeViewRowMapper(), 
				model.getBoard_id());
	}
	
	
	
	// 게시글 조회수
	public int update_view_cnt(DetailBoardFree_View model) {
		String sql = "update board_free set view_cnt=view_cnt+1 where board_id=?";
		
		return this.jdbcTemplate.update(sql,
				model.getBoard_id());
	}
	
	
	//좋아요 카운트
	public List<DetailBoardFree_View> selectAllOrdByLikeCntAsc() {
		String sql = "select * from DetailBoardFreeView order by like_cnt asc";
		List<DetailBoardFree_View> results=this.jdbcTemplate.query(sql,
				new DetailBoardFreeViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	//싫어요 카운트
	public List<DetailBoardFree_View> selectAllOrdByDisLikeCntAsc() {
		String sql = "select * from DetailBoardFreeView order by dislike_cnt asc";
		List<DetailBoardFree_View> results=this.jdbcTemplate.query(sql,
				new DetailBoardFreeViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	

	
	// 게시판 삭제
	public int delete(DetailBoardFree_View model) {
				return this.jdbcTemplate.update("delete from board_free where board_id = ?",
	
				model.getBoard_id());
	}
	
	
	// 게시판 수정
	public int update(DetailBoardFree_View model) {
		
				return this.jdbcTemplate.update("update board_free set Content = ? , Title = ? , image=?, category=? where board_id = ? " ,
				model.getContent(),
				model.getTitle(),
				model.getImage(),
				model.getCategory(),
				model.getBoard_id()
				
				);
				
				
	}


	
}



















