package com.brimmatech.dao;

import java.io.*;

public class RedfileUploader {
    public void fileUploader(String email, String documentname) throws IOException {

        String foldername = email.substring(0, email.lastIndexOf("."));
        String path = ("C:/Uploadedfiles/" + foldername + "/" + documentname);

        File recentupload = new File(path);
        String outpath = ("C:/Paidfiles/" + foldername);

        File newfile = new File(outpath);
        newfile.mkdirs();

        File output = new File(outpath + "/" + documentname);

        if(!output.exists())
        {
            recentupload.renameTo(output);
            System.out.println("error");
        }

//        String path = ("C:/Uploadedfiles/" + foldername + "/" + documentname);
//        InputStream input = new FileInputStream(path);
//        BufferedInputStream buffer = new BufferedInputStream(input);
//
//
//        String outpath = ("C:/Paidfiles/" + foldername);
//
//        File newfile = new File(outpath);
//        newfile.mkdirs();
//        OutputStream output = new FileOutputStream(outpath + "/" + documentname);
//        byte[] content = new byte[4 * 1024];
//        int read;
//        while ((read = buffer.read(content, 0, content.length)) != -1) {
//            output.write(content, 0, read);
//            output.flush();
//        }
//        output.close();



    }
}