package com.aktionen.agrar.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@Entity
public class Price {

    @EmbeddedId
    private PricePk pricePk;

    @Column(length = 1000)
    @CsvBindByPosition(position = 8)
    private String bruttopreis;

    @Column(length = 1000)
    @CsvBindByPosition(position = 9)
    private String stattpreis;

    private boolean validFlag;

    private double percentage;


/*
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "itemId")
    private Item item;
*/
}