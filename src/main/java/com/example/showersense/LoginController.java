package com.example.showersense;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    private int loggedInGebruikerID;

    @FXML
    protected void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (validateLogin(email, password)) {
            // Login succesvol!
            switchToDashboard(event);
        } else {
            // Foutmelding tonen
            errorLabel.setVisible(true);
            errorLabel.setText("Verkeerd e-mailadres of wachtwoord!");
        }
    }

    @FXML
    protected void handleShowRegister(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Bewaar de huidige venstergrootte
            double width = stage.getScene().getWidth();
            double height = stage.getScene().getHeight();

            Scene scene = new Scene(fxmlLoader.load(), width, height);
            stage.setTitle("Nieuwe Gebruiker");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validateLogin(String email, String password) {
        String sql = "SELECT gebruikerID FROM gebruiker WHERE email = ? AND wachtwoord = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    this.loggedInGebruikerID = rs.getInt("gebruikerID");
                    return true;
                }
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            errorLabel.setText("Databasefout!");
            errorLabel.setVisible(true);
            return false;
        }
    }

    private void switchToDashboard(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("real_time_verbruik.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Bewaar de huidige venstergrootte
            double width = stage.getScene().getWidth();
            double height = stage.getScene().getHeight();

            // Laad de nieuwe scène met de opgeslagen grootte
            Scene scene = new Scene(fxmlLoader.load(), width, height);

            WaterVerbruikController controller = fxmlLoader.getController();
            controller.setGebruikerID(this.loggedInGebruikerID);

            stage.setTitle("Waterverbruik Monitor");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            errorLabel.setText("Fout bij laden dashboard!");
            errorLabel.setVisible(true);
        }
    }
}
