package nl.hu.IPASS.DAO;

import nl.hu.IPASS.DAO.IDAO.EmployeeDAO;
import nl.hu.IPASS.domain.Employee;
import org.jetbrains.annotations.NotNull;

public class EmployeeDAOHibernate implements EmployeeDAO {
    @Override
    public boolean save(@NotNull Employee employee) {
        return false;
    }

    @Override
    public boolean update(@NotNull Employee employee) {
        return false;
    }

    @Override
    public boolean delete(@NotNull Employee employee) {
        return false;
    }
}
