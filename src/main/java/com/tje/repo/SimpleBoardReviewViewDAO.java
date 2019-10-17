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
public class SimpleBoardReviewViewDAO {
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public SimpleBoardReviewViewDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class SimpleBoardReviewViewRowMapper implements RowMapper<SimpleBoardReviewView>{
		@Override
		public SimpleBoardReviewView mapRow(ResultSet rs, int rowNum) throws SQLException {
			SimpleBoardReviewView simpleBoardReviewView=new SimpleBoardReviewView(
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
					rs.getDate(12));
			return simpleBoardReviewView;
		}
	}
	///////////////////////////////////////////////////////////////////////////////////////////////
	public List<SimpleBoardReviewView> selectOrderByLikeCount() throws Exception{
		String sql = "select * from simpleboardreviewview where like_cnt >=1 order by like_cnt desc limit 10";
		//String sql = "select board_id, category, title, comment_cnt, image, content, member_id, nickname, view_cnt, max(like_cnt) as like_cnt, dislike_cnt, write_date from simpleboardreviewview group by category order by like_cnt desc";
		return this.jdbcTemplate.query(sql, new SimpleBoardReviewViewRowMapper());
	}
	
	// 검색 창 sql 검색식
	public int searchReviewWriterCount(int category_Num, String keyword) {
		String sql;
		if(category_Num == 1) {
			sql = "select count(*) from simpleboardreviewview where board_id>0 and nickname like ?";
			return this.jdbcTemplate.queryForObject(sql, Integer.class, "%"+keyword+"%");
		} else {
			sql = "select count(*) from simpleboardreviewview where board_id>0 and category=? and nickname like ?";
			return this.jdbcTemplate.queryForObject(sql, Integer.class, category_Num, "%"+keyword+"%");
		}
	}
	
	public List<SimpleBoardReviewView> searchReviewWriter(int category_Num, String keyword, int pageStart, int perPageNum) {
		String sql;
		List<SimpleBoardReviewView> results;
		if(category_Num == 1) {
			sql = "select * from simpleboardreviewview where board_id>0 and nickname like ? order by board_id desc limit ?,?";
			results=this.jdbcTemplate.query(sql, new SimpleBoardReviewViewRowMapper(), "%"+keyword+"%", pageStart, perPageNum);
		} else {
			sql = "select * from simpleboardreviewview where board_id>0 and category=? and nickname like ? order by board_id desc limit ?,?";
			results=this.jdbcTemplate.query(sql, new SimpleBoardReviewViewRowMapper(), category_Num, "%"+keyword+"%", pageStart, perPageNum);
		}
		return results.isEmpty() ? null : results;
	}
	
	public int searchReviewTitleAndContentCount(int category_Num, String keyword) {
		String sql;
		if(category_Num == 1) {
			sql = "select count(*) from simpleboardreviewview where (board_id>0) and (title like ? or content like ?)";
			return this.jdbcTemplate.queryForObject(sql, Integer.class, "%"+keyword+"%", "%"+keyword+"%");
		} else {
			sql = "select count(*) from simpleboardreviewview where (board_id>0 and category=?) and (title like ? or content like ?)";
			return this.jdbcTemplate.queryForObject(sql, Integer.class, category_Num, "%"+keyword+"%", "%"+keyword+"%");
		}
	}
	
	public List<SimpleBoardReviewView> searchReviewTitleAndContent(int category_Num, String keyword, int pageStart, int perPageNum) {
		String sql;
		List<SimpleBoardReviewView> results;
		if(category_Num == 1) {
			sql = "select * from simpleboardreviewview where (board_id>0) and (title like ? or content like ?) order by board_id desc limit ?,?";
			results=this.jdbcTemplate.query(sql, new SimpleBoardReviewViewRowMapper(), "%"+keyword+"%", "%"+keyword+"%", pageStart, perPageNum);
		} else {
			sql = "select * from simpleboardreviewview where (board_id>0 and category=?) and (title like ? or content like ?) order by board_id desc limit ?,?";
			results=this.jdbcTemplate.query(sql, new SimpleBoardReviewViewRowMapper(), category_Num, "%"+keyword+"%", "%"+keyword+"%", pageStart, perPageNum);
		}
		return results.isEmpty() ? null : results;
	}
	
	public int searchReviewContentCount(int category_Num, String keyword) {
		String sql;
		if(category_Num == 1) {
			sql = "select count(*) from simpleboardreviewview where board_id>0 and content like ?";
			return this.jdbcTemplate.queryForObject(sql, Integer.class, "%"+keyword+"%");
		} else {
			sql = "select count(*) from simpleboardreviewview where board_id>0 and category=? and content like ?";
			return this.jdbcTemplate.queryForObject(sql, Integer.class, category_Num, "%"+keyword+"%");
		}
	}
	
