package io.buchin.entity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by yuri on 20.02.17.
 */
@XmlRootElement(name = "User")
@XmlType(propOrder = {"id", "login","password","userName","admin"})
public class User {
    private int id;
    private String login;
    private String password;
    private String userName;
    private boolean admin;

    public User(){}

    public User(int id, String login, String password, String userName, boolean admin) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userName = userName;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
