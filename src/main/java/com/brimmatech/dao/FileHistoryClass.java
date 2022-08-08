package com.brimmatech.dao;

public class FileHistoryClass {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentname) {
        this.documentName = documentname;
    }

    private String date;
    private String documentName;



    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    private String filesize;

private String status;

    public String getFilesize() {
        return filesize;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
