package com.aktionen.agrar.categorize.dao;

import com.aktionen.agrar.model.APILink;
import com.aktionen.agrar.model.Item;
import com.sun.xml.bind.v2.TODO;
import netscape.javascript.JSObject;


import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.ws.rs.PathParam;
import java.util.*;

@Dependent
@Named
public class CategoriesDao {

    @Inject
    EntityManager em;

    public List<Item> selectAll(){
        List<Item> itemList = em.createQuery("SELECT i from Item i where i.inserted = :check", Item.class)
                .setParameter("check", true)
                .getResultList();
        return itemList;
    }
    //____________________________Functions for getting the Categories of the wanted section____________________________//
    public Set<String> getPrimeCategories(){
        List<Item> itemList = em.createQuery("SELECT i from Item i where i.inserted = :check", Item.class)
                .setParameter("check", true)
                .getResultList();
        List<String> categoriesList = new LinkedList<>();
        for(Item item: itemList){
            categoriesList.add(item.getPrimeCategory());
        }

        Set<String> categories = new HashSet<String>(categoriesList);

        return categories;
    }

    public Set<String> getSecondCategories(String primeCategory){
        primeCategory.replace("%20", " ");
        List<Item> itemList = em.createQuery("select i from Item i where i.primeCategory = :primcat and i.inserted = :check", Item.class)
                .setParameter("primcat", primeCategory)
                .setParameter("check", true)
                .getResultList();
        List<String> categoriesList = new LinkedList<>();
        for(Item item: itemList){
            categoriesList.add(item.getSecondCategory());
        }

        Set<String> categories = new HashSet<String>(categoriesList);

        return categories;
    }
    public Set<String> getThirdCategories(String primeCategory, String secondCategory){
        primeCategory.replace("%20", " ");
        secondCategory.replace("%20", " ");
        List<Item> itemList = em.createQuery("select i from Item i where i.primeCategory = :primcat and i.secondCategory =:seccat and i.inserted = :check", Item.class)
                .setParameter("primcat", primeCategory)
                .setParameter("seccat", secondCategory)
                .setParameter("check", true)
                .getResultList();
        List<String> categoriesList = new LinkedList<>();
        for(Item item: itemList){
            categoriesList.add(item.getThirdCategory());
        }

        Set<String> categories = new HashSet<String>(categoriesList);

        return categories;
    }
    public Set<String> getFourthCategories(String primeCategory, String secondCategory, String thirdCategory){
        primeCategory.replace("%20", " ");
        secondCategory.replace("%20", " ");
        thirdCategory.replace("%20", " ");
        List<Item> itemList = em.createQuery("select i from Item i where i.primeCategory = :primcat and i.secondCategory =:secocat and i.thirdCategory =:thircat and i.inserted = :check", Item.class)
                .setParameter("primcat", primeCategory)
                .setParameter("secocat", secondCategory)
                .setParameter("thircat", thirdCategory)
                .setParameter("check", true)
                .getResultList();
        List<String> categoriesList = new LinkedList<>();
        for(Item item: itemList){
            categoriesList.add(item.getFourthCategory());
        }

        Set<String> categories = new HashSet<String>(categoriesList);

        return categories;
    }




    //____________________________Functions for getting the Items of the wanted category/categories____________________________//
    public List<Item> getPrime(String primeCategory) {
        primeCategory.replace("%20", " ");
        List<Item> itemList = em.createQuery("select i from Item i where i.primeCategory = :primcat and i.inserted = :check", Item.class)
                .setParameter("primcat", primeCategory)
                .setParameter("check", true)
                .getResultList();

        return itemList;
    }

    public List<Item> getSecond(String primeCategory, String secondCategory) {
        primeCategory.replace("%20", " ");
        secondCategory.replace("%20", " ");
        List<Item> itemList = em.createQuery("select i from Item i where i.primeCategory = :primcat and i.secondCategory =:seccat and i.inserted = :check", Item.class)
                .setParameter("primcat", primeCategory)
                .setParameter("seccat", secondCategory)
                .setParameter("check", true)
                .getResultList();

        return itemList;
    }
    public List<Item> getThird(String primeCategory, String secondCategory, String thirdCategory) {
        primeCategory.replace("%20", " ");
        secondCategory.replace("%20", " ");
        thirdCategory.replace("%20", " ");
        List<Item> itemList = em.createQuery("select i from Item i where i.primeCategory = :primcat and i.secondCategory =:secocat and i.thirdCategory =:thircat and i.inserted = :check", Item.class)
                .setParameter("primcat", primeCategory)
                .setParameter("secocat", secondCategory)
                .setParameter("thircat", thirdCategory)
                .setParameter("check", true)
                .getResultList();

        return itemList;
    }
    public List<Item> getFourth(String primeCategory, String secondCategory, String thirdCategory,String fourthCategory) {
        primeCategory.replace("%20", " ");
        secondCategory.replace("%20", " ");
        thirdCategory.replace("%20", " ");
        fourthCategory.replace("%20", " ");
        List<Item> itemList = em.createQuery("select i from Item i where i.primeCategory = :primcat and i.secondCategory =:secocat and i.thirdCategory =:thircat and i.fourthCategory =:fourcat and i.inserted = :check", Item.class)
                .setParameter("primcat", primeCategory)
                .setParameter("secocat", secondCategory)
                .setParameter("thircat", thirdCategory)
                .setParameter("fourcat", fourthCategory)
                .setParameter("check", true)
                .getResultList();

        return itemList;
    }

