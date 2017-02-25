package io.buchin.jdbc;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by yuri on 19.02.17.
 */
public class DBProcessor {
    public static final Logger logger = Logger.getLogger(DBProcessor.class);

    private Connection con;

    public DBProcessor(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("В системе отсутствует MySQL JDBC Driver");
            return;
        }
    }

    public Connection getConnection(String url, String username, String password) throws SQLException {
        if (con != null) return con;
        else con = DriverManager.getConnection(url, username, password);

        return con;
    }
}
