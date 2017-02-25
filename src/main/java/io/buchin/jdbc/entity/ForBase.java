package io.buchin.jdbc.entity;

import io.buchin.entity.Discussion;
import io.buchin.jdbc.DBProcessor;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created by yuri on 21.02.17.
 */
public class ForBase {
    public static final Logger logger = Logger.getLogger(ForBase.class);

    public static final String USERNAME = "root";
    public static final String PASS = "455415";
    public static final String URL = "jdbc:mysql://localhost:3306/Riddles?useSSL=false";

    public static void deleteTables(){
        DBProcessor db = new DBProcessor();

        try (Connection con = db.getConnection(URL, USERNAME, PASS)) {

            Statement statement = con.createStatement();

            statement.execute("DELETE FROM Riddles.users; " +
                                    "DELETE FROM Riddles.riddles; " +
                                    "DELETE FROM Riddles.riddles;");

        } catch (SQLException e) {
            logger.error("Ошибка удаления таблиц");
        }

    }
}
