package at.htl.bhif17.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class School {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String street;

    @OneToMany(fetch=FetchType.EAGER)
    private List<Course> courses;
}
