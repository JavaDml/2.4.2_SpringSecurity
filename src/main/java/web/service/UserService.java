package web.service;


import web.model.User;
import java.util.List;

public interface UserService {
    List<User> getUsers();
    void addUser(User user);
    void delUser(Long id);
    void updUser(User user);
    User getUser(Long id);
}
