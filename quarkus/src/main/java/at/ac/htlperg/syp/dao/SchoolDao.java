package at.ac.htlperg.syp.dao;

import at.ac.htlperg.syp.model.School;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Dependent
public class SchoolDao {
    @Inject
    EntityManager em;

    public List<School> getSchools() {
        return em.createQuery("select s from School s").getResultList();
    }
}
