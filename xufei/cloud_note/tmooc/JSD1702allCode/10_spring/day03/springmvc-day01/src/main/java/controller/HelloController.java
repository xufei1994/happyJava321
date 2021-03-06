package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * 处理器
 *
 */
public class HelloController 
	implements Controller{

	public ModelAndView handleRequest(
			HttpServletRequest arg0, 
			HttpServletResponse arg1)
					throws Exception {
		System.out.println("handleRequest()");
		/*
		 * ModelAndView有两个常用构造器:
		 * ModelAndView(String viewName)
		 * ModelAndView(String viewName,
		 * 	Map data)
		 * 注：
		 * 	viewName: 视图名
		 *  data: 处理结果
		 */
		return new ModelAndView("hello");
	}

}





