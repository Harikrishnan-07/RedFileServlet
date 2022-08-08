package com.brimmatech.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PaymentUpdator {
    public void paymentStatus(String email, String documentname) throws SQLException, IOException {
       Connection connection = DatabaseConnection.initializeDatabase();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String paymentdate = formatter.format(date);
        String status = "Success";
        PreparedStatement preparedStatement = connection.prepareStatement("Update uploadhistory set paymentdate=? , status=? where email=? and documentname=? ");
        preparedStatement.setString(1,paymentdate);
        preparedStatement.setString(2,status);
        preparedStatement.setString(3,email);
        preparedStatement.setString(4,documentname);
        preparedStatement.executeUpdate();
        System.out.println("successfully entered");
    }
}
