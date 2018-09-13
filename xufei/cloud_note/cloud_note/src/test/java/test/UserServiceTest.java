package test;

import note.entity.User;
import note.service.UserService;
import note.util.JsonResult;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTest extends BaseText {

    UserService service;
    @Before
    public void initDao(){
        service = ctx.getBean("userService",UserService.class);
    }

    @Test
    public void test2(){
        User user = service.regist("xufei","andy","19941219","19941219");
        System.out.println(user);
    }

    @Test
    public void tset1(){
        JsonResult result=new JsonResult();

        String name = "demo";
        String password="123456";
        UserService service=ctx.getBean("userService",UserService.class);
        User user= service.login(name,password);
        System.out.println(user);
    }
}
