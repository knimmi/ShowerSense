package com.example.showersense;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SensorenController {

    @FXML
    private TilePane sensorTilePane;

    private int gebruikerID;
    private BorderPane mainPane;

    public void setGebruikerID(int gebruikerID) {
        this.gebruikerID = gebruikerID;
        loadSensorData();
    }

    public void setMainPane(BorderPane mainPane) {
        this.mainPane = mainPane;
    }

    private void loadSensorData() {
        String sql = "SELECT sensorID, Status, Locatie FROM sensor WHERE gebruikerID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, this.gebruikerID);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int sensorID = rs.getInt("sensorID");
                    String status = rs.getString("Status");
                    String locatie = rs.getString("Locatie");
                    sensorTilePane.getChildren().add(createSensorCard(sensorID, status, locatie));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }
    }

    private VBox createSensorCard(int sensorID, String status, String locatie) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20));
        card.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5); -fx-cursor: hand;");

        card.setOnMouseClicked(event -> handleCardClick(sensorID));

        Label locatieLabel = new Label(locatie);
        locatieLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Label iconLabel = new Label("🚿"); // Shower icon
        iconLabel.setFont(Font.font(40));

        HBox statusBox = new HBox(5);
        statusBox.setAlignment(Pos.CENTER);
        Circle statusCircle = new Circle(5);
        statusCircle.setFill(status.equalsIgnoreCase("actief") ? Color.GREEN : Color.RED);
        Label statusLabel = new Label(status);

        statusBox.getChildren().addAll(statusCircle, statusLabel);
        card.getChildren().addAll(iconLabel, locatieLabel, statusBox);

        return card;
    }

    private void handleCardClick(int sensorID) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sensor_detail.fxml"));
            Node page = fxmlLoader.load();

            SensorDetailController controller = fxmlLoader.getController();
            controller.setSensorData(sensorID, this.gebruikerID, this.mainPane);

            mainPane.setCenter(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
