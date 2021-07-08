package nl.hu.IPASS.controllers;

import nl.hu.IPASS.DAO.IDAO.UserDAO;
import nl.hu.IPASS.DAO.UserDAOHibernate;
import java.util.List;
import nl.hu.IPASS.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get")
public class UserController {
    @Autowired
    private UserDAO userDao;

    @GetMapping
    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }
}
