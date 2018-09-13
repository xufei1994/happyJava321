package cn.tedu.note.test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.note.dao.NoteDao;

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
}




