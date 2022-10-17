package com.brimmatech.servlet;

import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
@WebServlet("/processfile")
public class ProcessedFileServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader br = request.getReader();
        String line;
        while ((line = br.readLine()) != null) {
            builder.append(line).append("\n");
        }
        try {
            JSONObject jsonObject = new JSONObject(builder.toString());
            String email = jsonObject.getString("email");
            String filename = jsonObject.getString("docName");


            String processfile = filename.concat(".zip");
            String name = email.substring(0, email.lastIndexOf("."));
            String path = "C:\\Paidfiles\\" + name + "\\" + processfile;
            FileInputStream inputStream = new FileInputStream(path);
            System.out.println("    input");


            ServletOutputStream stream = response.getOutputStream();
            response.setContentType("application/zip");


            response.setHeader("Content-Disposition", "attachment; filename=" + processfile);
            stream.write(inputStream.readAllBytes());
            stream.flush();
            stream.close();

        }catch(JSONException exception) {
            System.out.println("invalid input");
        }
    }
}
