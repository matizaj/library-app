package com.ps.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseRepository {
    protected Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/library";
        String username= "root";
        String password = "pass";

        return DriverManager.getConnection(url, username, password);
    }
}
