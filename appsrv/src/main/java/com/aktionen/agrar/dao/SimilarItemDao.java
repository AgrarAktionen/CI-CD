package com.aktionen.agrar.dao;

import com.aktionen.agrar.model.Image;
import com.aktionen.agrar.model.Item;
import com.aktionen.agrar.model.PredictedItem;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.LinkedList;
import java.util.List;

@Named
@Dependent
public class SimilarItemDao {

    @Inject
    EntityManager em;

    private final double PROBABILITY = 0.8;

    public List<Item> getAllUseAbleSimilarItems() {

        List<Image> imageList = em.createQuery("select i from Image i").getResultList();
        List<Item> itemList = new LinkedList<>();

        for (Image image : imageList) {
            if (image.isUsable()) {
                List<PredictedItem> predictedItemList = em.createQuery("select p from PredictedItem p where p.classname = :classname", PredictedItem.class)
                        .setParameter("classname", image.getClassname())
                        .getResultList();


                for (PredictedItem predictedItem : predictedItemList) {
                    if (predictedItem.getProbability() >= PROBABILITY) { //Minimal Probability

                        Item item = em.createQuery("select i from Item i where i.itemId = :predictedItemId", Item.class)
                                .setParameter("predictedItemId", predictedItem.getPredictedItemPk().getItemId())
                                .getSingleResult();
                        itemList.add(item);
                        System.out.println(itemList);
                    }
                }
                //Now the image get set to false. IDEA BEHIND:  to be able to get the similarity result of the image just once,
                //                                              but if you want to get the same image again you have to select
                //                                              the image by simply click on it in the previous made images, in the
                //                                              gridView on the application
                image.setUsable(false);
                //this sets the image to be in viewable in the gridView on the application
                image.setAlreadyUsed(true);
            }
        }
        return itemList;
    }

    public List<Item> getAlreadyUsed() {
        List<Image> imageList = em.createQuery("select i from Image i").getResultList();
        List<Item> itemList = new LinkedList<>();

        for (Image image : imageList) {
            if (image.isAlreadyUsed()) {
                List<PredictedItem> predictedItemList = em.createQuery("select p from PredictedItem p where p.classname = :classname", PredictedItem.class)
                        .setParameter("classname", image.getClassname())
                        .getResultList();


                for (PredictedItem predictedItem : predictedItemList) {
                    if (predictedItem.getProbability() >= PROBABILITY) { //Minimal Probability

                        Item item = em.createQuery("select i from Item i where i.itemId = :predictedItemId", Item.class)
                                .setParameter("predictedItemId", predictedItem.getPredictedItemPk().getItemId())
                                .getSingleResult();
                        itemList.add(item);
                        System.out.println(itemList);
                    }
                }
                //Now the image get set to false. IDEA BEHIND:  to be able to get the similarity result of the image just once,
                //                                              but if you want to get the same image again you have to select
                //                                              the image by simply click on it in the previous made images, in the
                //                                              gridView on the application
                image.setUsable(false);
                //this sets the image to be in viewable in the gridView on the application
                image.setAlreadyUsed(true);
            }
        }
        return itemList;
    }
}

