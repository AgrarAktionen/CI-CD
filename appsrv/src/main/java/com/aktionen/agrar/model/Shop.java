package com.aktionen.agrar.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity

public class Shop {

    @Id
    @GeneratedValue
    private int shopId;
    private String name;
    private String contactPerson;


}
