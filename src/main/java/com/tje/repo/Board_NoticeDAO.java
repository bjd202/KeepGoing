package com.tje.repo;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tje.model.*;
import java.util.*;

@Repository
public class Board_NoticeDAO {
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public Board_NoticeDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class Board_noticeRowMapper implements RowMapper<Board_Notice>{
		@Override
		public Board_Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
			Board_Notice board_notice=new Board_Notice(
					rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getString(4),
					rs.getString(5),
					rs.getDate(6));
			return board_notice;
		}
	}
	
	public int listCountCriteria() {
		return this.jdbcTemplate.queryForObject("select count(*) from board_notice", Integer.class);
	}
	
	public Board_Notice selectOne(Board_Notice model) {
		String sql = "select * from board_notice where board_id=?";
		return this.jdbcTemplate.queryForObject(sql, 
				new Board_noticeRowMapper(), 
				model.getBoard_id());
	}
	

	public List<Board_Notice> selectAll() {
		String sql = "select * from board_notice";
		List<Board_Notice> results=this.jdbcTemplate.query(sql,
				new Board_noticeRowMapper());
		return results.isEmpty() ? null : results;		
		
	}
	
	public List<Board_Notice> selectAllOrdByDateDesc() {
		String sql = "select * from board_notice order by write_date desc";
		List<Board_Notice> results=this.jdbcTemplate.query(sql,
				new Board_noticeRowMapper());
    return results.isEmpty() ? null : results;
	}

	public List<Board_Notice> selectAllOrdByDateDesc(int pageStart, int perPageNum) {
		List<Board_Notice> results=this.jdbcTemplate.query("select * from board_notice order by board_id desc limit ?,?",
				new Board_noticeRowMapper(),
				pageStart, perPageNum);

		return results.isEmpty() ? null : results;
	}
	
	public List<Board_Notice> selectAllOrdByDateAsc() {
		String sql = "select * from board_notice order by write_date asc";
		List<Board_Notice> results=this.jdbcTemplate.query(sql,
				new Board_noticeRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<Board_Notice> selectAllOrdByBoard_IdDesc() {
		String sql = "select * from board_notice order by board_id desc";
		List<Board_Notice> results=this.jdbcTemplate.query(sql,
				new Board_noticeRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public int insert(Board_Notice model) {
		return this.jdbcTemplate.update("insert into board_notice values(0,4,?,?,?,now())",
				model.getHead(),
				model.getTitle(),
				model.getContent()
				);
	}
	
	public List<Board_Notice> selectAll_Head() {
		String sql = "select * from board_notice where head = 2 order by board_id desc limit 3";
		List<Board_Notice> results=this.jdbcTemplate.query(sql,
				new Board_noticeRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	
	
	public Board_Notice upNotice(Board_Notice model) {
		String sql = "select * from board_notice where board_id > ? order by board_id limit 1";
		try {
			return this.jdbcTemplate.queryForObject(sql,
					new Board_noticeRowMapper(), 
					model.getBoard_id());
		} catch (Exception e) {
			return null;
		}
	}
	
	public Board_Notice downNotice(Board_Notice model) {
		String sql = "select * from board_notice where board_id < ? order by board_id desc limit 1";
		try {
			return this.jdbcTemplate.queryForObject(sql,
					new Board_noticeRowMapper(), 
					model.getBoard_id());
		} catch (Exception e) {
			return null;
		}
	}
	
	public int[] batchDelete(List<BoardsJosnModel> model) {
		return jdbcTemplate.batchUpdate("delete from board_notice where board_id=?",
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
}
