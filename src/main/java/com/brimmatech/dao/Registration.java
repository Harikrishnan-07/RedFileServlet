package com.brimmatech.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Registration {
    public void gettingDetail(String email) throws ClassNotFoundException, SQLException, IOException {

        Connection connection = DatabaseConnection.initializeDatabase();
        PreparedStatement statement = connection.prepareStatement("Insert into userprofile(email) values(?) ");
        statement.setString(1, email);
        statement.executeUpdate();
    }
}
