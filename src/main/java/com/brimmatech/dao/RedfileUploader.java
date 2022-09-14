package com.brimmatech.dao;

import java.io.*;

public class RedfileUploader {
    public void fileUploader(String email, String documentname)  {

        String foldername = email.substring(0, email.lastIndexOf("."));
        String path = ("C:/Uploadedfiles/" + foldername + "/" + documentname);

        File recentupload = new File(path);
        String outpath = ("C:/Paidfiles/" + foldername);

        File newfile = new File(outpath);
        newfile.mkdirs();

        File output = new File(outpath + "/" + documentname);

        if (!output.exists()) {
            recentupload.renameTo(output);
        }
    }
}