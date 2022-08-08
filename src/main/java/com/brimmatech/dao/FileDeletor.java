package com.brimmatech.dao;

import java.io.File;

public class FileDeletor {
    public void fileTobeDelete(String email , String documentname)
    {
        String foldername = email.substring(0,email.lastIndexOf("."));
        System.out.println(foldername);
        File deletefile = new File("C:/Uploadedfiles/" + foldername + "/" +documentname);
        if(deletefile.exists())
        {
            deletefile.delete();

        }
    }
}
