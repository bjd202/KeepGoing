package com.tje.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.Comment;
import com.tje.repo.CommentDAO;

@Service
public class CommentSelectService {
	@Autowired
	private CommentDAO commentDAO;
	
	public Object service(Object args) {
		Object result=null;
		
		result=commentDAO.select((Comment)args);
		
		return result;
	}
}
