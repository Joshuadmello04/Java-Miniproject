package Home;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHelper {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "";

    public static double getBalanceFromDatabase(String username, String password) {
        double balance = 0.0;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD)) {
            String sqlQuery = "SELECT balance FROM user WHERE username = ? AND atmpin = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (var resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        balance = resultSet.getDouble("balance");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return balance;
    }

    public static void updateBalanceInDatabase(String username, double newBalance) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD)) {
            String sqlQuery = "UPDATE user SET balance = ? WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setDouble(1, newBalance);
                preparedStatement.setString(2, username);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deposit(String username, double amount) {
        double currentBalance = getBalanceFromDatabase(username, getPasswordFromDatabase(username));
        double newBalance = currentBalance + amount;
        updateBalanceInDatabase(username, newBalance);
    }

    public static void withdraw(String username, double amount) {
        double currentBalance = getBalanceFromDatabase(username, getPasswordFromDatabase(username));
        double newBalance = currentBalance - amount;
        updateBalanceInDatabase(username, newBalance);
    }

    private static String getPasswordFromDatabase(String username) {
        String password = null;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD)) {
            String sqlQuery = "SELECT atmpin FROM user WHERE username = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setString(1, username);

                try (var resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        password = resultSet.getString("atmpin");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return password;
    }
}
