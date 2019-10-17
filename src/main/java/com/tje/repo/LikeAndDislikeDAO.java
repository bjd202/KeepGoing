package com.tje.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.tje.model.LikeAndDislike;

@Repository
public class LikeAndDislikeDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public LikeAndDislikeDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class LikeAndDislikeRowMapper implements RowMapper<LikeAndDislike>{

		@Override
		public LikeAndDislike mapRow(ResultSet rs, int rowNum) throws SQLException {
			LikeAndDislike LikeAndDislike=new LikeAndDislike(
					rs.getInt(1),
					rs.getString(2),
					rs.getInt(3),
					rs.getInt(4),
					rs.getInt(5));
			return LikeAndDislike;
		}
	}
	
	public LikeAndDislike selectOne(LikeAndDislike model) {
		try {
			return this.jdbcTemplate.queryForObject("select * from LikeAndDislike where member_id=? and board_id=? and topic=?", 
					new LikeAndDislikeRowMapper(), 
					model.getMember_id(),
					model.getBoard_id(),
					model.getTopic());
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public LikeAndDislike selectOneIsLike(LikeAndDislike model) {
		try {
			return this.jdbcTemplate.queryForObject("select * from LikeAndDislike where member_id=? and board_id=? and topic=? and is_like=?", 
					new LikeAndDislikeRowMapper(), 
					model.getMember_id(),
					model.getBoard_id(),
					model.getTopic(),
					model.getIs_like());
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public List<LikeAndDislike> selectAll() {
		List<LikeAndDislike> results=this.jdbcTemplate.query("select * from LikeAndDislike",
				new LikeAndDislikeRowMapper());
		
		return results.isEmpty() ? null : results;
	}
	
	public List<LikeAndDislike> select(LikeAndDislike model) {
		List<LikeAndDislike> results=this.jdbcTemplate.query("select * from LikeAndDislike where board_id=? and topic=?",
				new LikeAndDislikeRowMapper(),
				model.getBoard_id(),
				model.getTopic());
		
		return results.isEmpty() ? null : results;
	}
	
	public int like_cnt(LikeAndDislike model) {
		return this.jdbcTemplate.queryForObject("select count(*) from likeanddislike where is_like=1 and topic=? and board_id=?",
				new Object[] {model.getTopic(), model.getBoard_id()},
				Integer.class);
	}
	
	public int dislike_cnt(LikeAndDislike model) {
		return this.jdbcTemplate.queryForObject("select count(*) from likeanddislike where is_like=2 and topic=? and board_id=?",
				new Object[] {model.getTopic(), model.getBoard_id()},
				Integer.class);
	}
	
	public int delete(LikeAndDislike model) {
		return this.jdbcTemplate.update("delete from LikeAndDislike where member_id=? and board_id=? and topic=?", 
				model.getMember_id(),
				model.getBoard_id(),
				model.getTopic());
	}
	
	public int update_islike(LikeAndDislike model) {
		return this.jdbcTemplate.update("update LikeAndDislike set is_like=? where member_id=? and board_id=? and topic=?", 
				model.getIs_like(),
				model.getMember_id(),
				model.getBoard_id(),
				model.getTopic());
	}
	
	public int insert(LikeAndDislike model) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		int result=this.jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt=con.prepareStatement("insert into LikeAndDislike values(0,?,?,?,?)",
						new String[] { "like_id" });
				
				pstmt.setString(1, model.getMember_id());
				pstmt.setInt(2, model.getBoard_id());
				pstmt.setInt(3, model.getTopic());
				pstmt.setInt(4, model.getIs_like());
				
				return pstmt;
			}
		}, keyHolder);
		
		return keyHolder.getKey().intValue();
	}
}
