package com.tje.repo;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tje.model.*;

@Repository
public class Review_MapDAO {
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public Review_MapDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class Review_MapRowMapper implements RowMapper<Review_Map>{
		@Override
		public Review_Map mapRow(ResultSet rs, int rowNum) throws SQLException {
			Review_Map review_Map=new Review_Map(
					rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getString(4),
					rs.getDouble(5),
					rs.getDouble(6));
			return review_Map;
		}
	}
	
	public int delete(Review_Map model) throws Exception {
		return this.jdbcTemplate.update("delete from review_map where board_id = ?",
				model.getBoard_id());
	}
	
	public Review_Map selectOne(Review_Map model) {
		String sql = "select * from Review_Map where board_id = ?";
		return this.jdbcTemplate.queryForObject(sql, new Review_MapRowMapper(), model.getBoard_id());
	}
	
	public int insert(Review_Map model) throws Exception {
		return this.jdbcTemplate.update("insert into Review_Map values(0,1,?,?,?,?)",
				model.getBoard_id(),
				model.getSelectedAddress(),
				model.getSelectedLat(),
				model.getSelectedLng());
	}
	
	public int update(Review_Map model) {
		return this.jdbcTemplate.update("update Review_Map set selectedAddress = ?, selectedLat = ?, selectedLng = ? where board_id = ?)",
				model.getSelectedAddress(),
				model.getSelectedLat(),
				model.getSelectedLng(),
				model.getBoard_id());
	}
	
}
