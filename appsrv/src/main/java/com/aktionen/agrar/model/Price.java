package com.aktionen.agrar.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import javax.persistence.*;

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


/*
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "itemId")
    private Item item;
*/
}