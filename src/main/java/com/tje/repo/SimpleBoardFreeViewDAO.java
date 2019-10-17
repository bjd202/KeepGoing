package com.tje.repo;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.tje.model.*;
import com.tje.repo.DetailBoardFreeViewDAO.DetailBoardFreeViewRowMapper;
import com.tje.repo.SimpleBoardFreeViewDAO.SimpleBoardFreeViewRowMapper;

import java.util.*;

@Repository
public class SimpleBoardFreeViewDAO {
private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public SimpleBoardFreeViewDAO(DataSource dataSource) {
		this.jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	class SimpleBoardFreeViewRowMapper implements RowMapper<SimpleBoardFreeView>{
		@Override
		public SimpleBoardFreeView mapRow(ResultSet rs, int rowNum) throws SQLException {
			SimpleBoardFreeView simpleBoardFreeView=new SimpleBoardFreeView(
					rs.getInt(1),
					rs.getInt(2),
					rs.getString(3),
					rs.getInt(4),
					rs.getString(5),
					rs.getString(6),
					rs.getInt(7),
					rs.getInt(8),
					rs.getInt(9),
					rs.getDate(10));
			return simpleBoardFreeView;
		}
	}

	
	public SimpleBoardFreeView selectOne(SimpleBoardFreeView model) {
		String sql = "select * from SimpleBoardFreeView where board_id=?";
		return this.jdbcTemplate.queryForObject(sql,   // 결과 값이 하나 일때 queryForObject 사용
				new SimpleBoardFreeViewRowMapper(), 
				model.getBoard_id());
	}
	
	public List<SimpleBoardFreeView> selectAllOrdByDateDesc() {
		String sql = "select * from SimpleBoardFreeView order by write_date desc";
		List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardFreeViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardFreeView> selectAllOrdByCmtCntDesc() {
		String sql = "select * from SimpleBoardFreeView order by comment_cnt desc";
		List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardFreeViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardFreeView> selectAllOrdByLikeCntDesc() {
		String sql = "select * from SimpleBoardFreeView order by like_cnt desc";
		List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardFreeViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardFreeView> selectAllOrdByDisLikeCntDesc() {
		String sql = "select * from SimpleBoardFreeView order by dislike_cnt desc";
		List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardFreeViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	
	public List<SimpleBoardFreeView> selectAllOrdByDateAsc() {
		String sql = "select * from SimpleBoardFreeView order by write_date asc";
		List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardFreeViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
//////////////// 전체 게시글 ///////////////
	public int listCountCriteria() {
		String sql = "select count(*) from simpleboardfreeview";
		return this.jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	
	
	public List<SimpleBoardFreeView> listCriteria(int pageStart, int perPageNum) {
		// desc내림차순  limit 한도  order주문 by으로?
		String sql = "select * from simpleboardfreeview where board_id >0 order by board_id desc limit ?,?";
		List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardFreeViewRowMapper(),
				pageStart,
				perPageNum);
		return results.isEmpty() ? null : results;
	}
	
//////////////// 전체 게시글 ///////////////	
//
//
// 
////////////////2 우리동네 운동부 게시글 HometownMotionPart///////e/
		public int HometownMotionPart_listCountCriteria() {
			String sql = "select count(*) from simpleboardfreeview where category = 2";
			return this.jdbcTemplate.queryForObject(sql, Integer.class);
		}
		
		
		
		public List<SimpleBoardFreeView> HometownMotionPart_listCriteria(int pageStart, int perPageNum) {
			// desc내림차순  limit 한도  order주문 by으로?
			String sql = "select * from simpleboardfreeview where board_id >0 and category = 2 order by board_id desc limit ?,?";
			List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
					new SimpleBoardFreeViewRowMapper(),
					pageStart,
					perPageNum);
			return results.isEmpty() ? null : results;
		}		
////////////////2 우리동네 운동부 게시글 HometownMotionPart ////////e/
//
//
// 
////////////////3 건강한 식생활 게시글  healthy_diet ////////e/
		
		
		public int Healthy_Diet_ListCountCriteria() {
			String sql = "select count(*) from simpleboardfreeview where category = 3";
			return this.jdbcTemplate.queryForObject(sql, Integer.class);
		}
		
		
		
		public List<SimpleBoardFreeView> Healthy_Diet_ListCriteria(int pageStart, int perPageNum) {
			// desc내림차순  limit 한도  order주문 by으로?
			String sql = "select * from simpleboardfreeview where board_id >0 and category = 3 order by board_id desc limit ?,?";
			List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
					new SimpleBoardFreeViewRowMapper(),
					pageStart,
					perPageNum);
			return results.isEmpty() ? null : results;
		}
				
				
////////////////3 건강한 식생활 게시글  healthy_diet ////////e/		
//
//
// 
////////////////4 나만의 운동법  게시글 My_own_exercise ////////e/				

		public int My_Own_Exercise_ListCountCriteria() {
			String sql = "select count(*) from simpleboardfreeview where category = 4";
			return this.jdbcTemplate.queryForObject(sql, Integer.class);
		}
		
		
		
		public List<SimpleBoardFreeView> My_Own_Exercise_ListCriteria(int pageStart, int perPageNum) {
			// desc내림차순  limit 한도  order주문 by으로?
			String sql = "select * from simpleboardfreeview where board_id >0 and category = 4 order by board_id desc limit ?,?";
			List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
					new SimpleBoardFreeViewRowMapper(),
					pageStart,
					perPageNum);
			return results.isEmpty() ? null : results;
		}
				
////////////////4 나만의 운동법  게시글 My_own_exercise ////////e/					
//
//
// 
////////////////5 초보자를 위한 운동 추천 게시글 Beginner_exercise ////////e/		

		public int Beginner_Exercise_ListCountCriteria() {
			String sql = "select count(*) from simpleboardfreeview where category = 5";
			return this.jdbcTemplate.queryForObject(sql, Integer.class);
		}
		
		
		public List<SimpleBoardFreeView> Beginner_Exercise_listCriteria(int pageStart, int perPageNum) {
			// desc내림차순  limit 한도  order주문 by으로?
			String sql = "select * from simpleboardfreeview where board_id >0 and category = 5 order by board_id desc limit ?,?";
			List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
					new SimpleBoardFreeViewRowMapper(),
					pageStart,
					perPageNum);
			return results.isEmpty() ? null : results;
		}
		
		
////////////////5 초보자를 위한 운동 추천 게시글 Beginner_exercise /////////
//
//
// 
////////////////6 콤플랙스 극복 게시글 Complex ////////e/				

		public int Complex_ListCountCriteria() {
			String sql = "select count(*) from simpleboardfreeview where category = 6";
			return this.jdbcTemplate.queryForObject(sql, Integer.class);
		}
		
		
		
		public List<SimpleBoardFreeView> Complex_ListCriteria(int pageStart, int perPageNum) {
			// desc내림차순  limit 한도  order주문 by으로?
			String sql = "select * from simpleboardfreeview where board_id >0 and category = 6 order by board_id desc limit ?,?";
			List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
					new SimpleBoardFreeViewRowMapper(),
					pageStart,
					perPageNum);
			return results.isEmpty() ? null : results;
		}
		
		
////////////////6 콤플랙스 극복 게시글 Complex  /////////
//	
//
//
//////////////// 댓글 카운트, 좋아요 싫어요 		//////////	
	public List<SimpleBoardFreeView> selectAllOrdByCmtCntAsc() {
		String sql = "select * from SimpleBoardFreeView order by comment_cnt asc";
		List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardFreeViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardFreeView> selectAllOrdByLikeCntAsc() {
		String sql = "select * from SimpleBoardFreeView order by like_cnt asc";
		List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardFreeViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
	public List<SimpleBoardFreeView> selectAllOrdByDisLikeCntAsc() {
		String sql = "select * from SimpleBoardFreeView order by dislike_cnt asc";
		List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
				new SimpleBoardFreeViewRowMapper());
		return results.isEmpty() ? null : results;
	}
	
//////////////// 댓글 카운트 , 좋아요 싫어요 	//////////
//
//
//
//////////////// 검색창 (전체,제목,내용,제목+내용,글쓴이) //////////
	// 검색 창 sql 검색식
		public int searchFreeWriterCount(int category_Num, String keyword) {
			String sql;
			if(category_Num == 1) {
				sql = "select count(*) from SimpleBoardFreeView where board_id>0 and nickname like ?";
				return this.jdbcTemplate.queryForObject(sql, Integer.class, "%"+keyword+"%");
			} else {
				sql = "select count(*) from SimpleBoardFreeView where board_id>0 and category=? and nickname like ?";
				return this.jdbcTemplate.queryForObject(sql, Integer.class, category_Num, "%"+keyword+"%");
			}
		}
		//
		public List<SimpleBoardFreeView> searchFreeWriter(int category_Num, String keyword, int pageStart, int perPageNum) {
			String sql;
			List<SimpleBoardFreeView> results;
			if(category_Num == 1) {
				sql = "select * from SimpleBoardFreeView where board_id>0 and nickname like ? order by board_id desc limit ?,?";
				results=this.jdbcTemplate.query(sql, new SimpleBoardFreeViewRowMapper(), "%"+keyword+"%", pageStart, perPageNum);
			} else {
				sql = "select * from SimpleBoardFreeView where board_id>0 and category=? and nickname like ? order by board_id desc limit ?,?";
				results=this.jdbcTemplate.query(sql, new SimpleBoardFreeViewRowMapper(), category_Num, "%"+keyword+"%", pageStart, perPageNum);
			}
			return results.isEmpty() ? null : results;
		}
		
	
	
		
		public int searchFreeTitleCount(int category_Num, String keyword) {
			String sql;
			if(category_Num == 1) {
				sql = "select count(*) from SimpleBoardFreeView where board_id>0 and title like ?";
				return this.jdbcTemplate.queryForObject(sql, Integer.class, "%"+keyword+"%");
			} else {
				sql = "select count(*) from SimpleBoardFreeView where board_id>0 and category=? and title like ?";
				return this.jdbcTemplate.queryForObject(sql, Integer.class, category_Num, "%"+keyword+"%");
			}
		}
		
		public List<SimpleBoardFreeView> searchFreeTitle(int category_Num, String keyword, int pageStart, int perPageNum) {
			String sql;
			List<SimpleBoardFreeView> results;
			if(category_Num == 1) {
				sql = "select * from SimpleBoardFreeView where board_id>0 and title like ? order by board_id desc limit ?,?";
				results=this.jdbcTemplate.query(sql, new SimpleBoardFreeViewRowMapper(), "%"+keyword+"%", pageStart, perPageNum);
			} else {
				sql = "select * from SimpleBoardFreeView where board_id>0 and category=? and title like ? order by board_id desc limit ?,?";
				results=this.jdbcTemplate.query(sql, new SimpleBoardFreeViewRowMapper(), category_Num, "%"+keyword+"%", pageStart, perPageNum);
			}
			return results.isEmpty() ? null : results;
		}
		
		//e//
		public int searchFreeAllCount(int category_Num, String keyword) {
			String sql;
			if(category_Num == 1) {
				sql = "select count(*) from SimpleBoardFreeView where board_id>0 and ( title like ? or nickname like ?)";
				return this.jdbcTemplate.queryForObject(sql, Integer.class,  "%"+keyword+"%", "%"+keyword+"%");
			} else {
				sql = "select count(*) from SimpleBoardFreeView where board_id>0 and category = ? and ( title like ? or nickname like ?)";
				return this.jdbcTemplate.queryForObject(sql, Integer.class, category_Num,  "%"+keyword+"%", "%"+keyword+"%");
			}
		}
		//e//
		public List<SimpleBoardFreeView> searchFreeAll(int category_Num, String keyword, int pageStart, int perPageNum) {
			String sql;
			List<SimpleBoardFreeView> results;
			if(category_Num == 1) {
				sql = "select * from SimpleBoardFreeView where board_id>0 and (title like ? or nickname like ?) order by board_id desc limit ?,?";
				results=this.jdbcTemplate.query(sql, new SimpleBoardFreeViewRowMapper(), "%"+keyword+"%", "%"+keyword+"%", pageStart, perPageNum);
			} else {
				sql = "select * from SimpleBoardFreeView where (board_id>0 and category = ?) and ( title like ? or nickname like ?) order by board_id desc limit ?,?";
				results=this.jdbcTemplate.query(sql, new SimpleBoardFreeViewRowMapper(), category_Num,  "%"+keyword+"%", "%"+keyword+"%", pageStart, perPageNum);
			}
			return results.isEmpty() ? null : results;
		}


////////////////검색창 (전체,제목,내용,제목+내용,글쓴이) //////////
		
		public List<SimpleBoardFreeView> selectAll() {
			String sql = "select * from SimpleBoardFreeView";
			
			List<SimpleBoardFreeView> results=this.jdbcTemplate.query(sql,
					new SimpleBoardFreeViewRowMapper());
			
			return results.isEmpty() ? null : results;
		}
	
}































