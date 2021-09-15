package com.aktionen.agrar.dao;

import com.aktionen.agrar.model.APILink;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.List;

@Named
@Dependent
public class APILinkDao {
    @Inject
    EntityManager em;

    public List<APILink> getAll() {
        return em.createQuery("select a from APILink a", APILink.class).getResultList();
    }
    public APILink get(int id) {
        return em.find(APILink.class, id);
    }
    public APILink add(APILink apiLink) {
        return em.merge(apiLink);
    }
    public void delete(APILink apiLink) {
        em.remove(apiLink);
    }
}