    //___________________________________Functions for selecting the correct categories___________________________________//

    public String selectPrime(Item item) {

        String output= "";
        String primeCategory = item.getKategoriepfad();

        int posFrom = primeCategory.indexOf("");
        if (posFrom != -1) //if found char
        {
            int posTo = primeCategory.indexOf(">", posFrom + 1);
            if (posTo != -1) //if found char
            {
                output = primeCategory.substring(posFrom, posTo - posFrom);


            }

        }

        return output;
    }
    public String selectSecond(Item item) {

        String output = "";
        String primeCategory = item.getPrimeCategory();
        String itemCategoryPath = item.getKategoriepfad();
        String secondCategory = itemCategoryPath.replaceFirst(primeCategory, "");
        //String correctedSecondCategory = secondCategory.replaceFirst(">","");
        String correctedSecondCategory = secondCategory.substring(1);

        if(correctedSecondCategory.equals(">") || correctedSecondCategory.equals(">>") || correctedSecondCategory.equals("")){
            output = primeCategory;
        }else {
            int posFrom = correctedSecondCategory.indexOf("");
            if (posFrom != -1) //if found char
            {
                int posTo = correctedSecondCategory.indexOf(">", posFrom + 1);
                if (posTo != -1) //if found char
                {
                    output = correctedSecondCategory.substring(posFrom, posTo - posFrom);


                }
            }
        }
        return output;

    }
    public String selectThird(Item item) {

        String output = "";
        String primeCategory = item.getPrimeCategory();
        String itemCategoryPath = item.getKategoriepfad().replaceFirst(primeCategory, "");
        String secondCategory = item.getSecondCategory();
        String thirdCategory = itemCategoryPath.replaceFirst(secondCategory, "");
        //String correctedThirdCategory = thirdCategory.replaceFirst(">", "");
        String correctedThirdCategory = thirdCategory.substring(2);
        if(correctedThirdCategory.equals(">") || correctedThirdCategory.equals(">>") || correctedThirdCategory.equals("")){
            output = secondCategory;
        }else {
            int posFrom = correctedThirdCategory.indexOf("");
            if (posFrom != -1) //if found char
            {
                int posTo = correctedThirdCategory.indexOf(">", posFrom + 1);
                if (posTo != -1) //if found char
                {
                    output = correctedThirdCategory.substring(posFrom, posTo - posFrom);


                }
            }
        }
        return output;
    }

    //TODO Volle Funktionalität erweitern: Bei einigen noch nicht möglich!

    public String selectFourth(Item item) {

        String output = "";
        String primeCategory = item.getPrimeCategory();
        String itemCategoryPath = item.getKategoriepfad().replaceFirst(primeCategory, "");
        String secondCategory = item.getSecondCategory();
        String itemCategoryPath1 = itemCategoryPath.replaceFirst(secondCategory, "");
        String thirdCategory = item.getThirdCategory();
        String fourthCategory =  itemCategoryPath1.replaceFirst(thirdCategory, "");
        //String correctedThirdCategory = thirdCategory.replaceFirst(">", "");
        String correctedFourthCategory = fourthCategory.substring(3);
        if(correctedFourthCategory.equals(">") || correctedFourthCategory.equals(">>") || correctedFourthCategory.equals(">>>") || correctedFourthCategory.equals("")){
            output = thirdCategory;
        }else {
            int posFrom = correctedFourthCategory.indexOf("");
            if (posFrom != -1) //if found char
            {
                int posTo = correctedFourthCategory.indexOf(">", posFrom + 1);
                if (posTo != -1) //if found char
                {
                    output = correctedFourthCategory.substring(posFrom, posTo - posFrom);


                }
            }
        }
        return output;
    }
    //____________________________________________________________________________________________________________________//
}
