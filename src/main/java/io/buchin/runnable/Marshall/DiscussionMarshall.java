package io.buchin.runnable.Marshall;

import io.buchin.jaxb.JaxbParser;
import io.buchin.jaxb.jaxbwrapper.Discussions;
import io.buchin.jdbc.entity.DiscussionSQL;
import io.buchin.jdbc.entity.ForBase;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * Created by yuri on 22.02.17.
 */
public class DiscussionMarshall implements Runnable {
    public static final Logger logger = Logger.getLogger(DiscussionMarshall.class);

    private File discussionFile;

    public DiscussionMarshall(File discussionFile) {
        this.discussionFile = discussionFile;
    }

    @Override
    public void run() {
        Discussions discussionsToXML = new Discussions();

        discussionsToXML.setDiscussion(DiscussionSQL.selectDiscussions(ForBase.URL, ForBase.USERNAME, ForBase.PASS));

        JaxbParser parser = new JaxbParser();

        try {
            parser.saveObject(discussionFile, discussionsToXML);
        } catch (JAXBException e) {
            logger.error("Проблемы маршалинга discusions");
        }
    }
}
