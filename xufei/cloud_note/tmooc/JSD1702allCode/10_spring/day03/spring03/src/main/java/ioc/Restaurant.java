package ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("rest")
public class Restaurant {
	@Autowired
	@Qualifier("wt")
	private Waiter wt;
	
//	@Autowired
//	public void setWt(
//			@Qualifier("wt") Waiter wt) {
//		System.out.println("setWt()");
//		this.wt = wt;
//	}
	

	public Restaurant() {
		System.out.println("Restaurant()");
	}

	@Override
	public String toString() {
		return "Restaurant [wt=" + wt + "]";
	}
	
}
