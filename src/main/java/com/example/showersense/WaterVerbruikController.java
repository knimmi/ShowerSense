package com.example.showersense;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WaterVerbruikController {

    @FXML
    private BorderPane mainPane;

    @FXML
    private VBox dashboardContent;

    @FXML
    private Label lblActueel;

    @FXML
    private Label lblDag;

    @FXML
    private Label lblMaand;

    @FXML
    private LineChart<String, Number> historieChart;

    @FXML
    private NumberAxis yAxis;

    private int gebruikerID;

    public void setGebruikerID(int gebruikerID) {
        this.gebruikerID = gebruikerID;
        loadChartData();
    }

    @FXML
    private void handleRefresh() {
        historieChart.getData().clear();
        loadChartData();
    }

    private void loadChartData() {
        String sql = "SELECT cd.dataID, cd.water_verbruik " +
                     "FROM consumptie_data cd " +
                     "JOIN sensor s ON cd.sensorID = s.sensorID " +
                     "WHERE s.gebruikerID = ? " +
                     "ORDER BY cd.dataID DESC LIMIT 20";

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Waterverbruik");

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, this.gebruikerID);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String dataId = String.valueOf(rs.getInt("dataID"));
                    double waterVerbruik = rs.getDouble("water_verbruik");
                    series.getData().add(new XYChart.Data<>(dataId, waterVerbruik));
                }
            }

            historieChart.getData().add(series);
            if (historieChart.getStylesheets().isEmpty()) {
                historieChart.getStylesheets().add(getClass().getResource("chart-style.css").toExternalForm());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors (e.g., show an alert)
        }
    }

    @FXML
    private void handleDashboard() {
        mainPane.setCenter(dashboardContent);
    }

    @FXML
    private void handleAccount() {
        loadPage("account.fxml");
    }

    @FXML
    private void handleSensoren() {
        loadPage("sensoren.fxml");
    }

    @FXML
    private void handleFeedback() {
        loadPage("feedback.fxml");
    }

    private void loadPage(String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Node page = fxmlLoader.load();

            // Pass the gebruikerID and mainPane to the controller if it needs it
            Object controller = fxmlLoader.getController();
            if (controller instanceof AccountController) {
                ((AccountController) controller).setGebruikerID(this.gebruikerID);
            } else if (controller instanceof SensorenController) {
                ((SensorenController) controller).setGebruikerID(this.gebruikerID);
                ((SensorenController) controller).setMainPane(this.mainPane);
            }

            mainPane.setCenter(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
