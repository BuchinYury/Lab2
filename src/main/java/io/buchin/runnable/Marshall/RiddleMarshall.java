package io.buchin.runnable.Marshall;

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
public class RiddleMarshall implements Runnable {
    public static final Logger logger = Logger.getLogger(RiddleMarshall.class);

    private File riddleFile;

    public RiddleMarshall(File riddleFile) {
        this.riddleFile = riddleFile;
    }

    @Override
    public void run() {
        Riddles riddlesToXML = new Riddles();

        riddlesToXML.setRiddle(RiddleSQL.selectRiddles(ForBase.URL, ForBase.USERNAME, ForBase.PASS));

        JaxbParser parser = new JaxbParser();

        try {
            parser.saveObject(riddleFile, riddlesToXML);
        } catch (JAXBException e) {
            logger.error("Проблемы маршалинга riddles");
        }
    }
}
