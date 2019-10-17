package com.tje.repo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tje.model.Cart;
import com.tje.model.Sold_item;

@Repository
public class CartDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public CartDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class CartRowMapper implements RowMapper<Cart>{

		@Override
		public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cart Cart=new Cart(
					rs.getInt(1),
					rs.getInt(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getInt(6),
					rs.getString(7),
					rs.getTimestamp(8));
			
			return Cart;
		}
		
	}
	
	public Cart selectOne(Cart model) {
		String sql = "select * from cart where cart_id=?";
		return this.jdbcTemplate.queryForObject(sql, 
				new CartRowMapper(), 
				model.getCart_id());
	}
	
	public List<Cart> selectAll(Cart model) {
		String sql = "select * from cart where member_id=?";
		List<Cart> list = this.jdbcTemplate.query(sql, 
				new CartRowMapper(),
				model.getBoard_id());
		
		return list.isEmpty() ? null : list;
	}
	
	public int insert(Cart model) {
		
		return this.jdbcTemplate.update("insert into Cart values(0,?,?,?,?,?,?,now())",
				model.getBoard_id(),
				model.getMember_id(),
				model.getImage(),
				model.getTitle(),
				model.getCategory(),
				model.getPrice());
	}
	
	public int delete(Cart model) {
		
		return this.jdbcTemplate.update("delete from cart where cart_id=?",
				model.getCart_id());
	}
	
	public int[] batchDelete(List<Cart> carts) {
		return jdbcTemplate.batchUpdate("delete from cart where cart_id=?",
				new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						// TODO Auto-generated method stub
						ps.setInt(1, carts.get(i).getCart_id());
					}
					
					@Override
					public int getBatchSize() {
						return carts.size();
					}
			});
	}
}
