package ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("bar")
public class Bar {
	private Waiter wt;
	
	public Bar() {
		System.out.println("Bar()");
	}

	@Override
	public String toString() {
		return "Bar [wt=" + wt + "]";
	}

	@Autowired
	public Bar(@Qualifier("wt") Waiter wt) {
		System.out.println("Bar(wt)");
		this.wt = wt;
	}
	
	
}
