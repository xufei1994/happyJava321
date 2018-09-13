package test;

import note.dao.UserDao;
import note.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class UserDaoTset extends BaseText {
    UserDao dao;
    @Before
    public void initDao(){
         dao = ctx.getBean("userDao",UserDao.class);
    }
    @Test
    public  void testFindUserByName(){
        String name="demo";
        UserDao dao = ctx.getBean("userDao",UserDao.class);
        User user =  dao.findUserByName(name);
        System.out.println(user);
    }

    @Test
    public void test2(){
        String id=UUID.randomUUID().toString();
        String name = "Tom";
        String salt = "今天你吃了吗";
        String password =
                DigestUtils.md5Hex(salt+"123456");
        String token = "";
        String nick = "";
        User user = new User(
                id, name, password, token, nick);
        int n = dao.addUser(user);
        System.out.println(n);




    }



}
