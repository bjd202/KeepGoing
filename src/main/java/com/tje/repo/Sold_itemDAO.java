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
public class Sold_itemDAO {
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public Sold_itemDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class Sold_itemRowMapper implements RowMapper<Sold_item>{
		@Override
		public Sold_item mapRow(ResultSet rs, int rowNum) throws SQLException {
			Sold_item Sold_item=new Sold_item(
					rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7),
					rs.getString(8),
					rs.getString(9),
					rs.getInt(10),
					rs.getString(11),
					rs.getDate(12));
			return Sold_item;
		}
	}
	
	public Sold_item selectOne(Sold_item model) {
		String sql = "select * from Sold_item where sold_item_id=?";
		return this.jdbcTemplate.queryForObject(sql, 
				new Sold_itemRowMapper(), 
				model.getSold_item_id());
	}
	
	public List<Sold_item> selectAll() {
		String sql = "select * from Sold_item";
		List<Sold_item> results=this.jdbcTemplate.query(sql,
				new Sold_itemRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public int insert(Sold_item model) {

		return this.jdbcTemplate.update("insert into sold_item values(0,?,?,?,?,?,?,?,?,?,?,now())",
				model.getBoard_id(),
				model.getCategory(),
				model.getMember_id(),
				model.getName(),
				model.getAddress_post(),
				model.getAddress_basic(),
				model.getAddress_detail(),
				model.getTitle(),
				model.getNumber(),
				model.getPrice());
	}
	
	public int[] batchInsert(List<Sold_item> items) {
		return jdbcTemplate.batchUpdate("insert into sold_item values(0,?,?,?,?,?,?,?,?,?,?,now())", 
				new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						// TODO Auto-generated method stub
						ps.setInt(1, items.get(i).getBoard_id());
						ps.setInt(2, items.get(i).getCategory());
						ps.setString(3, items.get(i).getMember_id());
						ps.setString(4, items.get(i).getName());
						ps.setString(5, items.get(i).getAddress_post());
						ps.setString(6, items.get(i).getAddress_basic());
						ps.setString(7, items.get(i).getAddress_detail());
						ps.setString(8, items.get(i).getTitle());
						ps.setInt(9, items.get(i).getNumber());
						ps.setString(10, items.get(i).getPrice());
					}
					
					@Override
					public int getBatchSize() {
						return items.size();
					}
			});
	}
	
	public List<Sold_item> select_search(HashMap<String, Object> model) {
		String sql = "select * from Sold_item where member_id=? and sold_date between ? and ?";
		List<Sold_item> results=this.jdbcTemplate.query(sql, 
				new Sold_itemRowMapper(), 
				model.get("member_id"),
				model.get("from"),
				model.get("to"));
		
		return results.isEmpty() ? null : results;
	}
}
