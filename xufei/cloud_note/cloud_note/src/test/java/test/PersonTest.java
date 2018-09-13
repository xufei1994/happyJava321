package test;

import note.dao.PersonDao;
import note.entity.Person;
import org.junit.Before;
import org.junit.Test;

public class PersonTest extends BaseText{
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
