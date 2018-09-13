package cn.tedu.note.test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.note.dao.NoteDao;
import cn.tedu.note.entity.Note;

public class NoteDaoTest  extends BaseTest{
	
	NoteDao dao;
	
	@Before
	public void initDao(){
		dao = ctx.getBean("noteDao",
				NoteDao.class);
	}
	
	@Test
	//select cn_notebook_id from cn_note;
	public void testFindNotesByNotebookId(){
		String id="fa8d3d9d-2de5-4cfe-845f-951041bcc461";
		List<Map<String, Object>> list=
			dao.findNotesByNotebookId(id);
		for (Map<String, Object> map : list) {
			System.out.println(map); 
		}
	}
	
	@Test
	public void testFindNoteById(){
		String noteId = "019cd9e1-b629-4d8d-afd7-2aa9e2d6afe0";
		Note note = dao.findNoteById(noteId);
		System.out.println(note);
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

}




