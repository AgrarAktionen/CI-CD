package com.aktionen.agrar.classification;

import ai.djl.ModelException;
import ai.djl.translate.TranslateException;
import com.aktionen.agrar.dao.ImageDao;
import com.aktionen.agrar.model.Image;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import org.apache.commons.imaging.ImageReadException;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Path("/image")
public class AlreadyUsedImagesView {
    @Inject
    Template getAlreadyUsedImages;

    @Inject
    ImageDao imageDao;

    @Path("/AlreadyUsedView")
    @GET
    public TemplateInstance getAlreadyUsedInstance() throws URISyntaxException, IOException, ImageReadException, ModelException, TranslateException {

        //GET Request of the classified images
        List<Image> images = imageDao.getAllAlreadyUsedImages();
        return getAlreadyUsedImages.data("images", images);
    }
}
