package at.htl.quarkus.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Line {
    @EmbeddedId
    private LinePk linePk;
    private int amount;
}
