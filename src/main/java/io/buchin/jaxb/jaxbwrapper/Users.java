package io.buchin.jaxb.jaxbwrapper;

import io.buchin.entity.User;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by yuri on 20.02.17.
 */
@XmlRootElement(name = "Users")
public class Users {
    private List<User> user;

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
