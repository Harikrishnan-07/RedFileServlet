package com.brimmatech.servlet;


import com.brimmatech.dao.FileHistoryClass;
import com.brimmatech.dao.ProcessedFileHistory;
import com.brimmatech.dao.RedFileDetector;
import com.brimmatech.dao.RedFileDownloader;
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

@WebServlet("/receive")
public class ReceivedFileServlet extends HttpServlet {
    Gson gson = new Gson();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        StringBuilder builder = new StringBuilder();
        BufferedReader br = request.getReader();
        String line;
        while ((line = br.readLine()) != null) {
            builder.append(line).append("\n");
        }


         try{
            String email = builder.toString().trim();


            RedFileDetector redFileDetector = new RedFileDetector();
            redFileDetector.fileDetector(email);

            ProcessedFileHistory processedFileHistory = new ProcessedFileHistory();
            List<FileHistoryClass> list =  processedFileHistory.processFileList(email);
             System.out.println(list);
            String userJsonString = this.gson.toJson(list);
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(userJsonString);
            out.flush();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

}
