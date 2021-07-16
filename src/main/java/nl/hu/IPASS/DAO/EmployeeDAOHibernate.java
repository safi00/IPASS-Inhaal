package nl.hu.IPASS.DAO;

import nl.hu.IPASS.DAO.IDAO.EmployeeDAO;
import nl.hu.IPASS.domain.Employee;
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
public class EmployeeDAOHibernate implements EmployeeDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean save(@NotNull Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            session.save(employee);
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
    public boolean update(@NotNull Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            session.update(employee);
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
    public boolean delete(@NotNull Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        //noinspection TryFinallyCanBeTryWithResources
        try {
            session.delete(employee);
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
    public Employee getEmployeeByUsername(String username) {
        Session session = sessionFactory.openSession();
        Employee returnEmp = null;
        for (Employee emp : getAllEmployees()){
            if (emp.getUsername().equals(username)){
                returnEmp = emp;
            }
        }
        session.close();
        return returnEmp;
    }

    @Override
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.openSession();
        //noinspection unchecked
        List<Employee> employees = session.createQuery("from Employee ").list();
        session.close();
        return employees;
    }
}
