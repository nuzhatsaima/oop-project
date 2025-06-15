package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.User;
import utils.DatabaseManager;
import utils.SessionManager;

import java.io.IOException;

public class LoginController {
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    @FXML
    private void handleLogin() throws IOException {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Email and password cannot be empty.");
            return;
        }

        User user = DatabaseManager.validateLogin(email, password);
        if (user != null) {
            SessionManager.setCurrentUser(user);
            loadDashboard(user);
        } else {
            showAlert("Invalid email or password.");
        }
    }

    private void loadDashboard(User user) throws IOException {
        Stage stage = (Stage) emailField.getScene().getWindow();
        String fxml = user.getRole().equals("student") ? "/views/student_dashboard.fxml" : "/views/instructor_dashboard.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        stage.setScene(new Scene(root));
    }

    @FXML
    private void goToSignup() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/signup.fxml"));
        Stage stage = (Stage) emailField.getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

