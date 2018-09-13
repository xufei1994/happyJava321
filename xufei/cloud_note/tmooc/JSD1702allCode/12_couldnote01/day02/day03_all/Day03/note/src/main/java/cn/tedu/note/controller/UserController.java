package cn.tedu.note.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.note.entity.User;
import cn.tedu.note.service.PasswordException;
import cn.tedu.note.service.UserNotFoundException;
import cn.tedu.note.service.UserService;
import cn.tedu.note.util.JsonResult;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;
	
	@RequestMapping("/login.do")
	@ResponseBody
	public Object login(
			String name, String password){
		
		User user = userService.login(
			name, password);
		return new JsonResult(user);
		
	}
	
	/**
	 * 在其他控制器方法执行出现异常时候, 执行
	 * 异常处理方法 handleException
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Object handleException(
			Exception e){
		e.printStackTrace();
		return new JsonResult(e);
	}
}












