package at.htl.bhif17.demo;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Dependent
public class PersonDao {
    @Inject EntityManager em;

    public List<Person> getAll() {
        return em.createQuery("select p from Person p order by p.lastName, p.firstName, p.matNr", Person.class).getResultList();
    }
    public Person save(Person person) {
        return em.merge(person);
    }
}
