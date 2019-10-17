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

import com.tje.model.Comment;

@Repository
public class CommentDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public CommentDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class CommentRowMapper implements RowMapper<Comment>{

		@Override
		public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
			Comment Comment=new Comment(
					rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getTimestamp(7));
			return Comment;
		}
	}
	
	public Comment selectOne(Comment model) {
		return this.jdbcTemplate.queryForObject("select * from Comment where Comment_id=?", 
				new CommentRowMapper(), 
				model.getComment_id());
	}
	
	public List<Comment> selectAll() {
		List<Comment> results=this.jdbcTemplate.query("select * from Comment",
				new CommentRowMapper());
		
		return results.isEmpty() ? null : results;
	}
	
	public List<Comment> select(Comment model) {
		List<Comment> results=this.jdbcTemplate.query("select * from Comment where board_id=? and topic=?",
				new CommentRowMapper(),
				model.getBoard_id(),
				model.getTopic());
		
		return results.isEmpty() ? null : results;
	}
	
	public int delete(Comment model) {
		return this.jdbcTemplate.update("delete from comment where comment_id=?", 
				model.getComment_id());
	}
	
	public int insert(Comment model) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		int result=this.jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt=con.prepareStatement("insert into Comment values(0,?,?,?,?,?,now())",
						new String[] { "comment_id" });
				
				pstmt.setInt(1, model.getBoard_id());
				pstmt.setInt(2, model.getTopic());
				pstmt.setString(3, model.getMember_id());
				pstmt.setString(4, model.getNickname());
				pstmt.setString(5, model.getContent());
				
				return pstmt;
			}
		}, keyHolder);
		
		return keyHolder.getKey().intValue();
	}

}
