package com.aktionen.agrar.model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Embeddable
@Data
public class PricePk implements Serializable {



        private int itemId;
        private Timestamp timestamp;

        public PricePk() {
        }

        public PricePk(int itemId, Timestamp timestamp) {

            this.itemId = itemId;
            this.timestamp = timestamp;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PricePk)) return false;
            PricePk pricePk = (PricePk) o;
            return itemId == pricePk.itemId &&
                    timestamp == pricePk.timestamp;
        }

        @Override
        public int hashCode() {
            return Objects.hash(itemId, timestamp);
        }


}
