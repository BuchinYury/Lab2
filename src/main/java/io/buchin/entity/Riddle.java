package io.buchin.entity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by yuri on 20.02.17.
 */
@XmlRootElement(name = "Riddle")
@XmlType(propOrder = {"id", "name", "text", "level", "category", "answer", "idUser"})
public class Riddle {
    private int id;
    private String name;
    private String text;
    private int level;
    private String category;
    private String answer;
    private int idUser;

    public Riddle(){}

    public Riddle(int id, String name, String text, int level, String category, String answer, int idUser) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.level = level;
        this.category = category;
        this.answer = answer;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
