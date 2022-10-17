package com.brimmatech.servlet;


import com.brimmatech.dao.FilesUploadtoRedfile;
import com.brimmatech.dao.RedFileHistoryUpdator;
import com.brimmatech.dao.RedfileFolderTester;
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

@WebServlet("/redfileupload")
public class RedFileUploadServlet extends HttpServlet {
    Gson gson = new Gson();

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
            String status = "In progress";

            RedfileFolderTester redfileFolderTester = new RedfileFolderTester();
            redfileFolderTester.folderTester(email);

            FilesUploadtoRedfile filesUploadtoRedfile = new FilesUploadtoRedfile();
            String success = filesUploadtoRedfile.fileUploader(email, filename);

            RedFileHistoryUpdator redFileHistoryUpdator = new RedFileHistoryUpdator();
            redFileHistoryUpdator.uploadStatus(email,filename,status);

            String userJsonString = this.gson.toJson(success);
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(userJsonString);
            out.flush();

        } catch (JSONException ex) {
            System.out.println("invalid input");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

