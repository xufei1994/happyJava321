package cn.tedu.note.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.note.dao.NotebookDao;
import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;
import cn.tedu.note.service.NotebookService;
import cn.tedu.note.service.UserNotFoundException;

@Service("notebookService")
@Transactional
public class NotebookServiceImpl implements NotebookService {
	
	@Resource
	private NotebookDao notebookDao;
	
	@Resource 
	private UserDao userDao;
	
	@Value("#{jdbc.pageSize}")
	private int pageSize;
	
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
	
	public List<Map<String, Object>> 
		listNotebooks(
		String userId, Integer page) 
		throws UserNotFoundException {
		if(userId==null || userId.trim().isEmpty()){
			throw new UserNotFoundException("ID不能空");
		}
		User user = userDao.findUserById(userId);
		if(user==null){
			throw new UserNotFoundException("用户不存在");
		}
		if(page==null){
			page = 0;
		}
		int start = page * pageSize;
		String table = "cn_notebook";
		return notebookDao.findNotebooksByPage(
				userId, start, pageSize, table);
	}
}

