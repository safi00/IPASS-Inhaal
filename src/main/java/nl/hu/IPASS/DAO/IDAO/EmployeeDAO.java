package nl.hu.IPASS.DAO.IDAO;

import nl.hu.IPASS.domain.Employee;
import org.jetbrains.annotations.NotNull;

public interface EmployeeDAO {
    boolean save  (@NotNull Employee employee);
    boolean update(@NotNull Employee employee);
    boolean delete(@NotNull Employee employee);
}
