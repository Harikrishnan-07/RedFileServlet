package com.brimmatech.servlet;

import com.brimmatech.dao.FileHistoryClass;
import com.brimmatech.dao.FileHistoryCollector;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/filehistory")
public class FileHistoryServlet extends HttpServlet {
    private Gson gson = new Gson();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuilder builder = new StringBuilder();
        BufferedReader  br  = request.getReader();
        String line;
        while ((line = br.readLine()) != null) {
            builder.append(line).append("\n");
        }
        String email= builder.toString().trim();


        FileHistoryCollector filesHistoryCollector = new FileHistoryCollector();
        try {
            List<FileHistoryClass> list = filesHistoryCollector.historyCollector(email);
            request.setAttribute("list", list);

            String userJsonString = this.gson.toJson(list);
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(userJsonString);
            out.flush();

        } catch (SQLException|ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
