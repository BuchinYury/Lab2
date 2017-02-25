package io.buchin.runnable.Unmarshall;

import io.buchin.jaxb.JaxbParser;
import io.buchin.jaxb.jaxbwrapper.Riddles;
import io.buchin.jdbc.entity.ForBase;
import io.buchin.jdbc.entity.RiddleSQL;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * Created by yuri on 22.02.17.
 */
public class RiddleUnmarshall implements Runnable {
    public static final Logger logger = Logger.getLogger(RiddleUnmarshall.class);

    private File riddleFile;

    public RiddleUnmarshall(File riddleFile) {
        this.riddleFile = riddleFile;
    }

    @Override
    public void run() {
        JaxbParser parser = new JaxbParser();

        try {
            Riddles riddlesInXML = (Riddles) parser.getObject(riddleFile, Riddles.class);

            RiddleSQL.insertRiddles(riddlesInXML.getRiddle(), ForBase.URL, ForBase.USERNAME, ForBase.PASS);

        } catch (JAXBException e) {
            logger.error("Проблемы анмаршалинга riddles");
        }
    }
}