	public List<SimpleBoardReviewView> searchReviewContent(int category_Num, String keyword, int pageStart, int perPageNum) {
		String sql;
		List<SimpleBoardReviewView> results;
		if(category_Num == 1) {
			sql = "select * from simpleboardreviewview where board_id>0 and category=? and content like ? order by board_id desc limit ?,?";
			results=this.jdbcTemplate.query(sql, new SimpleBoardReviewViewRowMapper(), "%"+keyword+"%", pageStart, perPageNum);
		} else {
			sql = "select * from simpleboardreviewview where board_id>0 and category=? and content like ? order by board_id desc limit ?,?";
			results=this.jdbcTemplate.query(sql, new SimpleBoardReviewViewRowMapper(), category_Num, "%"+keyword+"%", pageStart, perPageNum);
		}
		return results.isEmpty() ? null : results;
	}
	
	public int searchReviewTitleCount(int category_Num, String keyword) {
		String sql;
		if(category_Num == 1) {
			sql = "select count(*) from simpleboardreviewview where board_id>0 and title like ?";
			return this.jdbcTemplate.queryForObject(sql, Integer.class, "%"+keyword+"%");
		} else {
			sql = "select count(*) from simpleboardreviewview where board_id>0 and category=? and title like ?";
			return this.jdbcTemplate.queryForObject(sql, Integer.class, category_Num, "%"+keyword+"%");
		}
	}
	
	public List<SimpleBoardReviewView> searchReviewTitle(int category_Num, String keyword, int pageStart, int perPageNum) {
		String sql;
		List<SimpleBoardReviewView> results;
		if(category_Num == 1) {
			sql = "select * from simpleboardreviewview where board_id>0 and title like ? order by board_id desc limit ?,?";
			results=this.jdbcTemplate.query(sql, new SimpleBoardReviewViewRowMapper(), "%"+keyword+"%", pageStart, perPageNum);
		} else {
			sql = "select * from simpleboardreviewview where board_id>0 and category=? and title like ? order by board_id desc limit ?,?";
			results=this.jdbcTemplate.query(sql, new SimpleBoardReviewViewRowMapper(), category_Num, "%"+keyword+"%", pageStart, perPageNum);
		}
		return results.isEmpty() ? null : results;
	}
	
