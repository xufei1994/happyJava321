package note.service.impl;

import note.dao.UserDao;
import note.entity.User;
import note.exception.PasswordException;
import note.exception.UserNameException;
import note.exception.UserNotFoundException;
import note.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;



@Service("userService")  //业务层 默认的UserServiceImpl的名字
@Transactional
public class UserServiceImpl  implements UserService {
    @Resource //“userDao” 会按名字匹配的规则，先按名字再按类型
    private  UserDao userDao;
    @Value("#{jdbc.salt}")
    private String salt ;

    @Override
    public User login(String name, String password) throws UserNotFoundException, PasswordException {


        if (name==null||name.trim().isEmpty()){ //检验空串，密码为空
            throw new PasswordException("用户名为空");
        }
        if (password==null||password.trim().isEmpty()){ //检验空串，密码为空
            throw new PasswordException("密码为空");
        }
        User user  = userDao.findUserByName(name.trim());   //去掉 空格
        if(user==null){
            throw  new UserNotFoundException("用户名错误");
        }
        System.out.println(salt+password.trim());
        String pwd= DigestUtils.md5Hex((salt+password.trim()));
        //System.out.println(user.getPassword());
        //System.out.println(pwd);
        if (pwd.equals(user.getPassword())){

            return user;
        }
        throw new PasswordException("密码错误");

    }



    //UserServiceImpl
    @Transactional
    @Override
    public User regist(String name, String nick, String password, String password2) throws UserNameException, PasswordException {
        //检查name不能重复
        if (name==null||name.trim().isEmpty()){
            throw new UserNameException("用户名不能为空");
        }
        User one =userDao.findUserByName(name);
        if (one!=null){throw new UserNameException("已注册");}
        //检查密码
        if (password==null||password.trim().isEmpty()){
            throw new PasswordException("密码不能为空");
        }
        if (password==null||password.trim().isEmpty()){
            if (!password.equals(password2)){
                throw new PasswordException("请确认两次密码相同");
            }
            //检查nick
            if (nick==null||nick.trim().isEmpty()){
            nick=name;}
        }
            String id = UUID.randomUUID().toString();
            String token="";
            String password3=DigestUtils.md5Hex(salt+password);
            User user = new User(id,name,password3,token,nick);
            int n=userDao.addUser(user);
            if (n!=1){
                throw  new RuntimeException("添加失败");
            }
            return user;



        }
    }
