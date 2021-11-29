package com.aktionen.agrar.classification;

import ai.djl.ModelException;
import ai.djl.translate.TranslateException;
import com.aktionen.agrar.dao.SimilarItemDao;
import com.aktionen.agrar.model.Item;
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
@Path("/get")
public class GetAlreadyUsedItemView {
    @Inject
    Template getAlreadyUsed;


    @Inject
    SimilarItemDao similarItemDao;

    @Path("/AlreadyUsedView")
    @GET
    public TemplateInstance getAlreadyUsedInstance() throws URISyntaxException, IOException, ImageReadException, ModelException, TranslateException {

        //GET Request of the classified images
        List<Item> items = similarItemDao.getAlreadyUsed();
        return getAlreadyUsed.data("items", items);
    }
}
