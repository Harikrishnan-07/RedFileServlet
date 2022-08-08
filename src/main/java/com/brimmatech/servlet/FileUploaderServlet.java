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
import java.nio.file.Files;
import java.sql.SQLException;


@MultipartConfig
@WebServlet(urlPatterns = "/files")
public class FileUploaderServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String email = request.getParameter("email");
        String foldername = email.substring(0, email.lastIndexOf("."));

        Part part = request.getPart("file");
        double length = part.getSize();
        double megabyte = Math.round(length / (1024 * 1024));
        String size = String.valueOf(megabyte).concat("mb");
        String name = part.getSubmittedFileName();


        try {
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
            }

            HistoryUpdator historyUpdator = new HistoryUpdator();
            historyUpdator.updatingHistory(email, name, size);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}

