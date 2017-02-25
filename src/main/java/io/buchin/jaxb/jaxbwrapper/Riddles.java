package io.buchin.jaxb.jaxbwrapper;

import io.buchin.entity.Riddle;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by yuri on 21.02.17.
 */
@XmlRootElement(name = "Riddles")
public class Riddles {
    private List<Riddle> riddle;

    public List<Riddle> getRiddle() {
        return riddle;
    }

    public void setRiddle(List<Riddle> riddle) {
        this.riddle = riddle;
    }
}
