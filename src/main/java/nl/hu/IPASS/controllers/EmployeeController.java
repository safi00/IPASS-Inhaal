package nl.hu.IPASS.controllers;

import nl.hu.IPASS.DAO.IDAO.EmployeeDAO;
import nl.hu.IPASS.DAO.IDAO.UserDAO;
import nl.hu.IPASS.domain.Employee;
import nl.hu.IPASS.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.List;


@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeDAO employeeDAO;
    @Autowired
    private UserDAO userDAO;

    @PostMapping("/emp/change/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeUser(@FormParam("employeeUsername") String employeeUsername,
                                   @FormParam("employeePassword") String employeePassword,
                                   @FormParam("userUsername") String userUsername,
                                   @FormParam("userName") String userName,
                                   @FormParam("userAboutMe") String userAboutMe,
                                   @FormParam("userEmail") String userEmail){
        try{
            if (!employeePassword.isEmpty() && !employeeUsername.isEmpty() && !userUsername.isEmpty() && !userName.isEmpty() && !userAboutMe.isEmpty() && !userEmail.isEmpty()){
                Employee emp = employeeDAO.getEmployeeByUsername(employeeUsername);
                if (!(emp == null)){
                    if (emp.checkPassword(employeePassword)) {
                        User userChange = userDAO.getUserByUsername(userUsername);
                        if (!(userChange == null)){
                            userChange.setName(userName);
                            userChange.setAboutMe(userAboutMe);
                            userChange.setEmail(userEmail);
                            return Response.ok(userDAO.update(userChange)).build();
                        }
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e);
            return Response.status(Response.Status.NOT_FOUND).entity(new AbstractMap.SimpleEntry<>("message", "params are not fully filled out!")).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new AbstractMap.SimpleEntry<>("message", "params are not fully filled out!")).build();
    }

    @PostMapping("/emp/remove/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeUser(@FormParam("employeeUsername") String employeeUsername,
                                   @FormParam("employeePassword") String employeePassword,
                                   @FormParam("userUsername") String userUsername){
        try{
            if (!employeePassword.isEmpty() && !employeeUsername.isEmpty() && !userUsername.isEmpty()){
                Employee emp = employeeDAO.getEmployeeByUsername(employeeUsername);
                if (!(emp == null)){
                    if (emp.checkPassword(employeePassword)) {
                        User userChange = userDAO.getUserByUsername(userUsername);
                        if (!(userChange == null)){
                            return Response.ok(userDAO.delete(userChange)).build();
                        }
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e);
            return Response.status(Response.Status.NOT_FOUND).entity(new AbstractMap.SimpleEntry<>("message", "params are not fully filled out!")).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new AbstractMap.SimpleEntry<>("message", "params are not fully filled out!")).build();
    }

    @GetMapping("emp/getAll")
    public List<Employee> getAllEmployees(){
        return employeeDAO.getAllEmployees();
    }
}
