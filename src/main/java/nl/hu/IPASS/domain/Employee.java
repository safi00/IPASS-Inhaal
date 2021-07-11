package nl.hu.IPASS.domain;

import javax.persistence.*;

@Entity
public class Employee extends Account{
    private String name;
    private String employeeType;

    public Employee(String username, String password, String email, String accountType, String employeeType, String name) {
        super(username, password, email, accountType);
        this.name = name;
        this.employeeType = employeeType;
    }

    public Employee() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public String getEmployeeDetails(){
        return "#" + getId() + " account type:" + getAccountType() + " by user " + getUsername() + " can be contacted by " + getEmail()
                + " name : " + getName() + " employee Type : " + getEmployeeType();
    }
}
