package note.util;

import lombok.Getter;
import lombok.Setter;
import note.exception.PasswordException;
import note.exception.UserNotFoundException;

import java.io.Serializable;

public class JsonResult implements Serializable {
    private static final long serialVersionUID = -3683914085929141042L;

    public static  final int SUCCESS=0;
    public static  final int ERROR=1;
    public static  final int PASSWOEDERROR=3;


    private  int state;
    private String message; //错误消息

    public JsonResult(int state,Throwable e) {
        this.state=state;
        this.message=e.getMessage();
    }

    public JsonResult(String error) {
        state =ERROR;
        this.message = error;
    }

    public JsonResult(Object data){
        state=SUCCESS;
        this.data=data;
    }
    public JsonResult(){}


    public JsonResult(Throwable throwable){
        state=ERROR;
        message = throwable.getMessage();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private Object data;   //返回正确的数据




}
