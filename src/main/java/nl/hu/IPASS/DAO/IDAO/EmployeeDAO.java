package nl.hu.IPASS.DAO.IDAO;

import nl.hu.IPASS.domain.Employee;
import org.jetbrains.annotations.NotNull;
import java.util.List;

public interface EmployeeDAO {
    boolean save  (@NotNull Employee employee);
    boolean update(@NotNull Employee employee);
    boolean delete(@NotNull Employee employee);
    Employee getEmployeeByUsername(String username);
    Employee getEmployeeByID(int id);
    List<Employee> getAllEmployees();
}
