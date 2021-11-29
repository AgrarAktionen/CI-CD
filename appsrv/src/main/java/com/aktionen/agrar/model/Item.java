package com.aktionen.agrar.model;

import com.aktionen.agrar.categorize.model.Categories;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class Item {
   // Artikelbezeichnung;Hersteller;Artikelnummer;Kategoriepfad;Beschreibungsfeld;Bild-Link;Deeplink;Verfuegbarkeit;Bruttopreis;Stattpreis;EAN;Versandkosten;

    @Id
    @GeneratedValue
    private int itemId;

    @Column(length = 1000)
    @CsvBindByPosition(position = 0)
    private String artikelbezeichnung;

    @Column(length = 1000)
    @CsvBindByPosition(position = 1)
    private String hersteller;

    @Column(length = 100, unique = true)
    @CsvBindByPosition(position = 2)
    private String artikelnummer;

    @Column(length = 1000)
    @CsvBindByPosition(position = 3)
    private String kategoriepfad;

    @Column(length = 1000)
    @CsvBindByPosition(position = 4)
    private String beschreibungsfeld;

    @Column(length = 1000)
    @CsvBindByPosition(position = 5)
    private String bildLink;

    @Column(length = 1000)
    @CsvBindByPosition(position = 6)
    private String deeplink;

    @Column(length = 1000)
    @CsvBindByPosition(position = 7)
    private String verfuegbarkeit;

    @Column(length = 1000)
    @CsvBindByPosition(position = 8)
    private String bruttopreis;

    @Column(length = 1000)
    @CsvBindByPosition(position = 9)
    private String stattpreis;

    @Column(length = 1000)
    @CsvBindByPosition(position = 10)
    private String ean;

    @Column(length = 1000)
    @CsvBindByPosition(position = 11)
    private String versandkosten;

    private double percentage;

    private Timestamp timestamp;

/*
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "itemId", referencedColumnName = "itemId")
    private List<Price> prices;

 */

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apiId")
    private APILink apiLink;

/*
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoriesId")
    private Categories categories;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "itemId", referencedColumnName = "itemId")
    private PredictedItem predictedItem;

 */

    private boolean inserted = false;
    private String primeCategory;
    private String secondCategory;
    private String thirdCategory;
    private String fourthCategory;


}
