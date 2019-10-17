package com.tje.repo;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tje.model.*;
import com.tje.repo.SimpleBoardReviewViewDAO.SimpleBoardReviewViewRowMapper;

import java.util.*;

@Repository
public class SimpleBoardItemViewDAO {
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public SimpleBoardItemViewDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class SimpleBoardItemViewRowMapper implements RowMapper<SimpleBoardItemView>{
		@Override
		public SimpleBoardItemView mapRow(ResultSet rs, int rowNum) throws SQLException {
			SimpleBoardItemView simpleBoardItemView=new SimpleBoardItemView(
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
			return simpleBoardItemView;
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////
public List<SimpleBoardItemView> selectOrderByLikeCount() throws Exception{
String sql = "select * from simpleboarditemview where like_cnt >= 1 order by like_cnt desc limit 10";
return this.jdbcTemplate.query(sql, new SimpleBoardItemViewRowMapper());
}
	
	public SimpleBoardItemView selectOne(SimpleBoardItemView model) {
		String sql = "select * from simpleboarditemview where board_id=?";
		return this.jdbcTemplate.queryForObject(sql, 
				new SimpleBoardItemViewRowMapper(), 
				model.getBoard_id());
	}
	
	public List<SimpleBoardItemView> selectAll() {
		String sql = "select * from simpleboarditemview";
		List<SimpleBoardItemView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardItemViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardItemView> listCriteria(int pageStart, int perPageNum) {
		String sql = "select * from simpleboarditemview where board_id>0 order by board_id desc limit ?,?";
		List<SimpleBoardItemView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardItemViewRowMapper(),
				pageStart,
				perPageNum);
		return results.isEmpty() ? null : results;
	}
	
	public int listCountCriteria() {
		String sql = "select count(*) from simpleboarditemview";
		return this.jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public List<SimpleBoardItemView> selectAllOrdByDateDesc() {
		String sql = "select * from simpleboarditemview order by write_date desc";
		List<SimpleBoardItemView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardItemViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardItemView> selectAllOrdByCmtCntDesc() {
		String sql = "select * from simpleboarditemview order by comment_cnt desc";
		List<SimpleBoardItemView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardItemViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardItemView> selectAllOrdByLikeCntDesc() {
		String sql = "select * from simpleboarditemview order by like_cnt desc";
		List<SimpleBoardItemView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardItemViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardItemView> selectAllOrdByDisLikeCntDesc() {
		String sql = "select * from simpleboarditemview order by dislike_cnt desc";
		List<SimpleBoardItemView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardItemViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	
	public List<SimpleBoardItemView> selectAllOrdByDateAsc() {
		String sql = "select * from simpleboarditemview order by write_date asc";
		List<SimpleBoardItemView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardItemViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardItemView> selectAllOrdByCmtCntAsc() {
		String sql = "select * from simpleboarditemview order by comment_cnt asc";
		List<SimpleBoardItemView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardItemViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardItemView> selectAllOrdByLikeCntAsc() {
		String sql = "select * from simpleboarditemview order by like_cnt asc";
		List<SimpleBoardItemView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardItemViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardItemView> selectAllOrdByDisLikeCntAsc() {
		String sql = "select * from simpleboarditemview order by dislike_cnt asc";
		List<SimpleBoardItemView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardItemViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
}
