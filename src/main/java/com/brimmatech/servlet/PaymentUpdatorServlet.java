package com.brimmatech.servlet;

import com.brimmatech.dao.FileHistoryClass;
import com.brimmatech.dao.PaymentHistoryUpdator;
import com.brimmatech.dao.PaymentUpdator;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/payment")
public class PaymentUpdatorServlet extends HttpServlet {
    private Gson gson = new Gson();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = request.getReader();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line).append("\n");
        }
        System.out.println(builder.toString());
        try {
            JSONObject jsonObject = new JSONObject(builder.toString());


            String email = jsonObject.getString("emailId");
            email = email.trim();
            System.out.println(email);
            String documentname = jsonObject.getString("docName");
            documentname = documentname.trim();
            System.out.println(documentname);

            PaymentUpdator paymentUpdator = new PaymentUpdator();
            paymentUpdator.paymentStatus(email, documentname);

            PaymentHistoryUpdator paymentHistoryUpdator = new PaymentHistoryUpdator();
            List<FileHistoryClass> list = paymentHistoryUpdator.paidFiles(email);
            System.out.println(list);

            String users = "true";
            String userJsonString = this.gson.toJson(users);
            System.out.println("true");
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(userJsonString);
            out.flush();
        } catch (JSONException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
