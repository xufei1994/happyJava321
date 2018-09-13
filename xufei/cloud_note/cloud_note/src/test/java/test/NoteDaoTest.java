package test;

import note.dao.NoteDao;
import note.entity.Note;
import org.junit.Before;
import org.junit.Test;

public class NoteDaoTest extends BaseText {

       private NoteDao dao;
    @Before
    public void initDao(){
        dao=ctx.getBean("noteDao", NoteDao.class);
    }
        @Test
        public void testFindNoteById(){
            String noteId = "019cd9e1-b629-4d8d-afd7-2aa9e2d6afe0";
            Note note = dao.findNoteById(noteId);
            System.out.println(note);
        }
        @Test
    public void test5(){
        String id1="8d3763b2-8e01-48d4-a338-730b02ded9c9"
                ;
        String id2=  "a200ec50-4111-4785-97b3-539115b61ed5" ;
        String id3="19fbb55b-0541-433b-a7cd-dba52220a447";
        int n=dao.deleteNotes(id1,id2,id3);
    }

}
