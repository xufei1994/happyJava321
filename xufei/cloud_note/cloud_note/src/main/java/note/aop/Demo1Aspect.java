package note.aop;

import note.exception.UserNotFoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
//@Aspect
public class Demo1Aspect {

    /**
     * 环绕通知 方法:
     *  1. 必须有返回值值Object
     *  2. 必须有参数  ProceedingJoinPoint
     *  3. 必须抛出异常
     *  4. 需要在方法中调用  jp.proceed()
     *  5. 返回业务方法的返回值
     * @param jp
     * @return
     * @throws Throwable
     */
   // @Around("bean(*Service)")
    public Object test5(ProceedingJoinPoint jp)
            throws Throwable{
        //业务方法之前
        long start=System.currentTimeMillis();
        Object val = jp.proceed();
        long end =System.currentTimeMillis();
        jp.getSignature();//获取方法签名  调用参数
        System.out.println("方法"+jp.getSignature());
        System.out.println("时间："+(end-start));

        //业务方法之后

        System.out.println("业务结果:"+val);
      //  throw new UserNotFoundException(
        //           "就是不让登录");
        return val;
    }

}
/*
  aop 原理
  代理：不改变其原有功能，为其扩展功能
  动态代理


   */
