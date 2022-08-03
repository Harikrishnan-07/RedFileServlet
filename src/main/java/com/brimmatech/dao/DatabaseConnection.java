package com.brimmatech.dao;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class DatabaseConnection {
    public static Connection initializeDatabase() throws SQLException, IOException {

        Properties properties = new Properties();
        InputStream inputStream = DatabaseConnection.class.getClassLoader().getResourceAsStream("Connection.properties");
        properties.load(inputStream);

        String dbclassname = properties.getProperty("driverClassName");
        String connectionurl = properties.getProperty("connectionUrl");
        String dbuser = properties.getProperty("dbUser");
        String dbpwd = properties.getProperty("dbPwd");

        try {
            Class.forName(dbclassname);
        } catch (ClassNotFoundException classNotFoundException) {
            throw new RuntimeException(classNotFoundException);
        }
        Connection connection = DriverManager.getConnection(connectionurl, dbuser, dbpwd);
        return connection;
    }
}
