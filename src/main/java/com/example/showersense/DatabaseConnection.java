package com.example.showersense;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String DATABASE_HOST = dotenv.get("DATABASE_HOST");
    private static final String DATABASE_PORT = dotenv.get("DATABASE_PORT");
    private static final String DATABASE_NAME = dotenv.get("DATABASE_NAME");
    private static final String DATABASE_USER = dotenv.get("DATABASE_USER");
    private static final String DATABASE_PASSWORD = dotenv.get("DATABASE_PASSWORD");

    private static final String JDBC_URL = "jdbc:mysql://" + DATABASE_HOST + ":" + DATABASE_PORT + "/" + DATABASE_NAME;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DATABASE_USER, DATABASE_PASSWORD);
    }
}
