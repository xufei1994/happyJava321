package test;


import note.entity.Note;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

public class Md5Test {

    @Value("#{jdbc.salt}")
    private String salt ;
    @Test
    public void test1(){
        String str="123456";
        String md5= str;
        System.out.println(md5);
        //加盐摘要

        md5=salt+str;
        System.out.println(md5);

        Note note=new Note() ;

    }
}
