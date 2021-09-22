package com.aktionen.agrar.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity

public class Shop implements Serializable {

    @Id
    @GeneratedValue
    private int shopId;
    private String name;
    private String contactPerson;


}
