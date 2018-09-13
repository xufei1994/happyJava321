package cn.tedu.note.test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.note.entity.Note;
import cn.tedu.note.service.NoteService;

public class NoteServiceTest  extends BaseTest{
	
	NoteService service;
	
	@Before
	public void initService(){
		service = ctx.getBean("noteService",
				NoteService.class);
	}
	
	@Test
	public void testListNotes(){
		String id="fa8d3d9d-2de5-4cfe-845f-951041bcc461";
		List<Map<String, Object>> list=
			service.listNotes(id);
		for (Map<String, Object> map : list) {
			System.out.println(map); 
		}
	}
	
	@Test
	public void testGetNote(){
		String noteId = "019cd9e1-b629-4d8d-afd7-2aa9e2d6afe0";
		Note note = service.getNote(noteId);
		System.out.println(note);
	}
	
	@Test
	public void testUpdate(){
		String id = "019cd9e1-b629-4d8d-afd7-2aa9e2d6afe0";
		String title = "Test";
		String body = "今天天气不错";
		boolean b = service.update(id, title, body);
		Note note = service.getNote(id);
		System.out.println(b);
		System.out.println(note);
	}
	
	@Test
	public void testDeleteNotes(){
		String id1 = "3febebb3-a1b7-45ac-83ba-50cdb41e5fc1";
		String id2 = "9187ffd3-4c1e-4768-9f2f-c600e835b823";
		String id3 = "ebd65da6-3f90-45f9-b045-782928a5e2c0";
		String id4 = "fed920a0-573c-46c8-ae4e-368397846efd";
		
		int n = service.deleteNotes(
			id1, id2, id3, id4);
		//int n = service.deleteNotes(
		//	new String[]{id1, id2, id3, id4});
		System.out.println(n); 
	}
	
	//NoteServiceTest
	
	@Test
	public void testAddStars(){
		String userId="03590914-a934-4da9-ba4d-b41799f917d1";
		boolean b = service.addStars(userId, 5);
		System.out.println(b);
		b = service.addStars(userId, 6);
		System.out.println(b);
	}
	
	
}











