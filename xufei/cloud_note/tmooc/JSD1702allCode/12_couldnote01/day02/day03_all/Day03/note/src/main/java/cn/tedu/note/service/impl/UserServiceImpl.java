package cn.tedu.note.service.impl;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;
import cn.tedu.note.service.PasswordException;
import cn.tedu.note.service.UserNotFoundException;
import cn.tedu.note.service.UserService;


@Service("userService")
public class UserServiceImpl 
	implements UserService {
	
	@Resource
	private UserDao userDao;
	
	public User login(String name, String password) 
			throws UserNotFoundException, 
			PasswordException {
		if(password==null ||
				password.trim().isEmpty()){
			throw new PasswordException("密码空");
		}
		if(name==null || name.trim().isEmpty()){
			throw new UserNotFoundException("用户名空");
		}
		User user = userDao.findUserByName(
				name.trim());
		if(user==null){
			throw new UserNotFoundException("name错误");
		}
		String salt="今天你吃了吗?";
		String pwd = DigestUtils.md5Hex(
				salt+password.trim());
		if(pwd.equals(user.getPassword())){
			return user;
		}
		throw new PasswordException("密码错误");
	}
}



