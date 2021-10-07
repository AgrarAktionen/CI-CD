package com.aktionen.agrar.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class APILink {

    @Id
    @GeneratedValue
    private int apiId;
    private String url;
    private String description;
    private String user;
    private String password;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shopId")
    private Shop shop;

}
