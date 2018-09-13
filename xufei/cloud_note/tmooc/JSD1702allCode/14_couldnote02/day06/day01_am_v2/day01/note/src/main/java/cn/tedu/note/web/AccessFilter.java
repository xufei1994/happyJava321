package cn.tedu.note.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.tedu.note.entity.User;

/**
 * Servlet Filter implementation class AccessFilter
 */
public class AccessFilter implements Filter {

	public void destroy() {
	}

	private String login = "/log_in.html";
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = 
				(HttpServletRequest)request;
		HttpServletResponse res =
				(HttpServletResponse)response;
		HttpSession session = req.getSession();
		//放过 log_in.html
		String path = req.getRequestURI();
		//System.out.println("access:"+path);
		if(path.endsWith(login)){
			chain.doFilter(request, response);
			return;
		}
		//放过  alert_error.html
		if(path.endsWith("alert_error.html")){
			chain.doFilter(request, response);
			return;
		}
		
		//检查用户是否登录
		User user = (User)session
				.getAttribute("loginUser");
		//如果没有登录就重定向到 登录页
		if(user==null){//没有登录
			//重定向到登录页
			res.sendRedirect(
				req.getContextPath()+login);
			return;
		}
		//如果登录就放过
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
