package com.brimmatech.servlet;

import com.brimmatech.dao.FileHistoryClass;
import com.brimmatech.dao.PaymentHistoryUpdator;
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

@WebServlet("/showHistory")
public class ProceesedFileHistoryServlet extends HttpServlet {
    Gson gson = new Gson();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = request.getReader();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line).append("\n");
        }

        String email = builder.toString().trim();
        try {
            PaymentHistoryUpdator paymentHistoryUpdator = new PaymentHistoryUpdator();
            List<FileHistoryClass> list = paymentHistoryUpdator.paidFiles(email);
            request.setAttribute("list", list);

            String users = "true";
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
