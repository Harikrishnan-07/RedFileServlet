package com.brimmatech.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.Scanner;

public class RedFileDetector {
    public void fileDetector(String email) throws IOException, JSONException, SQLException {

        String status ="Completed";

        URL url = new URL("http://20.228.123.187/storageservice/v1/changes");
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

            ObjectMapper mapper = new ObjectMapper();

            FileDetectorClass[] list = mapper.readValue(builder.toString(), FileDetectorClass[].class);

            for (FileDetectorClass fileDetectorClass : list) {
                String id = fileDetectorClass.getId();
                String name = fileDetectorClass.getName();
                String filepath = fileDetectorClass.getFilePath();

                String filename = id.substring(0, id.lastIndexOf("."));

                if (name.equals(email)) {

                    RedFileHistoryUpdator redFileHistoryUpdator = new RedFileHistoryUpdator();
                    redFileHistoryUpdator.uploadStatus(email,filename,status);

//                    RedFileDownloader redFileDownloader = new RedFileDownloader();
//                    redFileDownloader.fileDownloader(id, name, filepath);

                }
            }
        }


    }

}
