package at.htl.bhif17.demo.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@ToString
public class Person {
    @Id
    @GeneratedValue
    private Integer id;

    private String firstName;
    private String lastName;
    private String matNr;
}
