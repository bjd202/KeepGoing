package com.tje.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.Comment;
import com.tje.repo.CommentDAO;

@Service
public class CommentDeleteService {
	@Autowired
	private CommentDAO commentDAO;
	
	public Object service(Object args) {
		Object result=null;
		
		result=commentDAO.delete((Comment)args);
		
		return result;
	}
}
