package nl.hu.IPASS.security;

import nl.hu.IPASS.DAO.IDAO.UserDAO;
import nl.hu.IPASS.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDAO     userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);
        return new MyUserPrincipal(user);
    }
}
