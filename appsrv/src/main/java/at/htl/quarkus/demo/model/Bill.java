package at.htl.quarkus.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Bill {
    @Id
    @GeneratedValue
    private int bill_id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "bill_id", referencedColumnName = "bill_id")
    private List<Line> lines;
}
