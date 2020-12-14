package at.htl.bhif17.demo.model;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany
    private List<Person> persons;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @JsonbTransient
    public List<Person> getPersons() {
        return persons;
    }
    @JsonbTransient
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
