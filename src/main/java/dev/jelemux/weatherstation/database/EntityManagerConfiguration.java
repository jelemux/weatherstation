package dev.jelemux.weatherstation.database;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class EntityManagerConfiguration {

    private static final Logger LOGGER = Logger.getLogger(EntityManagerConfiguration.class.getCanonicalName());

    private EntityManagerFactory entityManagerFactory;

    @Inject
    @ConfigProperty(name = "datasource.url")
    private String url;

    @Inject
    @ConfigProperty(name = "datasource.username")
    private String username;

    @Inject
    @ConfigProperty(name = "datasource.password")
    private String password;

    @SuppressWarnings("unused")
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        initEntityManagerFactory();
    }

    @Produces
    @ApplicationScoped
    EntityManagerFactory getEntityManagerFactory() {
        return this.entityManagerFactory;
    }

    void close(@Disposes EntityManagerFactory entityManagerFactory) {
        entityManagerFactory.close();
    }

    @Produces
    @RequestScoped
    EntityManager createEntityManager() {
        return this.entityManagerFactory.createEntityManager();
    }

    void closeEntityManager(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }

    private void initEntityManagerFactory() {
        try {
            final var connectionProperties = new HashMap<String, String>();
            connectionProperties.put("jakarta.persistence.jdbc.url", url);
            connectionProperties.put("jakarta.persistence.jdbc.user", username);
            connectionProperties.put("jakarta.persistence.jdbc.password", password);
            entityManagerFactory = Persistence.createEntityManagerFactory("PersistenceUnit", connectionProperties);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, String.format("EntityManager could not be created: %s", e.getMessage()));
            System.exit(1);
        }
    }
}
