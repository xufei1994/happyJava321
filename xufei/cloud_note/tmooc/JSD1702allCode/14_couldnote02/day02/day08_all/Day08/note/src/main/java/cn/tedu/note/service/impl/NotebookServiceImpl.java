package cn.tedu.note.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.note.dao.NotebookDao;
import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;
import cn.tedu.note.service.NotebookService;
import cn.tedu.note.service.UserNotFoundException;

@Service("notebookService")
public class NotebookServiceImpl implements NotebookService {
	
	@Resource
	private NotebookDao notebookDao;
	
	@Resource 
	private UserDao userDao;
	
	public List<Map<String, Object>> 
		listNotebooks(String userId) 
		throws UserNotFoundException {
		if(userId==null || userId.trim().isEmpty()){
			throw new UserNotFoundException("ID不能空");
		}
		User user = userDao.findUserById(userId);
		if(user==null){
			throw new UserNotFoundException("用户不存在");
		}
		return notebookDao
				.findNotebooksByUserId(userId);
	}

}

