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
		String userId="52f9b276-38ee-447f-a3aa-0d54e7a736e4";
		List<Map<String, Object>> list=
			service.listNotebooks(userId);
		for (Map<String, Object> map : list) {
			System.out.println(map); 
		}

	}
}




