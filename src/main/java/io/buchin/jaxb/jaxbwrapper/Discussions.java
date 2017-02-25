package io.buchin.jaxb.jaxbwrapper;

import io.buchin.entity.Discussion;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by yuri on 21.02.17.
 */
@XmlRootElement(name = "Discussions")
public class Discussions {
    private List<Discussion> discussion;

    public List<Discussion> getDiscussion() {
        return discussion;
    }

    public void setDiscussion(List<Discussion> discussion) {
        this.discussion = discussion;
    }
}
