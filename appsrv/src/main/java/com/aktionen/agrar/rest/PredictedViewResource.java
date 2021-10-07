package com.aktionen.agrar.rest;

import com.aktionen.agrar.dao.ItemDao;
import com.aktionen.agrar.dao.PredectionDao;
import com.aktionen.agrar.model.PredictedItem;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Transactional
@Path("predictedView")
public class PredictedViewResource {

    @Inject
    Template predictedview;

    @Inject
    PredectionDao predectionDao;

    @Inject
    ItemDao itemDao;



    @GET
    public TemplateInstance templateInstance() {
        List<PredictedItem> predictedItemList = predectionDao.getAll();
        return predictedview.data("predictedItems", predictedItemList);
    }
}
