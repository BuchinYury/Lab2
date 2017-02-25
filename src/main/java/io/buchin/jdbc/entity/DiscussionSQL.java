package io.buchin.jdbc.entity;

import io.buchin.Main;
import io.buchin.entity.Discussion;
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
public class DiscussionSQL {
    public static final Logger logger = Logger.getLogger(DiscussionSQL.class);

    final static String SELECT = "SELECT * FROM Riddles.discussion " +
            "JOIN Riddles.users ON discussion.user_id = users.id_user " +
            "JOIN Riddles.riddles ON discussion.riddle_id = riddles.id_riddle";

    final static String INSERT = "INSERT INTO Riddles.discussion (`id`, `user_id`, `riddle_id`, `rating`, `status`, `text_discus`) VALUES (?,?,?,?,?,?)";

    public static void insertDiscussions(List<Discussion> discussions, String url, String username, String pass) {
        DBProcessor db = new DBProcessor();

        try (Connection con = db.getConnection(url, username, pass)) {
            PreparedStatement preparedInser = con.prepareStatement(INSERT);

            for (Discussion discussion : discussions) {

                preparedInser.setInt(1, discussion.getId());
                preparedInser.setInt(2, discussion.getUser().getId());
                preparedInser.setInt(3, discussion.getRiddle().getId());
                preparedInser.setInt(4, discussion.getRating());
                preparedInser.setString(5, discussion.getStatus());
                preparedInser.setString(6, discussion.getText());

                preparedInser.execute();
            }

            preparedInser = con.prepareStatement(INSERT);

        } catch (SQLException e) {
            logger.error("Ошибка загрузки discussion");
        }
    }

    public static List<Discussion> selectDiscussions(String url, String username, String pass) {
        List<Discussion> discussions = new ArrayList<>();

        DBProcessor db = new DBProcessor();

        try (Connection con = db.getConnection(url, username, pass)) {

            PreparedStatement prepStat = con.prepareStatement(SELECT);

            ResultSet resultSet = prepStat.executeQuery();

            while (resultSet.next()) {
                Discussion discussion = new Discussion();

                discussion.setId(resultSet.getInt("id"));
                discussion.setRating(resultSet.getInt("rating"));
                discussion.setStatus(resultSet.getString("status"));
                discussion.setText(resultSet.getString("text_discus"));

                discussion.setUser(selectUser(resultSet));

                discussion.setRiddle(selectRiddle(resultSet));

                discussions.add(discussion);
            }

        } catch (SQLException e) {
            logger.error("Ошибка выгрузки discussion");
        }

        return discussions;
    }

    private static User selectUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id_user"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setUserName(resultSet.getString("user_name"));
        if (resultSet.getInt("admin_true") == 1) user.setAdmin(true);
        else user.setAdmin(false);

        return user;
    }

    private static Riddle selectRiddle(ResultSet resultSet) throws SQLException {
        Riddle riddle = new Riddle();
        riddle.setId(resultSet.getInt("id_riddle"));
        riddle.setName(resultSet.getString("short_name"));
        riddle.setText(resultSet.getString("text_riddle"));
        riddle.setAnswer(resultSet.getString("answer"));
        riddle.setLevel(resultSet.getInt("level"));
        riddle.setCategory(resultSet.getString("category"));
        riddle.setIdUser(resultSet.getInt("id_usera"));

        return riddle;
    }
}
