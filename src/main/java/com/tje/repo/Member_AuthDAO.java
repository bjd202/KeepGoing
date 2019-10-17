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

import com.tje.model.Member_Auth;

@Repository
public class Member_AuthDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public Member_AuthDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class Member_AuthRowMapper implements RowMapper<Member_Auth>{

		@Override
		public Member_Auth mapRow(ResultSet rs, int rowNum) throws SQLException {
			Member_Auth Member_Auth=new Member_Auth(
					rs.getString(1),
					rs.getString(2),
					rs.getString(3),
					rs.getInt(4),
					rs.getInt(5),
					rs.getInt(6));
			return Member_Auth;
		}
	}
	
	public Member_Auth selectOne(Member_Auth model) {
		return this.jdbcTemplate.queryForObject("select * from Member_Auth where Member_Auth_id=?", 
				new Member_AuthRowMapper(), 
				model.getMember_id());
	}
	
	public Member_Auth selectOneNickName(Member_Auth model) {
		try {
			return this.jdbcTemplate.queryForObject("select * from Member_Auth where nickname=?", 
					new Member_AuthRowMapper(), 
					model.getNickname());
		}catch (Exception e) {
			return null;
		}
	}
	
	public List<Member_Auth> selectAll() {
		List<Member_Auth> results=this.jdbcTemplate.query("select * from Member_Auth",
				new Member_AuthRowMapper());
		
		return results.isEmpty() ? null : results;
	}
	
	public int insert(Member_Auth model) {
		
		return this.jdbcTemplate.update("insert into Member_Auth values(?,?,?,?,?,?)",
				model.getMember_id(),
				model.getName(),
				model.getNickname(),
				model.getCur_auth(),
				model.getReq_auth(),
				model.getMember_type());
	}
	
	public int[] batchDelete(List<Member_Auth> members) {
		return jdbcTemplate.batchUpdate("delete from Member_Auth where nickname=?",
				new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						// TODO Auto-generated method stub
						ps.setString(1, members.get(i).getNickname());
					}
					
					@Override
					public int getBatchSize() {
						return members.size();
					}
			});
	}
}
