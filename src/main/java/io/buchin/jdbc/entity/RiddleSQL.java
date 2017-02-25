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
public class RiddleSQL {
    public static final Logger logger = Logger.getLogger(Riddle.class);

    final static String SELECT = "SELECT * FROM Riddles.riddles";
    final static String INSERT = "INSERT INTO Riddles.riddles (`id_riddle`, `short_name`, `text_riddle`, `answer`, `level`, `category`, `id_usera`) VALUES (?,?,?,?,?,?,?)";

    public static void insertRiddles(List<Riddle> riddles, String url, String username, String pass) {
        DBProcessor db = new DBProcessor();

        try (Connection con = db.getConnection(url, username, pass)) {
            PreparedStatement preparedInser = con.prepareStatement(INSERT);

            for (Riddle riddle : riddles) {

                preparedInser.setInt(1, riddle.getId());
                preparedInser.setString(2, riddle.getName());
                preparedInser.setString(3, riddle.getText());
                preparedInser.setString(4, riddle.getAnswer());
                preparedInser.setInt(5, riddle.getLevel());
                preparedInser.setString(6, riddle.getCategory());
                preparedInser.setInt(7, riddle.getIdUser());

                preparedInser.execute();
            }

            preparedInser = con.prepareStatement(INSERT);

        } catch (SQLException e) {
            logger.error("Ошибка загрузки discussion");
        }
    }

    public static List<Riddle> selectRiddles(String url, String username, String pass) {
        List<Riddle> riddles = new ArrayList<>();

        DBProcessor db = new DBProcessor();

        try (Connection con = db.getConnection(url, username, pass)) {

            PreparedStatement prepStat = con.prepareStatement(SELECT);

            ResultSet resultSet = prepStat.executeQuery();

            while (resultSet.next()) {
                Riddle riddle = new Riddle();

                riddle.setId(resultSet.getInt("id_riddle"));
                riddle.setName(resultSet.getString("short_name"));
                riddle.setText(resultSet.getString("text_riddle"));
                riddle.setAnswer(resultSet.getString("answer"));
                riddle.setLevel(resultSet.getInt("level"));
                riddle.setCategory(resultSet.getString("category"));
                riddle.setIdUser(resultSet.getInt("id_usera"));

                riddles.add(riddle);
            }

        } catch (SQLException e) {
            logger.error("Ошибка выгрузки discussion");
        }

        return riddles;
    }

}
