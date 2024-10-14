package com.ardaslegends.albaseplugin.alapiclients;

import com.ardaslegends.albaseplugin.AL_Base_Plugin;
import com.ardaslegends.albaseplugin.models.DBModels.DBFactionModel;
import com.ardaslegends.albaseplugin.models.DBModels.DBPlayerModel;
import jakarta.persistence.*;

import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PluginDBManager {

    private static final Logger logger = AL_Base_Plugin.getALLogger();
    private static final String msgPrefix = AL_Base_Plugin.getMsgPrefix();
    private static final String errorPrefix = AL_Base_Plugin.getErrorPrefix();

    @PersistenceUnit
    private static EntityManagerFactory entityManagerFactory;

    @PersistenceContext
    static EntityManager em;

    /**
     * Initializes the database connection with the parameters provided in the plugin's config file.
     */
    public static void init() {
        Properties properties = new Properties();
        properties.setProperty("javax.persistence.jdbc.url", AL_Base_Plugin.getDbURL());
        properties.setProperty("javax.persistence.jdbc.user", AL_Base_Plugin.getDbUser());
        properties.setProperty("javax.persistence.jdbc.password", AL_Base_Plugin.getDbPassword());
        properties.setProperty("javax.persistence.jdbc.driver", AL_Base_Plugin.getDbDriver());

        Persistence.createEntityManagerFactory("AL_Base_Plugin", properties);

        logger.log(Level.INFO, msgPrefix + "Database connection set up.");
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    /*
     * Custom database calls
     */

    /**
     * Retrieves a faction by its name from the database.
     *
     * @param name the name of the faction to retrieve
     * @return the DBFactionModel object representing the faction, or null if no faction is found
     */
    public static DBFactionModel getFactionByName(String name) {
        em = getEntityManager();
        TypedQuery<DBFactionModel> query = em.createQuery(
                "SELECT f FROM DBFactionModel f WHERE f.factionName = :name", DBFactionModel.class);
        query.setParameter("name", name);
        DBFactionModel faction = null;
        try {
            faction = (DBFactionModel) query.getSingleResult();
            logger.log(Level.INFO, msgPrefix + "Faction found: " + faction.getFactionName());
        } catch (NoResultException e) {
            logger.log(Level.INFO, errorPrefix + "No Faction found with name: " + name);
        } finally {
            em.close();
        }
        return faction;
    }

    /**
     * Retrieves a list of all faction names from the database.
     *
     * @return a list of all faction names
     */
    public static List<String> getFactionNames() {
        List<String> factionNames = null;
        em = getEntityManager();
        TypedQuery<String> query = em.createQuery(
                "SELECT f.factionName FROM DBFactionModel f", String.class);
        try {
            factionNames = query.getResultList();
        } catch (NoResultException e) {
            logger.log(Level.INFO, errorPrefix + "No Factions found.");
        } finally {
            em.close();
        }
        return factionNames;
    }

    public static DBPlayerModel getPlayerByUUID(String uuid) {
        em = getEntityManager();
        TypedQuery<DBPlayerModel> query = em.createQuery(
                "SELECT p FROM DBPlayerModel p WHERE p.uuid = :uuid", DBPlayerModel.class);
        query.setParameter("uuid", uuid);
        DBPlayerModel player = null;
        try {
            player = (DBPlayerModel) query.getSingleResult();
            logger.log(Level.INFO, msgPrefix + "Player found: " + player.getUuid());
        } catch (NoResultException e) {
            logger.log(Level.INFO, errorPrefix + "No Player found with UUID: " + uuid);
        } finally {
            em.close();
        }
        return player;
    }

    /**
     * Persistiert ein Entity in der Datenbank
     * @param entity Das zu persistierende Entity
     */
    public static void persist(Object entity){
        em = getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Löscht ein Entity aus der Datenbank
     * @param entity Das zu löschende Entity
     */
    public static void deleteEntry(Object entity){
        em = getEntityManager();
        em.getTransaction().begin();
        em.remove(em.contains(entity) ? entity: em.merge(entity));
        em.getTransaction().commit();
        em.close();
    }

    /**
     * Updated ein Entity in der Datenbank
     * @param entity Das zu updatende Entity
     */
    public static void update(Object entity) {
        em = getEntityManager();
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
        em.close();
    }
}
