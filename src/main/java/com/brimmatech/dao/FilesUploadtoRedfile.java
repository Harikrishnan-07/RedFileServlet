package com.brimmatech.dao;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;


public class FilesUploadtoRedfile {
    public String fileUploader(String email, String filename) {
        try {
            String foldername = email.substring(0, email.lastIndexOf("."));
            String inputFile = "C:\\Uploadedfiles\\" + foldername + "\\" + filename;
            InputStream inputStream = new FileInputStream(inputFile);

            byte[] filetobyte = inputStream.readAllBytes();
            String s = Base64.getEncoder().encodeToString(filetobyte);


            URL url = new URL("http://20.228.123.187/storageservice/v1/folders/" + email + "/files/" + filename);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fileContentBytes", s);
            jsonObject.put("contentType", "application/pdf");
            connection.connect();


            String data = jsonObject.toString();

            byte[] b = new byte[13 * 1024];
            b = data.getBytes(StandardCharsets.UTF_8);

            OutputStream stream = connection.getOutputStream();
            stream.write(b);
            stream.flush();

            System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage());

            StringBuilder builder = new StringBuilder();
            Scanner scanner = new Scanner((InputStream) connection.getContent());
            while (scanner.hasNext()) {
                builder.append(scanner.nextLine());
            }
            scanner.close();
            JSONObject jsonObject1 = new JSONObject(builder.toString());

            String response = jsonObject1.toString();
            connection.disconnect();
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}

