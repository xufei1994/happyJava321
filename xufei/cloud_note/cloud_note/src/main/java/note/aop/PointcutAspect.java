package note.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class PointcutAspect {
    @Before("bean(*Service)")
    public void test1(){
        System.out.println("切入点测试");
    }
}

/*


 */
