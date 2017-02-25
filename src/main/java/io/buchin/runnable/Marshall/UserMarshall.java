package io.buchin.runnable.Marshall;

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
public class UserMarshall implements Runnable {
    public static final Logger logger = Logger.getLogger(UserMarshall.class);

    private File usersFile;

    public UserMarshall(File usersFile) {
        this.usersFile = usersFile;
    }

    @Override
    public void run() {
        Users usersToXML = new Users();

        usersToXML.setUser(UserSQL.selectUsers(ForBase.URL, ForBase.USERNAME, ForBase.PASS));

        JaxbParser parser = new JaxbParser();

        try {
            parser.saveObject(usersFile, usersToXML);
        } catch (JAXBException e) {
            logger.error("Проблемы маршалинга users");
        }
    }
}
