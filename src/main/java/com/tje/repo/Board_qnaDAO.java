package com.tje.repo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tje.model.*;
import com.tje.repo.Board_itemDAO.Board_itemRowMapper;



@Repository
public class Board_qnaDAO {
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public Board_qnaDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class Board_QnaRowMapper implements RowMapper<Board_Qna>{

		@Override
		public Board_Qna mapRow(ResultSet rs, int rowNum) throws SQLException {
			Board_Qna board_qna=new Board_Qna(
					rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getInt(8),
					rs.getDate(9));
			
			return board_qna;
		}
		
	}
	
	public int listCountCriteria() {
		return this.jdbcTemplate.queryForObject("select count(*) from board_qna", Integer.class);
	}
	
	public List<Board_Qna> selectAllOrdByDateDesc(int pageStart, int perPageNum) {
		List<Board_Qna> results=this.jdbcTemplate.query("select * from board_qna where board_id>0 order by board_id desc limit ?,?",
				new Board_QnaRowMapper(),
				pageStart,
				perPageNum
				);
		return results.isEmpty() ? null : results;
	}
	
	public Board_Qna selectOne(Board_Qna model) {
		return this.jdbcTemplate.queryForObject("select * from board_qna where board_id = ?", 
				new Board_QnaRowMapper(), 
				model.getBoard_id());
	}
	
 
	public List<Board_Qna> selectAll() {
		String sql = "select * from board_qna";
		List<Board_Qna> results=this.jdbcTemplate.query(sql, new Board_QnaRowMapper());
		return results.isEmpty() ? null : results;
	}

	public Board_Qna upQna(Board_Qna model) {
		String sql = "select * from board_qna set category = 6 where board_id > ? order by board_id limit 1";
		try {
			return this.jdbcTemplate.queryForObject(sql,
					new Board_QnaRowMapper(), 
					model.getBoard_id());
		} catch (Exception e) {
			return null;
		}
	}
	
	public Board_Qna downQna(Board_Qna model) {
		String sql = "select * from board_qna set category = 6 where board_id < ? order by board_id desc limit 1";
		try {
			return this.jdbcTemplate.queryForObject(sql,
					new Board_QnaRowMapper(), 
					model.getBoard_id());
		} catch (Exception e) {
			return null;
		}
	}
	
	public int insert(Board_Qna model) {
		return this.jdbcTemplate.update("insert into board_qna values(0,6,?,?,?,?,?,0,now())",
				model.getCategory(),
				model.getTitle(),
				model.getContent(),
				model.getMember_id(),
				model.getNickname()
				);
		
	}

	public int[] batchDelete(List<BoardsJosnModel> model) {
		return jdbcTemplate.batchUpdate("delete from board_qna where board_id=?",
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
	
	public List<Board_Qna> select_search(HashMap<String, Object> model){
		String group=(String) model.get("group");
		String sql="select * from board_qna where member_id=? and "+group+" like ? and write_date between ? and ?";
		List<Board_Qna> results=jdbcTemplate.query(sql, new Board_QnaRowMapper(),
				model.get("member_id"),
				"%"+model.get("search")+"%",
				model.get("from"),
				model.get("to"));
		return results.isEmpty() ? null : results;
	}
	
}
