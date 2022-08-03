package com.brimmatech.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.brimmatech.dao.DatabaseConnection.initializeDatabase;

public class ValidatingService {
    public boolean validate(String email) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = initializeDatabase();
        PreparedStatement statement = connection.prepareStatement("Select * from userprofile where email=?");
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return true;
        }
        return false;
    }
}
