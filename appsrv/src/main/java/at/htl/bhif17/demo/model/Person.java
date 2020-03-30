package at.htl.bhif17.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String matNr;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Person person;
        private Builder() {
            this.person = new Person();
        }

        public Builder firstName(String first) {
            person.firstName = first;
            return this;
        }
        public Builder lastName(String last) {
            person.lastName = last;
            return this;
        }
        public Builder matNr(String mat) {
            person.matNr = mat;
            return this;
        }
        public Person build() {
            return this.person;
        }
    }

    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getMatNr() {
        return matNr;
    }
    public void setMatNr(String matNr) {
        this.matNr = matNr;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", matNr='" + matNr + '\'' +
                '}';
    }
}
