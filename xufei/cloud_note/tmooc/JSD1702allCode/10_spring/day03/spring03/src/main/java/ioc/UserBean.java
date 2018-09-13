package ioc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("user")
public class UserBean {
	
	@Value("安迪")
	private String name;
	
	@Value("#{config.pageSize}")
	private String pageSize;
	
	@Override
	public String toString() {
		return "UserBean [name=" + name + ", pageSize=" + pageSize + "]";
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPageSize() {
		return pageSize;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
