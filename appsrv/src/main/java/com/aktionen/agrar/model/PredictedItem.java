package com.aktionen.agrar.model;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@Entity
public class PredictedItem {

    @EmbeddedId
    private PredictedItemPk predictedItemPk;

    private String classname;
    private double probability;
    private boolean validFlag;


}
