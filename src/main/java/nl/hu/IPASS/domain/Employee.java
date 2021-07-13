package nl.hu.IPASS.domain;

import javax.persistence.*;

@Entity
public class Employee extends Account{
    private String name;
    private String employeeType;

    public Employee(String username, String password, String email, String employeeType, String name) {
        super(username, password, email, "employee");
        this.name = name;
        this.employeeType = employeeType;
    }

    public Employee() {
    }

    @Override
    public String getRole() {
        return null;
    }

    @Override
    public Object getType() {
        return getEmployeeType();
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
