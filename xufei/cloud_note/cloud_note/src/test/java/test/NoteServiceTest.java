package test;

import note.entity.Note;
import note.service.NoteService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class NoteServiceTest  extends BaseText{

    NoteService service;

    @Before
    public void initService(){
        service = ctx.getBean("noteService",
                NoteService.class);
    }

    @Test
    public void testListNotes(){
        String id="9c68ca1a-830f-4a81-a8ec-d148d2df4f7f";
        List<Map<String, Object>> list=
                service.listNotes(id);
        for (Map<String, Object> map : list) {
            System.out.println(map);
        }
    }
    @Test
    public void testGetNote(){
        String noteId ="003ec2a1-f975-4322-8e4d-dfd206d6ac0c";
        Note note=service.getNote(noteId);
        System.out.println(note);
    }
    @Test
    public void test2(){
        String noteId ="52f9b276-38ee-447f-a3aa-0d54e7a736e4";
       String n1="0037215c-09fe-4eaa-aeb5-25a340c6b39b";
       String n2="ds";
        Note note=service.addNote(noteId,n1,n2);
        System.out.println(note);
    }
    @Test
    public void test3(){
        String noteId ="003ec2a1-f975-4322-8e4d-dfd206d6ac0c";
        String n1="4b86d1f9-6345-4532-bc50-ee86442f004b";
        boolean note=service.moveNote(noteId,n1);
        System.out.println(note);
    }
    @Test
    public void test5(){
        String id1="61c6755f-a078-4329-a2b4-5a97eeefd96b";
        String id2="60480071-f989-4945-9b1c-0d2aba07ae96";
        String id3="5efc0d6a-1fe3-45d5-863f-618d403a6d7f";
        String id4="5d9587d3-b15a-486a-970d-9964c5b2410e";
        int n=service.deleteNotes(id1,id2,id3,id4);
        //int n=service.deleteNotes(new String[]{id1,id2,id3,id4});  编译完成后的代码
        System.out.println(n);


    }
    @Test
    public  void test6(){
        String userId="2273f742-61ec-4440-b88a-42cf48db19ff";
        boolean b=service.addStars(userId,5);
        System.out.println(b);
        b=service.addStars(userId,44);
        System.out.println(b);
    }


}
