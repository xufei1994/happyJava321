package cn.tedu.note.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.tedu.note.entity.User;
import cn.tedu.note.util.JsonResult;

@Component
public class AccessInterceptor implements 
	HandlerInterceptor {
	public boolean preHandle(
			HttpServletRequest req,
			HttpServletResponse res, 
			Object handle) throws Exception {
		String path=req.getRequestURI();
		//System.out.println("Interceptor:"+path);
		HttpSession session = req.getSession();
		User user = (User)session
				.getAttribute("loginUser");
		//如果没有登录就返回错误的JSON消息
		if(user==null){
			JsonResult result = 
				new JsonResult("需要重新登录!");
			//利用response 对象反馈结果
			res.setContentType(
				"application/json;charset=UTF-8");
			res.setCharacterEncoding("UTF-8");
			ObjectMapper mapper = 
					new ObjectMapper();
			String json=mapper
				.writeValueAsString(result);
			res.getWriter().println(json);
			res.flushBuffer();
			return false;
		}
		//如果登录了就放过请求
		return true;//放过请求
	}
	
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}
	
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}
}
