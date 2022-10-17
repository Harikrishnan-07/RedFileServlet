package com.brimmatech.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class RedFileHistoryUpdator {


        public void uploadStatus(String email, String documentname,String status) throws SQLException, IOException {
            Connection connection = DatabaseConnection.initializeDatabase();

            PreparedStatement preparedStatement = connection.prepareStatement("Update uploadhistory set  status=? where email=? and documentname=? ");

            preparedStatement.setString(1, status);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, documentname);
            preparedStatement.executeUpdate();
        }


    }








