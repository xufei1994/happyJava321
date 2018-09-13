package test;

import note.dao.NoteDao;
import note.entity.Note;
import note.service.NoteService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class NoteBookTest extends BaseText {

         NoteService service;
        NoteDao dao;


    @Before
    public void initService(){
        service = ctx.getBean("noteService",
                NoteService.class);
    }
        @Before
        public void initDao(){
            dao = ctx.getBean("noteDao",
                    NoteDao.class);
        }

        @Test
        //select cn_notebook_id from cn_note;
        public void testFindNotesByNotebookId(){
            String id="d92e6b86-e48a-485d-8f11-04a93818bb42";
            List<Map<String, Object>> list=
                    dao.findNotesByNotebookId(id);
            for (Map<String, Object> map : list) {
                System.out.println(map);
            }
        }

    @Test
    public void testUpdateNote(){
        Note note = new Note();
        String noteId = "019cd9e1-b629-4d8d-afd7-2aa9e2d6afe0";
        note.setId(noteId);
        note.setTitle("Test");
        note.setBody("Test123");
        note.setLastModifyTime(System.currentTimeMillis());
        dao.updateNote(note);
        note = dao.findNoteById(noteId);
        System.out.println(note);
    }
    @Test
    public void testUpdate(){
        String id = "019cd9e1-b629-4d8d-afd7-2aa9e2d6afe0";
        String title = "Test";
        String body = "Test123";
        boolean b = service.update(id, title, body);
        Note note = service.getNote(id);
        System.out.println(b);
        System.out.println(note);
    }


    }

