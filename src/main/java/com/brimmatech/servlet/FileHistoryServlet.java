package com.brimmatech.servlet;

import com.brimmatech.dao.FileHistoryClass;
import com.brimmatech.dao.FileHistoryCollector;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/filehistory")
public class FileHistoryServlet extends HttpServlet {
    private Gson gson = new Gson();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = "oom@gmail.com";
//        String email = request.getParameter("email");
//        request.setAttribute("email", email);

        FileHistoryCollector filesHistoryCollector = new FileHistoryCollector();
        try {
            List<FileHistoryClass> list = filesHistoryCollector.historyCollector(email);
            request.setAttribute("list", list);
            System.out.println(list);
            String userJsonString = this.gson.toJson(list);
            System.out.println("true");
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
