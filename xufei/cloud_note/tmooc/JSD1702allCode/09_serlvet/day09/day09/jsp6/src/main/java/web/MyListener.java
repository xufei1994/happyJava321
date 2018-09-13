package web;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyListener implements 
	ServletRequestListener,
	ServletRequestAttributeListener {

	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("request销毁");
	}

	public void requestInitialized(ServletRequestEvent e) {
		System.out.println("request创建");
		System.out.println(e.getServletRequest());
	}

	public void attributeAdded(ServletRequestAttributeEvent arg0) {
		System.out.println(
			"向request中添加数据");
	}

	public void attributeRemoved(ServletRequestAttributeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void attributeReplaced(ServletRequestAttributeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}







