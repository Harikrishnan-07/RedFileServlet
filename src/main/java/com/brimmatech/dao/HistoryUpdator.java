package com.brimmatech.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryUpdator {
    public void updatingHistory(String email, String filename) throws SQLException, ClassNotFoundException, IOException {

        Connection connection = DatabaseConnection.initializeDatabase();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String newdate = formatter.format(date);
        System.out.println(email);
        PreparedStatement statement = connection.prepareStatement("Insert into uploadhistory (email,date,documentname) values(?,?,?)");
        statement.setString(1, email);
        statement.setString(2, newdate);
        statement.setString(3, filename);
        statement.executeUpdate();
    }
}
