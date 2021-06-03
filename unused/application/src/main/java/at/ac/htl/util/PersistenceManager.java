package at.ac.htl.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class PersistenceManager {
    private EntityManagerFactory emFactory;

    private PersistenceManager() {
        emFactory = Persistence.createEntityManagerFactory("database");
    }
    @PostConstruct
    private void init() {
        System.out.println("create factory");
        emFactory = Persistence.createEntityManagerFactory("database");
    }
    @Produces
    @ApplicationScoped
    public EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }
    @PreDestroy
    public void close() {
        emFactory.close();
    }
}