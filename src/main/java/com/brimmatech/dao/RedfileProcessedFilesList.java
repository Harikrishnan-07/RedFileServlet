package com.brimmatech.dao;



import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class RedfileProcessedFilesList {
    public static void main(String[] args) {
        try {

            URL url = new URL("http://localhost:8080/Assets/assigned");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responsecode = connection.getResponseCode();
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponsecode" + responsecode);
            } else {
                StringBuilder builder = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    builder.append(scanner.nextLine());
                }
                scanner.close();
                System.out.println(builder);

            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
