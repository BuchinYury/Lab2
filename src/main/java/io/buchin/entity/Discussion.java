package io.buchin.entity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by yuri on 21.02.17.
 */

@XmlRootElement(name = "Discussion")
@XmlType(propOrder = {"id", "user", "riddle", "rating", "status", "text"})
public class Discussion {
    private int id;
    private User user;
    private Riddle riddle;
    private int rating;
    private String status;
    private String text;

    public Discussion(int id, User user, Riddle riddle, int rating, String status, String text) {
        this.id = id;
        this.user = user;
        this.riddle = riddle;
        this.rating = rating;
        this.status = status;
        this.text = text;
    }

    public Discussion() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Riddle getRiddle() {
        return riddle;
    }

    public void setRiddle(Riddle riddle) {
        this.riddle = riddle;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
