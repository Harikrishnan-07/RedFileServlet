package com.brimmatech.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletingService {
    public void fileDeletor(String email,String documentname) throws SQLException, IOException {

        Connection connection = DatabaseConnection.initializeDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement("Delete from uploadhistory where email=? AND documentname=?");
        preparedStatement.setString(1,email);
        preparedStatement.setString(2,documentname);
        preparedStatement.executeUpdate();
    }
}
