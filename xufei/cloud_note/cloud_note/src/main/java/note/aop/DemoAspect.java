package note.aop;

import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


/**
 * 创建一个切面组件, 就是一个普通的JavaBean
 */
@Component
@Aspect
public class DemoAspect {
public DemoAspect() {
        System.out.println("---------------------");  //打桩
        }

//声明test方法将在 userService的全部方法之前运行
@Before("bean(userService)")
public void test(){
        System.out.println("Hello World!");
        }


@After("bean(userService)")
public void test2(){
        System.out.println("After");
        }

@AfterReturning("bean(userService)")
public void test3(){
        System.out.println("AfterReturning");
        }

@AfterThrowing("bean(userService)")
public void test4(){
        System.out.println("AfterThrowing");
        }
        }
