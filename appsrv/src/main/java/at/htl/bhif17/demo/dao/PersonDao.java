package at.htl.bhif17.demo.dao;

import at.htl.bhif17.demo.model.Person;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.List;

@Named
@Dependent
public class PersonDao {
    @Inject
    EntityManager em;

    public List<Person> getAll() {
        return em.createQuery("select p from Person p order by p.lastName, p.firstName, p.matNr", Person.class).getResultList();
    }
    public Person save(Person person) {
        return em.merge(person);
    }
    public Person get(int id) {
        return em.find(Person.class, id);
    }

    public void remove(Person person) {
        em.remove(person);
    }
}
