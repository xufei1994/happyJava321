package cn.tedu.note.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.note.dao.UserDao;
import cn.tedu.note.entity.User;

public class UserDaoTest {
	
	ClassPathXmlApplicationContext ctx;
	@Before 
	public void initCtx(){
		ctx = new ClassPathXmlApplicationContext(
				"conf/spring-mvc.xml",
				"conf/spring-mybatis.xml");
	}

	@After
	public void closeCtx(){
		ctx.close();
	}
	
	@Test
	public void testFindUserByName(){
		String name = "demo";
		UserDao dao = ctx.getBean(
			"userDao", UserDao.class);
		User user = dao.findUserByName(name);
		System.out.println(user); 
	}
}





