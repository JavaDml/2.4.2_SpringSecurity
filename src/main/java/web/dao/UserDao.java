package web.dao;

import web.model.User;
import java.util.List;

public interface UserDao {
    List<User> getUsers();
    void addUser(User user);
    void delUser(Long id);
    void updUser(User user);
    User getUser(Long id);
    User getUserByName(String s);
}

