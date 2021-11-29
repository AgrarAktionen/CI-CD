package com.aktionen.agrar.classification;

import ai.djl.ModelException;
import ai.djl.translate.TranslateException;
import com.aktionen.agrar.dao.SimilarItemDao;
import com.aktionen.agrar.dao.ImageDao;
import com.aktionen.agrar.model.Item;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import org.apache.commons.imaging.ImageReadException;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Transactional
@Path("/get")
public class GetSimilarItemView {

    @Inject
    Template getSimilarItems;

    @Inject
    Template getAlreadyUsed;


    @Inject
    SimilarItemDao similarItemDao;

    @Inject
    ImageDao imageDao;

    @Path("/SimilarItemView")
    @GET
    public TemplateInstance getSimilarItemsInstance() throws URISyntaxException, IOException, ImageReadException, ModelException, TranslateException {

        //GET Request of the classified images
        List<Item> items = similarItemDao.getAllUseAbleSimilarItems();
        return getSimilarItems.data("items", items);
    }


}
