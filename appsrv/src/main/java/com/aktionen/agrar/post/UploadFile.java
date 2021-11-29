package com.aktionen.agrar.post;

import ai.djl.ModelException;
import ai.djl.translate.TranslateException;
import com.aktionen.agrar.dao.ImageDao;
import com.aktionen.agrar.model.Image;
import org.apache.commons.imaging.ImageReadException;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Path("/upload")
public class UploadFile {

    @Inject
    ImageDao imageDao;

    @POST
    @Path("/imageAndData")
    @Consumes("multipart/form-data")
    @Transactional
    public Response uploadFile(@MultipartForm MultipartBody form) throws URISyntaxException, IOException, ImageReadException, ModelException, TranslateException {

        String userName = form.getUserName() == null ? "Unknown" : form.getUserName() ;
        byte [] bytes = form.getFileData();

        //write to Database
        Image image = new Image();
        image.setBytes(bytes);
        image.setUsername(userName);
        //Set the usability of the image to true
        image.setUsable(true);
        //This image wasn't used ever before
        image.setAlreadyUsed(false);
        imageDao.add(image);
        //com.aktionen.agrar.classification of the uploaded Image
        List<Image> imageList=imageDao.getAll();
        imageDao.insertAll(imageList);
        System.out.println("Inserted");

        //Build a response to return
        return Response.status(200)
                .entity("uploadFile is called, Uploaded file name : " + userName).build();
    }
}