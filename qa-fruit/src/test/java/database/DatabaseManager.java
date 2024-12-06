package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

    /**
     * Подключение к БД
     */
    private final String url = "jdbc:h2:tcp://localhost:9092/mem:testdb";
    private final String user = "user";
    private final String password = "pass";
    private Connection connection;

    public Connection getConnection() {
        try {
            this.connection = java.sql.DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.connection;
    }

    /**
     * Получить количество строк по foodName
     */
    public int getFoodCount(String foodName) {
        String sql = "SELECT COUNT(*) FROM FOOD WHERE FOOD_NAME = ?";
        int count = 0;

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, foodName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    /**
     * Метод удаления товара
     */
    public boolean deleteLastAddedFood(String foodName) {
        String sql = "DELETE FROM FOOD WHERE FOOD_ID = (SELECT MAX(FOOD_ID) FROM FOOD WHERE FOOD_NAME = ?)";
        boolean isDeleted = false;

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, foodName);
            int rowsAffected = preparedStatement.executeUpdate();
            isDeleted = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isDeleted;
    }
}
