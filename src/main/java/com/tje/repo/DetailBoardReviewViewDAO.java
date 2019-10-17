package com.tje.repo;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tje.model.*;
import com.tje.repo.*;

@Repository
public class DetailBoardReviewViewDAO {
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public DetailBoardReviewViewDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class DetailBoardReviewViewRowMapper implements RowMapper<DetailBoardReviewView>{
		@Override
		public DetailBoardReviewView mapRow(ResultSet rs, int rowNum) throws SQLException {
			DetailBoardReviewView detailBoardReviewView=new DetailBoardReviewView(
					rs.getInt(1),
					rs.getInt(2),
					rs.getString(3),
					rs.getInt(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getString(8),
					rs.getInt(9),
					rs.getInt(10),
					rs.getInt(11),
					rs.getDate(12),
					rs.getString(13),
					rs.getDouble(14),
					rs.getDouble(15));
			return detailBoardReviewView;
		}
	}
	
	public int update_view_cnt(DetailBoardReviewView model) {
		String sql = "update board_review set view_cnt=view_cnt+1 where board_id=?";
		
		return this.jdbcTemplate.update(sql,
				model.getBoard_id());
	}
	
	public DetailBoardReviewView selectOne(DetailBoardReviewView model) throws Exception{
		String sql = "select * from DetailBoardReviewView where board_id=?";
		return this.jdbcTemplate.queryForObject(sql, 
				new DetailBoardReviewViewRowMapper(), 
				model.getBoard_id());
	}
	
}
