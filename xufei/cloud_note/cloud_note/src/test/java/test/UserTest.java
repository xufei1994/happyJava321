package test;

import note.dao.UserDao;
import note.entity.User;
import org.junit.Test;

public class UserTest extends BaseText{



    @Test
    public void testFindUserByName(){
        String name = "demo";
        UserDao dao = ctx.getBean(
                "userDao", UserDao.class);
        User user = dao.findUserByName(name);
        System.out.println(user);
    }
}
