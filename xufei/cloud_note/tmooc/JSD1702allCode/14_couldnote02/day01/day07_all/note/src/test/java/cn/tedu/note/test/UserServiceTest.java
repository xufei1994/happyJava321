package cn.tedu.note.test;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.note.entity.User;
import cn.tedu.note.service.UserService;

public class UserServiceTest extends BaseTest {
	UserService service;
	@Before
	public void initService(){
		service = ctx.getBean("userService",
				UserService.class);
	}
	
	@Test
	public void testRegist(){
		User user = service.regist(
				"Andy", "Andy", "123456", 
				"123456");
		System.out.println(user); 
	}
	
	@Test
	public void testLogin(){
		String name = "demo";
		String password = "123456";
		User user = service.login(
			name, password);
		System.out.println(user); 
	}
}




