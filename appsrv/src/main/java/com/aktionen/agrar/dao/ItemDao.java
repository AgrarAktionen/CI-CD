package com.aktionen.agrar.dao;

import com.aktionen.agrar.model.APILink;
import com.aktionen.agrar.model.Item;
import com.aktionen.agrar.model.Price;
import com.aktionen.agrar.model.PricePk;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Named
@Dependent
public class ItemDao {
    @Inject
    EntityManager em;

    public void insert(Item item, String name){

        APILink apiLink = em.createQuery("select a from APILink a where a.description = :desc", APILink.class)
                .setParameter("desc", name)
                .getSingleResult();


        if((em.createQuery("select i from Item i where i.artikelnummer = :artikelnummer", Item.class)
                .setParameter("artikelnummer", item.getArtikelnummer())
                .getResultList().size()) > 0){


            em.createQuery("update Item i set i.hersteller = :hersteller where i.artikelnummer = :artikelnummer")
                    .setParameter("hersteller", item.getHersteller())
                    .setParameter("artikelnummer", item.getArtikelnummer()).executeUpdate();

            em.createQuery("update Item i set i.artikelbezeichnung = :artikelbezeichnung where i.artikelnummer = :artikelnummer")
                    .setParameter("artikelbezeichnung", item.getArtikelbezeichnung())
                    .setParameter("artikelnummer", item.getArtikelnummer()).executeUpdate();

            em.createQuery("update Item i set i.kategoriepfad = :kategoriepfad where i.artikelnummer = :artikelnummer")
                    .setParameter("kategoriepfad", item.getKategoriepfad())
                    .setParameter("artikelnummer", item.getArtikelnummer()).executeUpdate();

            em.createQuery("update Item i set i.beschreibungsfeld = :beschreibungsfeld where i.artikelnummer = :artikelnummer")
                    .setParameter("beschreibungsfeld", item.getBeschreibungsfeld())
                    .setParameter("artikelnummer", item.getArtikelnummer()).executeUpdate();

            em.createQuery("update Item i set i.bildLink = :bildLink where i.artikelnummer = :artikelnummer")
                    .setParameter("bildLink", item.getBildLink())
                    .setParameter("artikelnummer", item.getArtikelnummer()).executeUpdate();

            em.createQuery("update Item i set i.verfuegbarkeit = :verfuegbarkeit where i.artikelnummer = :artikelnummer")
                    .setParameter("verfuegbarkeit", item.getVerfuegbarkeit())
                    .setParameter("artikelnummer", item.getArtikelnummer()).executeUpdate();

            em.createQuery("update Item i set i.versandkosten = :versandkosten where i.artikelnummer = :artikelnummer")
                    .setParameter("versandkosten", item.getVersandkosten())
                    .setParameter("artikelnummer", item.getArtikelnummer()).executeUpdate();

            em.createQuery("update Item i set i.bruttopreis = :bruttopreis where i.artikelnummer = :artikelnummer")
                    .setParameter("bruttopreis", item.getBruttopreis())
                    .setParameter("artikelnummer", item.getArtikelnummer()).executeUpdate();

            em.createQuery("update Item i set i.stattpreis = :stattpreis where i.artikelnummer = :artikelnummer")
                    .setParameter("stattpreis", item.getStattpreis())
                    .setParameter("artikelnummer", item.getArtikelnummer()).executeUpdate();

            double stattpreis = Double.parseDouble(item.getStattpreis().replace(",", "."));
            double bruttopreis = Double.parseDouble(item.getBruttopreis().replace(",", "."));
            double percentage = ((stattpreis-bruttopreis)/stattpreis)*100;
            em.createQuery("update Item i set i.percentage = :percentage where i.artikelnummer = :artikelnummer")
                    .setParameter("percentage", percentage)
                    .setParameter("artikelnummer", item.getArtikelnummer()).executeUpdate();

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            em.createQuery("update Item i set i.timestamp = :timestamp where i.artikelnummer = :artikelnummer")
                    .setParameter("timestamp", timestamp)
                    .setParameter("artikelnummer", item.getArtikelnummer()).executeUpdate();

            em.flush();


        }else {
            //percentage between "stattpreis" and "bruttopreis"
            double stattpreis = Double.parseDouble(item.getStattpreis().replace(",", "."));
            double bruttopreis = Double.parseDouble(item.getBruttopreis().replace(",", "."));
            double percentage = ((stattpreis-bruttopreis)/stattpreis)*100;
            item.setPercentage(percentage);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            item.setTimestamp(timestamp);

            item.setApiLink(apiLink);
            em.persist(item);
            em.flush();
        }



    }



    public List<Item> getAll() {
        return em.createQuery("select i from Item i ", Item.class).getResultList();
    }


    public Item add(@NotNull Item item) {
        return em.merge(item);
    }

    public Item get(int id) {
        return em.find(Item.class, id);

    }

    public void delete(Item item) {
        em.remove(item);
    }

}

