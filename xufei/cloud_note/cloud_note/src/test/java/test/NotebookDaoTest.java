package test;

import note.dao.NotebookDao;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class NotebookDaoTest extends BaseText {

    NotebookDao dao;
    @Before
    public void initDao(){
        dao=ctx.getBean("notebookDao", NotebookDao.class);
    }


    @Test
    public void testFindNotebooksByUserId(){
        String userId="333c6d0b-e4a2-4596-9902-a5d98c2f665a";
        List<Map<String,Object>> list =
                dao.findNotebooksByUserId(userId);
        for (Map<String,Object>map:list){
            System.out.println(map);
        }

    }
}
