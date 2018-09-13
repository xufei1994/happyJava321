package test;

import note.service.NotebookService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class NoteBookServiceTest extends BaseText {
    NotebookService service;

    @Before
    public void initService() {
        service = ctx.getBean("notebookService",
                NotebookService.class);
    }

    @Test
    public void testListNotebooks() {
        String userId = "39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
        List<Map<String, Object>> list =
                 service.listNotebooks(userId);
        for (Map<String, Object> map : list) {
            System.out.println(map);
        }
    }




    @Test
    public void test6(){
        String userId="39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
        List<Map<String,Object>> list = service.listNotebooks(userId,0);
        for (Map<String,Object>map:list){
            System.out.println(map);
        }
    }
//    @Test
//    public void testAddStars(){
//        String userId="03590914-a934-4da9-ba4d-b41799f917d1";
//        boolean b = service.addStars(userId, 5);
//        System.out.println(b);
//        b = service.addStars(userId, 6);
//        System.out.println(b);
//    }
}
