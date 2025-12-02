package com.example.showersense;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label messageLabel;

    private int gebruikerID;

    public void setGebruikerID(int gebruikerID) {
        this.gebruikerID = gebruikerID;
        loadUserData();
    }

    private void loadUserData() {
        String sql = "SELECT naam, email FROM gebruiker WHERE gebruikerID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, this.gebruikerID);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    nameField.setText(rs.getString("naam"));
                    emailField.setText(rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage("Fout bij laden van gegevens.", Color.RED);
        }
    }

    @FXML
    private void handleSaveChanges() {
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!newPassword.isEmpty() && !newPassword.equals(confirmPassword)) {
            showMessage("De wachtwoorden komen niet overeen.", Color.RED);
            return;
        }

        StringBuilder sql = new StringBuilder("UPDATE gebruiker SET naam = ?, email = ?");
        if (!newPassword.isEmpty()) {
            sql.append(", wachtwoord = ?");
        }
        sql.append(" WHERE gebruikerID = ?");

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            pstmt.setString(1, nameField.getText());
            pstmt.setString(2, emailField.getText());

            if (!newPassword.isEmpty()) {
                pstmt.setString(3, newPassword);
                pstmt.setInt(4, this.gebruikerID);
            } else {
                pstmt.setInt(3, this.gebruikerID);
            }

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                showMessage("Gegevens succesvol bijgewerkt!", Color.GREEN);
                newPasswordField.clear();
                confirmPasswordField.clear();
            } else {
                showMessage("Bijwerken mislukt.", Color.RED);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage("Databasefout: " + e.getMessage(), Color.RED);
        }
    }

    private void showMessage(String message, Color color) {
        messageLabel.setText(message);
        messageLabel.setTextFill(color);
        messageLabel.setVisible(true);
    }
}
