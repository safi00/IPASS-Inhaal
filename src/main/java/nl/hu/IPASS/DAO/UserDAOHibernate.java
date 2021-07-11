package nl.hu.IPASS.DAO;

import nl.hu.IPASS.DAO.IDAO.UserDAO;
import nl.hu.IPASS.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDAOHibernate implements UserDAO {
    @Autowired

    private SessionFactory sessionFactory;
    @Override
    public boolean save(@NotNull User user) {
        return false;
    }

    @Override
    public boolean update(@NotNull User user) {
        return false;
    }

    @Override
    public boolean delete(@NotNull User user) {
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        //noinspection unchecked
        List<User> users = session.createQuery("from users").list();
        session.close();
        return users;
    }
}
