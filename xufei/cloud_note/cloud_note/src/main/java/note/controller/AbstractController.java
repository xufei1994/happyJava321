package note.controller;

import note.exception.PasswordException;
import note.exception.UserNameException;
import note.exception.UserNotFoundException;
import note.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

public abstract class AbstractController {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public  JsonResult handleUserNotFound(UserNotFoundException e){
        e.printStackTrace();
        return new JsonResult(2,e);
    }
    @ExceptionHandler(PasswordException.class)
    @ResponseBody
    public  JsonResult handlePassword(PasswordException e){
        e.printStackTrace();
        return new JsonResult(3,e);
    }


    @ExceptionHandler(UserNameException.class)
    @ResponseBody
    public JsonResult handleUserName(
            UserNameException e){
        e.printStackTrace();
        return new JsonResult(4,e);
    }

    /*
    在其他控制器方法执行出现异常时候，
    执行异常处理方法handleException
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult handleException(Exception e){
        e.printStackTrace();
        return new JsonResult(e);
    }

}
