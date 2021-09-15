package com.aktionen.agrar.dao;

import com.aktionen.agrar.model.Shop;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.List;

@Named
@Dependent
public class ShopDao {
    @Inject
    EntityManager em;

    public List<Shop> getAll() {
        return em.createQuery("select s from Shop s", Shop.class).getResultList();
    }
    public Shop get(int id) {
        return em.find(Shop.class, id);
    }
    public Shop add(Shop shop) {
        return em.merge(shop);
    }
    public void delete(Shop shop) {
        em.remove(shop);
    }
}