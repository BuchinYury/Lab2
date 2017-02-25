package io.buchin.runnable.Unmarshall;

import io.buchin.jaxb.JaxbParser;
import io.buchin.jaxb.jaxbwrapper.Discussions;
import io.buchin.jaxb.jaxbwrapper.Riddles;
import io.buchin.jdbc.entity.DiscussionSQL;
import io.buchin.jdbc.entity.ForBase;
import io.buchin.jdbc.entity.RiddleSQL;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * Created by yuri on 22.02.17.
 */
public class DiscussionUnmarshall implements Runnable {
    public static final Logger logger = Logger.getLogger(DiscussionUnmarshall.class);

    private File discussionFile;

    public DiscussionUnmarshall(File discussionFile) {
        this.discussionFile = discussionFile;
    }

    @Override
    public void run() {
        JaxbParser parser = new JaxbParser();

        try {
            Discussions discussionsInXML = (Discussions) parser.getObject(discussionFile, Discussions.class);

            DiscussionSQL.insertDiscussions(discussionsInXML.getDiscussion(), ForBase.URL, ForBase.USERNAME, ForBase.PASS);

        } catch (JAXBException e) {
            logger.error("Проблемы анмаршалинга discussion");
        }
    }
}
