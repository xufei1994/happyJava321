package note.dao;

import note.entity.User;
import org.springframework.stereotype.Repository;


public interface UserDao {
    User findUserByName(String name);
    int addUser(User user);

    User findUserById(String userId);
}
