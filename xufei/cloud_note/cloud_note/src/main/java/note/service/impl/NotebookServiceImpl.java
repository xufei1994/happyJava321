package note.service.impl;

import note.dao.NotebookDao;
import note.dao.UserDao;
import note.entity.User;
import note.exception.UserNotFoundException;
import note.service.NotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("notebookService")
@Transactional
public class NotebookServiceImpl implements NotebookService {


  @Resource
    private NotebookDao notebookDao;

  @Value("#{jdbc.pageSize}")
  private  int pageSize;

    @Resource
    private UserDao userDao;

    public List<Map<String, Object>>
    listNotebooks(String userId)
            throws UserNotFoundException {
        if(userId==null || userId.trim().isEmpty()){
            throw new UserNotFoundException("ID不能空");
        }
        User user = userDao.findUserById(userId);
        if(user==null){
            throw new UserNotFoundException("用户不存在");
        }
        return notebookDao
                .findNotebooksByUserId(userId);
    }

    @Override
    public List<Map<String, Object>> listNotebooks(String userId, Integer page) throws UserNotFoundException {
        if(userId==null || userId.trim().isEmpty()){
            throw new UserNotFoundException("ID不能空");
        }
        User user = userDao.findUserById(userId);
        if(user==null){
            throw new UserNotFoundException("用户不存在");
        }
        if (page==null){
            page=0;
        }

        int start = page * pageSize;
        String table="cn_notebook";
        return notebookDao.findNotebooksByPage(userId,start,pageSize,table);
    }

}
