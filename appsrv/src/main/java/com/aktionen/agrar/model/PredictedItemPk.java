package com.aktionen.agrar.model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
@Data

public class PredictedItemPk implements Serializable {


    private int itemId;
    private String prediction;


    public PredictedItemPk() {
    }

    public PredictedItemPk(int itemId, String prediction) {

        this.itemId = itemId;
        this.prediction = prediction;


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PredictedItemPk)) return false;
        PredictedItemPk predictedItemPk = (PredictedItemPk) o;
        return itemId == predictedItemPk.itemId &&
                prediction == predictedItemPk.prediction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, prediction);
    }


}

