package nl.hu.IPASS.controllers;

import nl.hu.IPASS.DAO.IDAO.EmployeeDAO;
import nl.hu.IPASS.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeDAO employeeDAO;

    @GetMapping("/getAll")
    public List<Employee> getAllEmployees(){
        return employeeDAO.getAllEmployees();
    }
}
