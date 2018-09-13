package test;

import java.util.Properties;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import autowire.Bar;
import ioc.A;
import ioc.Manager;
import value.ExampleBean;
import value.InfoBean;
import value.ValueBean;

public class TestCase {
	@Test
	//测试 set方法注入
	public void test1(){
		String config = "ioc.xml";
		ApplicationContext ac =
		new ClassPathXmlApplicationContext(
				config);
		A a = ac.getBean("a1",A.class);
		a.execute();
	}
	
	@Test
	//测试 构造器注入
	public void test2(){
		String config = "ioc.xml";
		ApplicationContext ac =
		new ClassPathXmlApplicationContext(
				config);
		Manager mg1 = 
				ac.getBean("mg1",Manager.class);
		System.out.println(mg1);
	}
	
	@Test
	//测试 自动装配
	public void test3(){
		String config = "autowire.xml";
		ApplicationContext ac =
		new ClassPathXmlApplicationContext(
				config);
		Bar bar = 
				ac.getBean("bar",Bar.class);
		System.out.println(bar);
	}
	
	@Test
	//测试 注入基本类型的值
	public void test4(){
		String config = "value.xml";
		ApplicationContext ac =
		new ClassPathXmlApplicationContext(
				config);
		ValueBean vb1 = 
				ac.getBean("vb1",
						ValueBean.class);
		System.out.println(vb1);
	}
	
	@Test
	//测试 集合类型的值的注入
	public void test5(){
		String config = "value.xml";
		ApplicationContext ac =
		new ClassPathXmlApplicationContext(
				config);
		ExampleBean eb = 
				ac.getBean("eb1",
						ExampleBean.class);
		System.out.println(eb);
	}
	
	@Test
	//测试 读取属性文件的内容
	public void test6(){
		String config = "value.xml";
		ApplicationContext ac =
		new ClassPathXmlApplicationContext(
				config);
		Properties props = 
				ac.getBean("config",
						Properties.class);
		System.out.println(props);
	}
	
	@Test
	//测试 spring表达式
	public void test7(){
		String config = "value.xml";
		ApplicationContext ac =
		new ClassPathXmlApplicationContext(
				config);
		InfoBean ib1 = 
				ac.getBean("ib1",InfoBean.class);
		System.out.println(ib1);
	}
	
	
	
	
}








