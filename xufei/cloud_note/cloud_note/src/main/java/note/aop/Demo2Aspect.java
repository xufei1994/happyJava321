package note.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class Demo2Aspect {
    @Before(value = "execution(* note.service.UserService.login(..))") //注意* 后面有空格
    public void test1(){
        System.out.println("within测试");//方法切入点测试
    }
}
