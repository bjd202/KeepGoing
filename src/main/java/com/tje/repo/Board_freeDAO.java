package com.tje.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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



@Repository
public class Board_freeDAO {
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public Board_freeDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class Board_FreeRowMapper implements RowMapper<Board_Free>{

		@Override
		public Board_Free mapRow(ResultSet rs, int rowNum) throws SQLException {
			Board_Free board_free=new Board_Free(
					rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getInt(8),
					rs.getTimestamp(9));
			
			return board_free;
		}
		
	}
	
	public int insert(Board_Free model) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		this.jdbcTemplate.update(new PreparedStatementCreator() {			
			
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
	
				PreparedStatement pstmt = 
					con.prepareStatement(
							"insert into board_free values(0,2,?,?,?,?,?,null,now())", 
						 new String[]{"board_id"});			
				pstmt.setInt(1, model.getCategory());
				pstmt.setString(2, model.getTitle());
				pstmt.setString(3, model.getContent());
				pstmt.setString(4, model.getMember_id());
				return pstmt;
			}
		}, keyHolder);
		
		return keyHolder.getKey().intValue();
		
		
	}

	public int[] batchDelete(List<BoardsJosnModel> model) {
		return jdbcTemplate.batchUpdate("delete from board_free where board_id=?",
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
	
	public List<Board_Free> select_search(HashMap<String, Object> model){
		String group=(String) model.get("group");
		String sql="select * from board_free where member_id=? and "+group+" like ? and write_date between ? and ?";
		List<Board_Free> results=jdbcTemplate.query(sql, new Board_FreeRowMapper(),
				model.get("member_id"),
				"%"+model.get("search")+"%",
				model.get("from"),
				model.get("to"));
		return results.isEmpty() ? null : results;
	}
}
