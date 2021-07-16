package nl.hu.IPASS.DAO.IDAO;

import nl.hu.IPASS.domain.User;
import java.util.List;

public interface UserDAO {
    boolean save  (User user);
    boolean update(User user);
    boolean delete(User user);
    boolean isUserSaved(User user);
    User getUserByUsername(String username);
    User getUserByID(int id);
    List<User> getAllUsers();
}
