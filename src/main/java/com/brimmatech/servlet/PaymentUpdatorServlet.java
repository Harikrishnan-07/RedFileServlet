package com.brimmatech.servlet;

import com.brimmatech.dao.*;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

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

        try {
            JSONObject jsonObject = new JSONObject(builder.toString());
            String email = jsonObject.getString("email");
            email = email.trim();
            String foldername = email.substring(0, email.lastIndexOf("."));

            String documentname = jsonObject.getString("docName");
            documentname = documentname.trim();

            PaymentUpdator paymentUpdator = new PaymentUpdator();
            paymentUpdator.paymentStatus(email, documentname);

            PaymentHistoryUpdator paymentHistoryUpdator = new PaymentHistoryUpdator();
            List<FileHistoryClass> list = paymentHistoryUpdator.paidFiles(email);
            request.setAttribute("list", list);


        } catch (JSONException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
