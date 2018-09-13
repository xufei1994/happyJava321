package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ioc.Bar;
import ioc.Leader;
import ioc.Restaurant;
import ioc.Student;
import ioc.UserBean;

public class TestCase {
	@Test
	//测试 组件扫描
	public void test1(){
		String config = "ioc.xml";
		ApplicationContext ac = 
		new ClassPathXmlApplicationContext(
				config);
		Student stu1 = 
				ac.getBean("stu1",
						Student.class);
		System.out.println(stu1);
	}
	
	@Test
	//测试 作用域
	public void test2(){
		String config = "ioc.xml";
		ApplicationContext ac = 
		new ClassPathXmlApplicationContext(
				config);
		Student stu1 = 
				ac.getBean("stu1",
						Student.class);
		Student stu2 = 
				ac.getBean("stu1",
						Student.class);
		System.out.println(stu1 == stu2);
	}
	
	@Test
	//测试 延迟加载
	public void test3(){
		String config = "ioc.xml";
		ApplicationContext ac = 
		new ClassPathXmlApplicationContext(
				config);
		
	}
	
	@Test
	//测试 生命周期相关的方法
	public void tes4(){
		String config = "ioc.xml";
		AbstractApplicationContext ac = 
		new ClassPathXmlApplicationContext(
				config);
		Student stu1 = 
				ac.getBean("stu1",
						Student.class);
		ac.close();
	}
	
	@Test
	//测试  @Autowired和@Qualifier
	public void test5(){
		String config = "ioc.xml";
		ApplicationContext ac = 
		new ClassPathXmlApplicationContext(
				config);
		Restaurant rest =
				ac.getBean("rest",
						Restaurant.class);
		System.out.println(rest);
		Bar bar = 
				ac.getBean("bar",Bar.class);
		System.out.println(bar);
	}
	
	@Test
	//测试 @Resource
	public void test6(){
		String config = "ioc.xml";
		ApplicationContext ac = 
		new ClassPathXmlApplicationContext(
				config);
		Leader ld = 
				ac.getBean("leader",
						Leader.class);
		System.out.println(ld);
	}
	
	@Test
	//测试  @Value
	public void test7(){
		String config = "ioc.xml";
		ApplicationContext ac = 
		new ClassPathXmlApplicationContext(
				config);
		UserBean ub =
				ac.getBean("user",
						UserBean.class);
		System.out.println(ub);
	}
	
	
}



