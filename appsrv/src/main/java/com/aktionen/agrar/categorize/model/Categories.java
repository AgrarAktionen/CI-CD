package com.aktionen.agrar.categorize.model;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Categories {

    @Id
    private int categoriesId;
    private String prime;
    private String second;
    private String third;


}
