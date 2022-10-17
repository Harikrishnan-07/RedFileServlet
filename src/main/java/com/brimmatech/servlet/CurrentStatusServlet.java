package com.brimmatech.servlet;

import com.brimmatech.dao.CurrentStatusCollector;
import com.brimmatech.dao.FileHistoryClass;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
@WebServlet("/currentstatus")
public class CurrentStatusServlet extends HttpServlet {
        Gson gson = new Gson();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        StringBuilder builder = new StringBuilder();
        BufferedReader br = request.getReader();
        String line;
        while ((line = br.readLine()) != null) {
            builder.append(line).append("\n");
        }

            String email = builder.toString().trim();

        CurrentStatusCollector currentStatusCollector = new CurrentStatusCollector();
        try {
           List<FileHistoryClass> list = currentStatusCollector.statusCollector(email);


            String userJsonString = this.gson.toJson(list);
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            out.print(userJsonString);
            out.flush();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }





}
