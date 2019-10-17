package com.tje.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.repo.*;

@Service
public class SimpleBoardFreeViewSelectByDateDescService {
	@Autowired
	private SimpleBoardFreeViewDAO simpleBoardFreeViewDAO;
	
	public Object service() {
		Object result=null;
		result=simpleBoardFreeViewDAO.selectAllOrdByDateDesc();
		return result;
	}
}
