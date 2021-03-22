package at.htl.quarkus.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Bill {
    @Id
    @GeneratedValue
    private int bill_id;
    private String number;
    @OneToMany
    @JoinColumns({
            @JoinColumn(name = "bill_id", referencedColumnName = "bill_id")
    })
    private List<Line> lines;
}
