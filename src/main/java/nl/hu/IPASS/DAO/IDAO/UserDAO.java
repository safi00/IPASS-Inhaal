package nl.hu.IPASS.DAO.IDAO;

import nl.hu.IPASS.domain.User;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public interface UserDAO {
    boolean save  (@NotNull User user);
    boolean update(@NotNull User user);
    boolean delete(@NotNull User user);
    User getUserByUsername(String username);
    User getUserByID(int id);
    List<User> getAllUsers();
}
