package com.brimmatech.dao;

import java.io.File;

public class FileDeletor {
    public void fileTobeDelete(String foldername, String documentname) {


        File deletefile = new File("C:/Uploadedfiles/" + foldername + "/" + documentname);
        if (deletefile.exists()) {
            deletefile.delete();
        }
    }
}
