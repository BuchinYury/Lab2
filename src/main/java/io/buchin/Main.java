package io.buchin;

import io.buchin.entity.User;
import io.buchin.jaxb.JaxbParser;
import io.buchin.jaxb.jaxbwrapper.Users;
import io.buchin.jdbc.entity.ForBase;
import io.buchin.jdbc.entity.UserSQL;

import io.buchin.runnable.Marshall.DiscussionMarshall;
import io.buchin.runnable.Marshall.RiddleMarshall;
import io.buchin.runnable.Marshall.UserMarshall;
import io.buchin.runnable.Unmarshall.DiscussionUnmarshall;
import io.buchin.runnable.Unmarshall.RiddleUnmarshall;
import io.buchin.runnable.Unmarshall.UserUnmarshall;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;


/**
 * Created by yuri on 21.02.17.
 */
public class Main {
    static {
        DOMConfigurator.configure("src/main/resources/log4j.xml");
    }

    public static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Начинаем работу");

        File usersFile = new File("src/main/resources/xml/users.xml");
        File riddlesFile = new File("src/main/resources/xml/riddles.xml");
        File discusFile = new File("src/main/resources/xml/discussions.xml");

        Thread marshallUsers = new Thread(new UserMarshall(usersFile));
        Thread marshallRiddle = new Thread(new RiddleMarshall(riddlesFile));
        Thread marshallDiscuss = new Thread(new DiscussionMarshall(discusFile));

        marshallUsers.start();
        marshallRiddle.start();
        marshallDiscuss.start();

        try {
            marshallUsers.join();
            marshallRiddle.join();
            marshallDiscuss.join();
        } catch (InterruptedException e) {
            logger.error("Выброшен InterruptedException на маршалинге");
        }

        Thread unmarshallUsers = new Thread(new UserUnmarshall(usersFile));
        Thread unmarshallRiddle = new Thread(new RiddleUnmarshall(riddlesFile));

        unmarshallUsers.start();
        unmarshallRiddle.start();

        try {
            unmarshallUsers.join();
            unmarshallRiddle.join();
        } catch (InterruptedException e) {
            logger.error("Выброшен InterruptedException на анмаршалинге");
        }

        Thread unmarshallDiscuss = new Thread(new DiscussionUnmarshall(discusFile));

        unmarshallDiscuss.start();

        try {
            unmarshallDiscuss.join();
        } catch (InterruptedException e) {
            logger.error("Выброшен InterruptedException на анмаршалинге");
        }

        System.out.println("Done");
        logger.info("Done");
    }
}
