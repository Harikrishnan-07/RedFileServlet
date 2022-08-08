package com.brimmatech.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PaymentHistoryUpdator {
    public List<FileHistoryClass> paidFiles(String email) throws SQLException, IOException {
        Connection connection = DatabaseConnection.initializeDatabase();
        List<FileHistoryClass> list = new LinkedList<>();
        String status = "Success";
        PreparedStatement preparedStatement = connection.prepareStatement("Select * from uploadhistory where email=? and status=?");
        preparedStatement.setString(1,email);
        preparedStatement.setString(2,status);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next())
        {
            FileHistoryClass fileHistoryClass = new FileHistoryClass();
            fileHistoryClass.setEmail(resultSet.getString("email"));
            fileHistoryClass.setDate(resultSet.getString("paymentdate"));
            fileHistoryClass.setDocumentName(resultSet.getString("documentname"));
            fileHistoryClass.setFilesize(resultSet.getString("documentsize"));
            fileHistoryClass.setStatus(resultSet.getString("status"));
            list.add(fileHistoryClass);
        }

     return list;
    }
}
