package cn.tedu.note.test;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;

public class UserDaoTest extends BaseTest{
	UserDao dao;
	@Before
	public void initDao(){
		dao = ctx.getBean(
				"userDao", UserDao.class);
	}
	@Test
	public void testAddUser(){
		String id=UUID.randomUUID().toString();
		String name = "Tom";
		String salt = "今天你吃了吗?";
		String password = 
			DigestUtils.md5Hex(salt+"123456");
		String token = "";
		String nick = "";
		User user = new User(
			id, name, password, token, nick);
		int n = dao.addUser(user);
		System.out.println(n); 
	}
	
	@Test
	public void testFindUserByName(){
		String name = "demo";
		User user = dao.findUserByName(name);
		System.out.println(user); 
	}
	

}





