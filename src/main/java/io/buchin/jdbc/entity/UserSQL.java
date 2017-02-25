package io.buchin.jdbc.entity;

import io.buchin.entity.Riddle;
import io.buchin.entity.User;
import io.buchin.jdbc.DBProcessor;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuri on 21.02.17.
 */
public class UserSQL {
    public static final Logger logger = Logger.getLogger(Riddle.class);

    final static String SELECT = "SELECT * FROM Riddles.users";
    final static String INSERT = "INSERT INTO `Riddles`.`users` (`id_user`, `login`, `password`, `user_name`, `admin_true`) VALUES (?,?,?,?,?)";

    public static void insertUsers(List<User> users, String url, String username, String pass) {
        DBProcessor db = new DBProcessor();

        try (Connection con = db.getConnection(url, username, pass)) {
            PreparedStatement preparedInser = con.prepareStatement(INSERT);

            for (User user : users) {

                preparedInser.setInt(1, user.getId());
                preparedInser.setString(2, user.getLogin());
                preparedInser.setString(3, user.getPassword());
                preparedInser.setString(4, user.getUserName());

                if (user.isAdmin())preparedInser.setInt(5, 1);
                else preparedInser.setInt(5, 0);

                preparedInser.execute();
            }
        } catch (SQLException e) {
            logger.error("Ошибка загрузки discussion");
        }
    }

    public static List<User> selectUsers(String url, String username, String pass) {
        List<User> users = new ArrayList<>();

        DBProcessor db = new DBProcessor();

        try (Connection con = db.getConnection(url, username, pass)) {

            PreparedStatement prepStat = con.prepareStatement(SELECT);

            ResultSet resultSet = prepStat.executeQuery();

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt("id_user"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setUserName(resultSet.getString("user_name"));

                if (resultSet.getInt("admin_true") == 1) user.setAdmin(true);
                else user.setAdmin(false);

                users.add(user);
            }

        } catch (SQLException e) {
            logger.error("Ошибка выгрузки discussion");
        }

        return users;
    }
}
