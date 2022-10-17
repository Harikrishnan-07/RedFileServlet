package com.brimmatech.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CurrentStatusCollector {

    public List<FileHistoryClass> statusCollector(String email) throws SQLException, IOException {


        List<FileHistoryClass> list = new LinkedList<>();
        Connection connection = DatabaseConnection.initializeDatabase();

        PreparedStatement statement = connection.prepareStatement("Select * from uploadhistory where email=? and status = 'In progress' OR status = 'Completed'");
        statement.setString(1, email);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            FileHistoryClass fileHistoryClass = new FileHistoryClass();
            fileHistoryClass.setEmail(resultSet.getString("email"));
            fileHistoryClass.setDocumentName(resultSet.getString("documentname"));
            fileHistoryClass.setFilesize(resultSet.getString("documentsize"));
            fileHistoryClass.setStatus(resultSet.getString("status"));
            list.add(fileHistoryClass);
        }
        return list;
    }
    }


