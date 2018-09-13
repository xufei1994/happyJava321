package test;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class BaseText {  //公共的基类 不需要的 创建对象没有意义的  使用 abstract
    ClassPathXmlApplicationContext ctx;

    @Before
    public void initCtx(){
        ctx=new ClassPathXmlApplicationContext("conf/spring/Spring-mvc.xml",
                "conf/spring/Spring-mybatis.xml",
                "conf/spring/Spring-service.xml");
    }

    @After
    public void close(){
        ctx.close();
    }
}
