package com.example.showersense;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SensorDetailController {

    @FXML
    private TextField locatieField;

    @FXML
    private TextField statusField;

    @FXML
    private Label messageLabel;

    private int sensorID;
    private int gebruikerID;
    private BorderPane mainPane;

    public void setSensorData(int sensorID, int gebruikerID, BorderPane mainPane) {
        this.sensorID = sensorID;
        this.gebruikerID = gebruikerID;
        this.mainPane = mainPane;
        loadSensorDetails();
    }

    private void loadSensorDetails() {
        String sql = "SELECT Locatie, Status FROM sensor WHERE sensorID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, this.sensorID);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    locatieField.setText(rs.getString("Locatie"));
                    statusField.setText(rs.getString("Status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage("Fout bij laden van sensorgegevens.", Color.RED);
        }
    }

    @FXML
    private void handleUpdate() {
        String sql = "UPDATE sensor SET Locatie = ? WHERE sensorID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, locatieField.getText());
            pstmt.setInt(2, this.sensorID);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                showMessage("Locatie succesvol bijgewerkt!", Color.GREEN);
            } else {
                showMessage("Bijwerken mislukt.", Color.RED);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage("Databasefout: " + e.getMessage(), Color.RED);
        }
    }

    @FXML
    private void handleDelete() {
        String sql = "DELETE FROM sensor WHERE sensorID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, this.sensorID);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                showMessage("Sensor succesvol verwijderd!", Color.GREEN);
                locatieField.setDisable(true);
                statusField.setDisable(true);
            } else {
                showMessage("Verwijderen mislukt.", Color.RED);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage("Databasefout: " + e.getMessage(), Color.RED);
        }
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sensoren.fxml"));
            Node page = fxmlLoader.load();

            SensorenController controller = fxmlLoader.getController();
            controller.setGebruikerID(this.gebruikerID);
            controller.setMainPane(this.mainPane);

            mainPane.setCenter(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMessage(String message, Color color) {
        messageLabel.setText(message);
        messageLabel.setTextFill(color);
        messageLabel.setVisible(true);
    }
}
