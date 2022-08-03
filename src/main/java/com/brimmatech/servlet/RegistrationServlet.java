package com.brimmatech.servlet;

import com.brimmatech.dao.Registration;
import com.brimmatech.dao.ValidatingService;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@MultipartConfig
@WebServlet("/registration")

public class RegistrationServlet extends HttpServlet {
    private Gson gson = new Gson();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      //  response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");

        BufferedReader br = request.getReader();
        StringBuilder builder = new StringBuilder();
        String line;
               while ((line = br.readLine()) != null) {
                    builder.append(line).append("\n");
                }

             ValidatingService validatingService = new ValidatingService();
             Registration register = new Registration();
            try {
                if (validatingService.validate(builder.toString())){
                    String users = "true";
                    String userJsonString = this.gson.toJson(users);
                    System.out.println("true");
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.print(userJsonString);
                    out.flush();
                } else {
                    register.gettingDetail(builder.toString());
                    String user = "false";
                    String userJsonString = this.gson.toJson(user);
                    System.out.println("false");
                    PrintWriter out = response.getWriter();
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out.print(userJsonString);
                    out.flush();
                }
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
    }
}