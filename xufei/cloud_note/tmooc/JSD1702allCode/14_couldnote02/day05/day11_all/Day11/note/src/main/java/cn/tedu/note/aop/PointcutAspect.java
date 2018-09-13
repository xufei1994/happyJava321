package cn.tedu.note.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PointcutAspect {
	
	//@Before("bean(*Service)")
	//@Before("execution(* cn.tedu.note.service.UserService.login(..))")
	@Before("execution(* cn.tedu.note.*.*Service.list*(..))")
	public void test(){
		System.out.println("切入点测试");
	}
	
}




