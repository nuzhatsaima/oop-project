package utils;

import models.User;

import java.sql.*;

public class DatabaseManager {
    private static Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:tracker.db");
    }

    // ✅ Create required tables when app starts
    public static void initDB() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            // Users table
            stmt.execute("CREATE TABLE IF NOT EXISTS users (" +
                    "id TEXT PRIMARY KEY, " +
                    "name TEXT NOT NULL, " +
                    "email TEXT UNIQUE NOT NULL, " +
                    "password TEXT NOT NULL, " +
                    "role TEXT NOT NULL)");

            // Assignments table
            stmt.execute("CREATE TABLE IF NOT EXISTS assignments (" +
                    "id TEXT PRIMARY KEY, " +
                    "title TEXT NOT NULL, " +
                    "description TEXT, " +
                    "dueDate TEXT, " +
                    "studentId TEXT, " +
                    "submissionFilePath TEXT, " +
                    "grade TEXT, " +
                    "feedback TEXT, " +
                    "FOREIGN KEY(studentId) REFERENCES users(id))");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Register a new user
    public static boolean registerUser(String name, String email, String password, String role) {
        try (Connection conn = connect()) {
            PreparedStatement check = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
            check.setString(1, email);
            if (check.executeQuery().next()) {
                return false; // User already exists
            }

            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO users (id, name, email, password, role) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, java.util.UUID.randomUUID().toString());
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setString(4, password);
            stmt.setString(5, role);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Validate login credentials
    public static User validateLogin(String email, String password) {
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
