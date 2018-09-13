package cn.tedu.note.test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.note.entity.User;
import cn.tedu.note.service.NotebookService;
import cn.tedu.note.service.UserService;

public class NotebookServiceTest extends BaseTest {
	NotebookService service;
	@Before
	public void initService(){
		service = ctx.getBean("notebookService",
				NotebookService.class);
	}
	
	@Test
	public void testListNotebooks(){
		String userId="39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
		List<Map<String, Object>> list=
			service.listNotebooks(userId);
		for (Map<String, Object> map : list) {
			System.out.println(map); 
		}

	}
	@Test
	public void testListNotebooksPaged(){
		String userId="39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
		List<Map<String, Object>> list=
			service.listNotebooks(userId, 0);
		for (Map<String, Object> map : list) {
			System.out.println(map); 
		}

	}
}




