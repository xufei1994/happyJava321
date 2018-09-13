package cn.tedu.note.test;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.note.dao.NotebookDao;

public class NotebookDaoTest extends BaseTest{
	
	NotebookDao dao;
	
	@Before
	public void initDao(){
		dao = ctx.getBean("notebookDao",
				NotebookDao.class);
	}
	
	@Test
	//select cn_user_id from cn_notebook;
	public void testFindNotebooksByUserId(){
		String userId="52f9b276-38ee-447f-a3aa-0d54e7a736e4";
		List<Map<String, Object>> list=
			dao.findNotebooksByUserId(userId);
		for (Map<String, Object> map : list) {
			System.out.println(map); 
		}
	}
	
	@Test
	public void testCountByNotebookId(){
		String id = "d0b0727f-a233-4a1f-8600-f49fc1f25bc9";
		int n = dao.countNotebookById(id);
		System.out.println(n); 
	}
}




