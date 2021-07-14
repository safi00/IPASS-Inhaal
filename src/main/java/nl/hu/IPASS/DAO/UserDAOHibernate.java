package nl.hu.IPASS.DAO;

import nl.hu.IPASS.DAO.IDAO.UserDAO;
import nl.hu.IPASS.domain.OwnedPokemon;
import nl.hu.IPASS.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class UserDAOHibernate implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean save(@NotNull User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            session.save(user);
            transaction.commit();
            return true;
        } catch (PersistenceException exception) {
            transaction.rollback();
            Throwable cause = exception.getCause();
            while (cause != null) {
                if (cause instanceof ConstraintViolationException)
                    return false;
                cause = cause.getCause();
            }
            throw exception;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean update(@NotNull User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            session.update(user);
            transaction.commit();

            return true;
        } catch (PersistenceException exception) {
            transaction.rollback();
            Throwable cause = exception.getCause();
            while (cause != null) {
                if (cause instanceof ConstraintViolationException)
                    return false;
                cause = cause.getCause();
            }
            throw exception;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(@NotNull User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            session.delete(user);
            transaction.commit();
            return true;
        } catch (PersistenceException exception) {
            transaction.rollback();
            Throwable cause = exception.getCause();
            while (cause != null) {
                if (cause instanceof ConstraintViolationException)
                    return false;
                cause = cause.getCause();
            }
            throw exception;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean isUserSaved(@NotNull User user) {
        return getAllUsers().contains(user);
    }

    @Override
    public User getUserByUsername(String username) {
        Session session = sessionFactory.openSession();
        User returnUser = null;
        for (User user : getAllUsers()){
            if (user.getUsername().equals(username)){
                returnUser = user;
            }
        }
        session.close();
        return returnUser;
    }

    @Override
    public User getUserByID(int id) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
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
