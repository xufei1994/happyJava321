package cn.tedu.note.service;

import cn.tedu.note.entity.User;

/**
 * 业务层接口 
 */
public interface UserService {
	/**
	 * 登录功能, 登录成功返回用户信息, 登录失败
	 * 则抛出异常.
	 * @param name 用户名 
	 * @param password 密码
	 * @return 如果登录成功就返回登录用户信息
	 * @throws UserNotFoundException 用户不存在
	 * @throws PasswordException 密码错误
	 */
	User login(String name, String password)
		throws UserNotFoundException,
		PasswordException;
	/**
	 * UserService 中添加注册功能
	 * @param name 
	 * @param nick
	 * @param password
	 * @param confirm
	 * @return 注册成功的用户信息
	 * @throws UserNameException 用户名异常
	 * @throws PasswordException 密码异常
	 */
	User regist(String name, String nick, 
			String password, String confirm)
		throws UserNameException, 
		PasswordException;
}






