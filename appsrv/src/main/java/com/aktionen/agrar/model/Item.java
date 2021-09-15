package com.aktionen.agrar.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import javax.persistence.*;
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

    @Column(length = 1000)
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
    @CsvBindByPosition(position = 10)
    private String ean;

    @Column(length = 1000)
    @CsvBindByPosition(position = 11)
    private String versandkosten;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "itemId", referencedColumnName = "itemId")
    private List<Price> prices;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "apiId")
    private APILink apiLink;

}
