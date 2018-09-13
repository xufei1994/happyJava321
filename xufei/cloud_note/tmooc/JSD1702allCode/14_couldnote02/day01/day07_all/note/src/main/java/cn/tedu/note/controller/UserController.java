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
import cn.tedu.note.service.UserNameException;
import cn.tedu.note.service.UserNotFoundException;
import cn.tedu.note.service.UserService;
import cn.tedu.note.util.JsonResult;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

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
	
	@ExceptionHandler( UserNotFoundException.class)
	@ResponseBody
	public JsonResult handleUserNotFound(
			UserNotFoundException e){
		e.printStackTrace();
		return new JsonResult(2,e);
	}
	
	@ExceptionHandler(PasswordException.class)
	@ResponseBody
	public JsonResult handlePassword(
			PasswordException e){
		e.printStackTrace();
		return new JsonResult(3,e);
	}

	@ExceptionHandler(UserNameException.class)
	@ResponseBody
	public JsonResult handleUserName(
			UserNameException e){
		e.printStackTrace();
		return new JsonResult(4,e);
	}

	
	
	
	@RequestMapping("/regist.do")
	@ResponseBody
	public JsonResult regist(String name,
			String nick, String password,
			String confirm){
		User user = userService.regist(
				name, nick, password, confirm);
		return new JsonResult(user);
	}
}












