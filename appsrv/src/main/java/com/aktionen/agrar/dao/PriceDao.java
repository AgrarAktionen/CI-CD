package com.aktionen.agrar.dao;

import com.aktionen.agrar.model.Item;
import com.aktionen.agrar.model.Price;
import com.aktionen.agrar.model.PricePk;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.List;

@Named
@Dependent
public class PriceDao {
    @Inject
    EntityManager em;

    @Inject
    ItemDao itemDao;

    public void insertAll(Price price, Item item){

            //percentage between "stattpreis" and "bruttopreis"
            double stattpreis = Double.parseDouble(price.getStattpreis().replace(",", "."));
            double bruttopreis = Double.parseDouble(price.getBruttopreis().replace(",", "."));
            double percentage = ((stattpreis-bruttopreis)/stattpreis)*100;
            price.setPercentage(percentage);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            //price.setPricePk(new PricePk(1000, timestamp));
            price.setPricePk(new PricePk(item.getItemId(), timestamp)); // -> throws an ERROR because the correct update implementation has not been done yet!
                                                                        // however, it can be neglected until fixing TODO: Solving Price ERROR -> Maybe you have to change the primary key relation from the Price to the Item Model to a 1:n relation because one Item may have more Prices because of the fact that it had changed. So you have to check if the timestamp to specify!

            em.persist(price);
            //em.merge(price);
            em.flush();



    }

    public List<Price> getAll() {
        return em.createQuery("select p from Price p ", Price.class).getResultList();
    }

    public Price add( Price price) {
        return em.merge(price);
    }

    public Price get(int id) {
        return em.find(Price.class, id);
    }

    public void delete(Price price) {
        em.remove(price);
    }

}
