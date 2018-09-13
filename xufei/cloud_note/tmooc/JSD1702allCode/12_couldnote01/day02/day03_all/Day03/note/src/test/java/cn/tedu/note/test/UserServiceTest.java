package cn.tedu.note.test;

import org.junit.Test;

import cn.tedu.note.entity.User;
import cn.tedu.note.service.UserService;

public class UserServiceTest extends BaseTest {
	
	@Test
	public void testLogin(){
		String name = "demo";
		String password = "123456";
		UserService service = 
			ctx.getBean("userService",
			UserService.class);
		User user = service.login(
			name, password);
		System.out.println(user); 
	}
}




