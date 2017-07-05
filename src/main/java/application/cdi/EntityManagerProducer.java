package application.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author sara.so
 */
@ApplicationScoped
public class EntityManagerProducer {

    @PersistenceUnit
    private EntityManagerFactory factory;
    
    @Produces
    @RequestScoped
    EntityManager produceEntityManager() {
        return this.factory.createEntityManager();
    }
    
    void close(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.clear();
            entityManager.close();
        }
    }
}
