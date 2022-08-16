package com.brimmatech.servlet;


import com.brimmatech.dao.HistoryUpdator;



import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.*;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;


@MultipartConfig
@WebServlet(urlPatterns = "/files")
public class FileUploaderServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

//        StringBuilder builder = new StringBuilder();
//        BufferedReader bufferedReader = request.getReader();
//        String line;
//
//        while ((line = bufferedReader.readLine()) != null) {
//            builder.append(line).append("\n");
//        }
//        try{
//        System.out.println(builder.toString());
//        JSONObject object = new JSONObject(builder.toString());

        String email = request.getParameter("email");
        System.out.println(email);
        Part part = request.getPart("file");

        // System.out.println(part.length());
//            String email = object.getString("mail");
//            System.out.println(email);


        //Part part = (Part) object.get("data");
        // Part part = request.getPart("data");


        //     System.out.println(part);

        try {
            String foldername = email.substring(0, email.lastIndexOf("."));
            String status = "Not paid";

            double length = part.getSize();
            double megabyte = length / (1024 * 1024);
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.CEILING);
            double megabyte1 = Double.parseDouble(df.format(megabyte));
            System.out.println(part.getSize());
            String size = String.valueOf(megabyte1).concat(" MB");
            String name = part.getSubmittedFileName();
            System.out.println(part.getSubmittedFileName());

            InputStream input = part.getInputStream();
            BufferedInputStream buffer = new BufferedInputStream(input);
            String path = ("C:/Uploadedfiles/" + foldername);
            File newfile = new File(path);
            newfile.mkdirs();

            OutputStream output = new FileOutputStream(path + "/" + name);
            byte[] content = new byte[4 * 1024];
            int read;
            while ((read = buffer.read(content, 0, content.length)) != -1) {
                output.write(content, 0, read);
                output.flush();
            }
            output.close();

            HistoryUpdator historyUpdator = new HistoryUpdator();
            historyUpdator.updatingHistory(email, name, size, status);


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);


        }

    }
}