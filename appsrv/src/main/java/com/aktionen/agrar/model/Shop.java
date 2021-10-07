package com.aktionen.agrar.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity

public class Shop {

    @Id
    @GeneratedValue
    private int shopId;
    private String name;
    private String contactPerson;


}
