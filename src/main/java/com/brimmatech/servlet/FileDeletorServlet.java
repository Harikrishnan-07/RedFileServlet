package com.brimmatech.servlet;

import com.brimmatech.dao.DeletingService;
import com.brimmatech.dao.FileDeletor;
import org.json.JSONException;
import org.json.JSONObject;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete")
public class FileDeletorServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = request.getReader();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line).append("\n");
            System.out.println(builder.toString());
        }
        try {
            JSONObject jsonObject = new JSONObject(builder.toString());
            String email = jsonObject.getString("emailId");
            String foldername = email.substring(0, email.lastIndexOf("."));
            String documentname = jsonObject.getString("docName");

            DeletingService deletingService = new DeletingService();
            deletingService.fileDeletor(email, documentname);

//            String path = ("C:/Uploadedfiles/" + foldername + "/" + documentname);
//            File deletefile = new File(path);
//            if (deletefile.exists()) {
//                deletefile.delete();
//            }

            FileDeletor fileDeletor = new FileDeletor();
            fileDeletor.fileTobeDelete(foldername, documentname);

        } catch (SQLException e) {
            System.out.println("error");
        } catch (JSONException e) {
            System.out.println("error");
        }
    }
}
