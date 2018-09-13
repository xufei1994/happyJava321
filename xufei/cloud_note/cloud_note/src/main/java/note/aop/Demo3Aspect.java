package note.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Demo3Aspect {

    @Pointcut
    public void test1(){
        System.out.println("excution 方法切入点测试");
    }
}
