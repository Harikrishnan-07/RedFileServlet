package com.brimmatech.servlet;


import com.brimmatech.dao.FilesUploadtoRedfile;
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


        String email = request.getParameter("email");

        Part part = request.getPart("file");

        try {
            String foldername = email.substring(0, email.lastIndexOf("."));
            String status = "Not paid";

            double length = part.getSize();
            double megabyte = length / (1024 * 1024);
            DecimalFormat df = new DecimalFormat("#.##");
            double megabyte1 = Double.parseDouble(df.format(megabyte));
            System.out.println(part.getSize());
            String size = String.valueOf(megabyte1).concat(" MB");
            String name = part.getSubmittedFileName();
            System.out.println(part.getSubmittedFileName());
            System.out.println("content type"+part.getContentType());

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

            FilesUploadtoRedfile filesUploadtoRedfile = new FilesUploadtoRedfile();
            filesUploadtoRedfile.fileUploader(email,name);


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

    }
}