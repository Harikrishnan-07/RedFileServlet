package com.brimmatech.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class FileHistoryCollector {
    public List<FileHistoryClass> historyCollector(String email) throws SQLException, ClassNotFoundException, IOException {

        List<FileHistoryClass> list = new LinkedList<>();
        Connection connection = DatabaseConnection.initializeDatabase();
        String status = "Not paid";
        PreparedStatement statement = connection.prepareStatement("Select * from uploadhistory where email=? and status=? ");
        statement.setString(1, email);
        statement.setString(2, status);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            FileHistoryClass fileHistoryClass = new FileHistoryClass();
            fileHistoryClass.setEmail(resultSet.getString("email"));
            fileHistoryClass.setDate(resultSet.getString("date"));
            fileHistoryClass.setDocumentName(resultSet.getString("documentname"));
            fileHistoryClass.setFilesize(resultSet.getString("documentsize"));
            list.add(fileHistoryClass);
        }
        return list;
    }


}
