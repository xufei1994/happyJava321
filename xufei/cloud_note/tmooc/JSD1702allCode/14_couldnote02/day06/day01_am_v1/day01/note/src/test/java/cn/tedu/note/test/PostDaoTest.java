package cn.tedu.note.test;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.note.dao.PostDao;
import cn.tedu.note.entity.Post;
 
public class PostDaoTest extends BaseTest {
	PostDao dao;
	@Before
	public void initDao(){
		dao = ctx.getBean(
			"postDao", PostDao.class);
	}
	@Test
	public void testFindPostById(){
		Post post = dao.findPostById(1);
		System.out.println(post);
	}
}





