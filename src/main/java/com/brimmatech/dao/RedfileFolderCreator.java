package com.brimmatech.dao;



import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;



public class RedfileFolderCreator {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://20.228.123.187/storageservice/v1/folders");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept","application/json");
            connection.setRequestProperty("Content-Type","application/json");
            String data = "{\n \"name\":\"gokulkrish@brimmatech.com\"\n}";
            connection.connect();
            byte[] out = data.getBytes(StandardCharsets.UTF_8);
            OutputStream stream = connection.getOutputStream();
            stream.write(out);
            System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage());

            StringBuilder builder = new StringBuilder();
                Scanner scanner = new Scanner((InputStream) connection.getContent());
                while (scanner.hasNext()) {
                    builder.append(scanner.nextLine());
                }
                scanner.close();
                System.out.println(builder);
                JSONObject jsonObject = new JSONObject(builder.toString());
              String folderid =   jsonObject.getString("id");
                System.out.println(folderid);

                connection.disconnect();

        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }


    }
}