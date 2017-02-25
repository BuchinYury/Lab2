package io.buchin.runnable.Unmarshall;

import io.buchin.jaxb.JaxbParser;
import io.buchin.jaxb.jaxbwrapper.Users;
import io.buchin.jdbc.entity.ForBase;
import io.buchin.jdbc.entity.UserSQL;
import org.apache.log4j.Logger;

import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * Created by yuri on 22.02.17.
 */
public class UserUnmarshall implements Runnable {
    public static final Logger logger = Logger.getLogger(UserUnmarshall.class);

    private File usersFile;

    public UserUnmarshall(File usersFile) {
        this.usersFile = usersFile;
    }

    @Override
    public void run() {
        JaxbParser parser = new JaxbParser();

        try {
            Users usersInXML = (Users) parser.getObject(usersFile, Users.class);

            UserSQL.insertUsers(usersInXML.getUser(), ForBase.URL, ForBase.USERNAME, ForBase.PASS);

        } catch (JAXBException e) {
            logger.error("Проблемы анмаршалинга users");
        }
    }
}