	//
	public int searchReviewAllCount(int category_Num, String keyword) {
		String sql;
		if(category_Num == 1) {
			sql = "select count(*) from simpleboardreviewview where board_id>0 and (content like ? or title like ? or nickname like ?)";
			return this.jdbcTemplate.queryForObject(sql, Integer.class, "%"+keyword+"%", "%"+keyword+"%", "%"+keyword+"%");
		} else {
			sql = "select count(*) from simpleboardreviewview where board_id>0 and category = ? and (content like ? or title like ? or nickname like ?)";
			return this.jdbcTemplate.queryForObject(sql, Integer.class, category_Num, "%"+keyword+"%", "%"+keyword+"%", "%"+keyword+"%");
		}
	}
	//
	public List<SimpleBoardReviewView> searchReviewAll(int category_Num, String keyword, int pageStart, int perPageNum) {
		String sql;
		List<SimpleBoardReviewView> results;
		if(category_Num == 1) {
			sql = "select * from simpleboardreviewview where board_id>0 and (content like ? or title like ? or nickname like ?) order by board_id desc limit ?,?";
			results=this.jdbcTemplate.query(sql, new SimpleBoardReviewViewRowMapper(), "%"+keyword+"%", "%"+keyword+"%", "%"+keyword+"%", pageStart, perPageNum);
		} else {
			sql = "select * from simpleboardreviewview where (board_id>0 and category = ?) and (content like ? or title like ? or nickname like ?) order by board_id desc limit ?,?";
			results=this.jdbcTemplate.query(sql, new SimpleBoardReviewViewRowMapper(), category_Num, "%"+keyword+"%", "%"+keyword+"%", "%"+keyword+"%", pageStart, perPageNum);
		}
		return results.isEmpty() ? null : results;
	}
	//
	//
	public List<SimpleBoardReviewView> recipelistCriteria(int pageStart, int perPageNum) {
		String sql = "select * from simpleboardreviewview where board_id>0 and category=7 order by board_id desc limit ?,?";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper(),
				pageStart,
				perPageNum);
		return results.isEmpty() ? null : results;
	}
	
	public int recipelistCountCriteria() {
		String sql = "select count(*) from simpleboardreviewview where category=7";
		return this.jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public List<SimpleBoardReviewView> weightlistCriteria(int pageStart, int perPageNum) {
		String sql = "select * from simpleboardreviewview where category=6 order by board_id desc limit ?,?";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper(),
				pageStart,
				perPageNum);
		return results.isEmpty() ? null : results;
	}
	
	public int weightlistCountCriteria() {
		String sql = "select count(*) from simpleboardreviewview where category=6";
		return this.jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public List<SimpleBoardReviewView> dietlistCriteria(int pageStart, int perPageNum) {
		String sql = "select * from simpleboardreviewview where board_id>0 and category=5 order by board_id desc limit ?,?";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper(),
				pageStart,
				perPageNum);
		return results.isEmpty() ? null : results;
	}
	
	public int dietlistCountCriteria() {
		String sql = "select count(*) from simpleboardreviewview where category=5";
		return this.jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public List<SimpleBoardReviewView> placelistCriteria(int pageStart, int perPageNum) {
		String sql = "select * from simpleboardreviewview where board_id>0 and category=4 order by board_id desc limit ?,?";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper(),
				pageStart,
				perPageNum);
		return results.isEmpty() ? null : results;
	}
	
	public int placelistCountCriteria() {
		String sql = "select count(*) from simpleboardreviewview where category=4";
		return this.jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public List<SimpleBoardReviewView> fitnesslistCriteria(int pageStart, int perPageNum) {
		String sql = "select * from simpleboardreviewview where board_id>0 and category=3 order by board_id desc limit ?,?";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper(),
				pageStart,
				perPageNum);
		return results.isEmpty() ? null : results;
	}
	
	public int fitnesslistCountCriteria() {
		String sql = "select count(*) from simpleboardreviewview where category=3";
		return this.jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public List<SimpleBoardReviewView> itemlistCriteria(int pageStart, int perPageNum) {
		String sql = "select * from simpleboardreviewview where board_id>0 and category=2 order by board_id desc limit ?,?";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper(),
				pageStart,
				perPageNum);
		return results.isEmpty() ? null : results;
	}
	
	public int itemlistCountCriteria() {
		String sql = "select count(*) from simpleboardreviewview where category=2";
		return this.jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public List<SimpleBoardReviewView> listCriteria(int pageStart, int perPageNum) {
		String sql = "select * from simpleboardreviewview where board_id>0 order by board_id desc limit ?,?";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper(),
				pageStart,
				perPageNum);
		return results.isEmpty() ? null : results;
	}
	
	public int listCountCriteria() {
		String sql = "select count(*) from simpleboardreviewview";
		return this.jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public SimpleBoardReviewView selectOne(SimpleBoardReviewView model) throws Exception{
		String sql = "select * from SimpleBoardReviewView where board_id=?";
		return this.jdbcTemplate.queryForObject(sql, 
				new SimpleBoardReviewViewRowMapper(), 
				model.getBoard_id());
	}
	
	public List<SimpleBoardReviewView> selectAllOrdByDateDesc() {
		String sql = "select * from SimpleBoardReviewView order by write_date desc";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardReviewView> selectAllOrdByCmtCntDesc() {
		String sql = "select * from SimpleBoardReviewView order by comment_cnt desc";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardReviewView> selectAllOrdByLikeCntDesc() {
		String sql = "select * from SimpleBoardReviewView order by like_cnt desc";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardReviewView> selectAllOrdByDisLikeCntDesc() {
		String sql = "select * from SimpleBoardReviewView order by dislike_cnt desc";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	
	public List<SimpleBoardReviewView> selectAllOrdByDateAsc() {
		String sql = "select * from SimpleBoardReviewView order by write_date asc";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardReviewView> selectAllOrdByCmtCntAsc() {
		String sql = "select * from SimpleBoardReviewView order by comment_cnt asc";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardReviewView> selectAllOrdByLikeCntAsc() {
		String sql = "select * from SimpleBoardReviewView order by like_cnt asc";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardReviewView> selectAllOrdByDisLikeCntAsc() {
		String sql = "select * from SimpleBoardReviewView order by dislike_cnt asc";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public SimpleBoardReviewView insert(SimpleBoardReviewView model) {
		String sql = "select * from SimpleBoardReviewView where board_id=?";
		return this.jdbcTemplate.queryForObject(sql, 
				new SimpleBoardReviewViewRowMapper(), 
				model.getBoard_id());
	}
	
	public List<SimpleBoardReviewView> selectAll() {
		String sql = "select * from SimpleBoardReviewView";
		List<SimpleBoardReviewView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardReviewViewRowMapper());
		return results.isEmpty() ? null : results;
	}
}
