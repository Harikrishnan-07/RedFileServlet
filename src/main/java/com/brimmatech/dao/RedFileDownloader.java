package com.brimmatech.dao;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class RedFileDownloader {

    public void fileDownloader(String filename,String foldername,String filepath) throws IOException {
            URL url = new URL("http://20.228.123.187/storageservice/v1/files/"+filename+"?loanNumber="+foldername+"&filePath="+filepath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responsecode = connection.getResponseCode();
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponsecode" + responsecode);
            } else {

                BufferedInputStream buffer = new BufferedInputStream(url.openStream());
                String name2 = foldername.substring(0,foldername.lastIndexOf("."));
                String path = ("C:/Paidfiles/" + name2);
                File newfile = new File(path);
                newfile.mkdirs();

                OutputStream output = new FileOutputStream(path + "/" + filename);
                byte[] content = new byte[4 * 1024];
                int read;
                while ((read = buffer.read(content, 0, content.length)) != -1) {
                    output.write(content, 0, read);
                    output.flush();
                }
                output.close();
                System.out.println("downloaded sucessfully");

            }
        }
}
