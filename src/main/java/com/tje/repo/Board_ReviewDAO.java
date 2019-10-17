package com.tje.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.tje.model.*;
import com.tje.repo.Board_itemDAO.Board_itemRowMapper;

@Repository
public class Board_ReviewDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public Board_ReviewDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class Board_ReviewRowMapper implements RowMapper<Board_Review>{

		@Override
		public Board_Review mapRow(ResultSet rs, int rowNum) throws SQLException {
			Board_Review board_Review=new Board_Review(
					rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getInt(8),
					rs.getTimestamp(9));
			return board_Review;
		}
		
	}
	public int delete(Board_Review model) throws Exception{
		return this.jdbcTemplate.update("delete from board_review where board_id = ?",
				model.getBoard_id());
	}
	
	public int update(Board_Review model) throws Exception{
		return this.jdbcTemplate.update("update board_review set category = ?, title = ?, content = ?, image = ? where board_id = ?",
				model.getCategory(),
				model.getTitle(),
				model.getContent(),
				model.getImage(),
				model.getBoard_id());
	}
	
	
	public int insert(Board_Review model) throws Exception{
		return this.jdbcTemplate.update("insert into Board_Review values(0,1,?,?,?,?,?,0,now())",
				model.getCategory(),
				model.getTitle(),
				model.getContent(),
				model.getMember_id(),
				model.getImage());
	}
	
	public int insertReturnsKey(Board_Review model) throws Exception{
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into Board_Review values(0,1,?,?,?,?,?,0,now())", new String[]{"board_id"});
				pstmt.setInt(1, model.getCategory());
				pstmt.setString(2, model.getTitle());
				pstmt.setString(3, model.getContent());
				pstmt.setString(4, model.getMember_id());
				pstmt.setString(5, model.getImage());
				return pstmt;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	public int[] batchDelete(List<BoardsJosnModel> model) {
		return jdbcTemplate.batchUpdate("delete from Board_Review where board_id=?",
				new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						// TODO Auto-generated method stub
						ps.setInt(1, model.get(i).getBoard_id());
					}
					
					@Override
					public int getBatchSize() {
						return model.size();
					}
			});
	}
	
	public List<Board_Review> select_search(HashMap<String, Object> model){
		String group=(String) model.get("group");
		String sql="select * from Board_Review where member_id=? and "+group+" like ? and write_date between ? and ?";
		List<Board_Review> results=jdbcTemplate.query(sql, new Board_ReviewRowMapper(),
				model.get("member_id"),
				"%"+model.get("search")+"%",
				model.get("from"),
				model.get("to"));
		return results.isEmpty() ? null : results;
	}
}
