package com.aktionen.agrar.dao;

import com.aktionen.agrar.model.Item;
import com.aktionen.agrar.model.Price;
import com.aktionen.agrar.model.PricePk;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
//import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Named
@Dependent
public class PriceDao {
    @Inject
    EntityManager em;

    @Inject
    ItemDao itemDao;

    public void insertAll(List<Price> prices){
    // TODO create a currentId which is an actual id in the database: hibernate_sequence -> import SQL
        //Item firstItem = itemDao.get(0);
        //int currentId = firstItem.getItemId();
        int currentId = 1000;
        for(Price price:prices){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Item item = em.createQuery("select p from Item p where p.itemId = :ids", Item.class)
                    .setParameter("ids", currentId)
                    .getSingleResult();

            price.setPricePk(new PricePk(item.getItemId(), timestamp));
            //em.persist(item);
            em.merge(price);
            em.flush();
            currentId++;

        }
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
