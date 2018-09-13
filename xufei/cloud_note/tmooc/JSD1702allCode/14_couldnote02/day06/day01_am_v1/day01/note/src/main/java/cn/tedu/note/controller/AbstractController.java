package cn.tedu.note.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.note.util.JsonResult;

public abstract class AbstractController {
	/**
	 * 在其他控制器方法执行出现异常时候, 执行
	 * 异常处理方法 handleException
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Object handleException( Exception e){
		e.printStackTrace();
		return new JsonResult(e);
	}

}