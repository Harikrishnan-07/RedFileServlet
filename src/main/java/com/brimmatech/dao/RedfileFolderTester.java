package com.brimmatech.dao;

import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class RedfileFolderTester {
    Gson gson = new Gson();

    public void folderTester(String email) throws IOException, JSONException {
        URL url = new URL("http://20.228.123.187/storageservice/v1/folders/" + email);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        String name = "{\"name\":\""+email+"\"}";
        int responsecode = connection.getResponseCode();
        if (responsecode != 200) {

            RedfileFolderCreator redfileFolderCreator = new RedfileFolderCreator();
            redfileFolderCreator.folderCreator(name);

        }

    }

}