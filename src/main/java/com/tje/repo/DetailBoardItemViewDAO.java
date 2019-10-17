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
public class DetailBoardItemViewDAO {
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public DetailBoardItemViewDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class DetailBoardItemViewRowMapper implements RowMapper<DetailBoardItemView>{
		@Override
		public DetailBoardItemView mapRow(ResultSet rs, int rowNum) throws SQLException {
			DetailBoardItemView detailBoardItemView=new DetailBoardItemView(
					rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getInt(7),
					rs.getString(8),
					rs.getInt(9),
					rs.getString(10),
					rs.getString(11),
					rs.getInt(12),
					rs.getInt(13),
					rs.getInt(14),
					rs.getDate(15));
			return detailBoardItemView;
		}
	}
	
	public DetailBoardItemView selectOne(DetailBoardItemView model) {
		String sql = "select * from detailboarditemview where board_id=?";
		return this.jdbcTemplate.queryForObject(sql, 
				new DetailBoardItemViewRowMapper(), 
				model.getBoard_id());
	}
	
	public List<DetailBoardItemView> selectAll() {
		String sql = "select * from DetailBoardItemView";
		List<DetailBoardItemView> results=this.jdbcTemplate.query(sql,
				new DetailBoardItemViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public int update(DetailBoardItemView model) {
		String sql = "update board_item set title=?, content=?, category=?, number=?, price=?, image=? where board_id=?";
		
		return this.jdbcTemplate.update(sql,
				model.getTitle(),
				model.getContent(),
				model.getCategory(),
				model.getNumber(),
				model.getPrice(),
				model.getImage(),
				model.getBoard_id());
	}
	
	public int[] batchNumberUpdate(List<DetailBoardItemView> items) {
		return jdbcTemplate.batchUpdate("update board_item set number=number-? where board_id=?",
				new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						// TODO Auto-generated method stub
						ps.setInt(1, items.get(i).getNumber());
						ps.setInt(2, items.get(i).getBoard_id());
					}
					
					@Override
					public int getBatchSize() {
						return items.size();
					}
			});
	}
	
	public int update_view_cnt(DetailBoardItemView model) {
		String sql = "update board_item set view_cnt=view_cnt+1 where board_id=?";
		
		return this.jdbcTemplate.update(sql,
				model.getBoard_id());
	}
	
	public int delete(DetailBoardItemView model) {
		String sql = "delete from board_item where board_id=?";
		
		return this.jdbcTemplate.update(sql,
				model.getBoard_id());
	}
	
	public List<DetailBoardItemView> selectAllOrdByDateDesc() {
		String sql = "select * from DetailBoardItemView order by write_date desc";
		List<DetailBoardItemView> results=this.jdbcTemplate.query(sql,
				new DetailBoardItemViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<DetailBoardItemView> selectAllOrdByCmtCntDesc() {
		String sql = "select * from DetailBoardItemView order by comment_cnt desc";
		List<DetailBoardItemView> results=this.jdbcTemplate.query(sql,
				new DetailBoardItemViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<DetailBoardItemView> selectAllOrdByLikeCntDesc() {
		String sql = "select * from DetailBoardItemView order by like_cnt desc";
		List<DetailBoardItemView> results=this.jdbcTemplate.query(sql,
				new DetailBoardItemViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<DetailBoardItemView> selectAllOrdByDisLikeCntDesc() {
		String sql = "select * from DetailBoardItemView order by dislike_cnt desc";
		List<DetailBoardItemView> results=this.jdbcTemplate.query(sql,
				new DetailBoardItemViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	
	public List<DetailBoardItemView> selectAllOrdByDateAsc() {
		String sql = "select * from DetailBoardItemView order by write_date asc";
		List<DetailBoardItemView> results=this.jdbcTemplate.query(sql,
				new DetailBoardItemViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<DetailBoardItemView> selectAllOrdByCmtCntAsc() {
		String sql = "select * from DetailBoardItemView order by comment_cnt asc";
		List<DetailBoardItemView> results=this.jdbcTemplate.query(sql,
				new DetailBoardItemViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<DetailBoardItemView> selectAllOrdByLikeCntAsc() {
		String sql = "select * from DetailBoardItemView order by like_cnt asc";
		List<DetailBoardItemView> results=this.jdbcTemplate.query(sql,
				new DetailBoardItemViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<DetailBoardItemView> selectAllOrdByDisLikeCntAsc() {
		String sql = "select * from DetailBoardItemView order by dislike_cnt asc";
		List<DetailBoardItemView> results=this.jdbcTemplate.query(sql,
				new DetailBoardItemViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
}
