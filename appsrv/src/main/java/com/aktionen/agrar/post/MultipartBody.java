package com.aktionen.agrar.post;

import javax.persistence.Lob;
import javax.ws.rs.FormParam;

import lombok.Data;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

@Data
public class MultipartBody {


    @Lob
    private byte[] fileData;
    private String userName;

    public String getUserName() {
        return userName;
    }

    @FormParam("userName")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public byte[] getFileData() {
        return fileData;
    }

    @FormParam("selectedFile")
    @PartType("application/octet-stream")
    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
