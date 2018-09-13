package cn.tedu.note.dao;

import cn.tedu.note.entity.User;

public interface UserDao {
	
	User findUserByName(String name);
	
	int addUser(User user);

	User findUserById(String userId);
	
	
}
