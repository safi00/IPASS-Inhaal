package nl.hu.IPASS.security;

import nl.hu.IPASS.DAO.IDAO.EmployeeDAO;
import nl.hu.IPASS.DAO.IDAO.UserDAO;
import nl.hu.IPASS.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private EmployeeDAO employeeDAO;
    private UserDAO     userDAO;

    public UserDetails loadEmployeeByUsername(String username) throws UsernameNotFoundException {
        Account employee = employeeDAO.getEmployeeByUsername(username);
        if (employee == null)
            throw new UsernameNotFoundException(username);
        return new MyUserPrincipal(employee);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = userDAO.getUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);
        return new MyUserPrincipal(user);
    }
}
