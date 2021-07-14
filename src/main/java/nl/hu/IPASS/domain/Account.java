package nl.hu.IPASS.domain;

import javax.persistence.*;
import java.security.Principal;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Account implements Principal {
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String password;
    private String email;
    private String accountType;

    public Account(String username, String password, String email, String accountType) {
        this.username    = username;
        this.password    = password;
        this.email       = email;
        this.accountType = accountType;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountType() {
        return accountType;
    }

    public String toString() {
        return "#" + id + " account type:" + accountType + " by user " + username + " can be contacted by " + email;
    }

    public abstract String getRole();

    public abstract Object getType();

    public abstract Object build();
}