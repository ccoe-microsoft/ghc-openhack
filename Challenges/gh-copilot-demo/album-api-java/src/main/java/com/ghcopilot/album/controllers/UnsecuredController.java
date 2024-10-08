package com.ghcopilot.album.controllers;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UnsecuredController {

    private String connectionString = "";

    public String readFile(String userInput) {
        StringBuilder content = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(userInput);
             InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
             BufferedReader br = new BufferedReader(isr)) {

            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public int getProduct(String productName) {
        int productId = -1;
        try (Connection connection = DriverManager.getConnection(connectionString);
             Statement statement = connection.createStatement()) {

            String query = "SELECT ProductId FROM Products WHERE ProductName = '" + productName + "'";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                productId = resultSet.getInt("ProductId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productId;
    }

    public void getObject() {
        try {
            Object o = null;
            o.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}