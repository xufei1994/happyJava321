package test;

import org.junit.Before;
import org.junit.Test;

import note.dao.PostDao;
import note.entity.Post;
 
public class PostDaoTest extends BaseText {
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





