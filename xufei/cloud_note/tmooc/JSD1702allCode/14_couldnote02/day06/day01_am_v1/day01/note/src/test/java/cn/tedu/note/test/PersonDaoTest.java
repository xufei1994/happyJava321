package cn.tedu.note.test;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.note.dao.PersonDao;
import cn.tedu.note.entity.Person;

public class PersonDaoTest extends BaseTest {
	
	PersonDao dao;
	
	@Before
	public void initDao(){
		dao = ctx.getBean(
			"personDao", PersonDao.class);
	}
	
	@Test
	public void testAddPerson(){
		Person person = new Person(null, "熊大");
		System.out.println(person);
		int n = dao.addPerson(person);
		System.out.println(n);
		System.out.println(person);
	}
}





