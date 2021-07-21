package com.msc.myspotify.dao;

import java.io.File;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author Michael
 */
class HibernateFactory {

    
    private static SessionFactory sessionFactory;

    public static void setUp() {

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure(new File("hibernate.cfg.xml")).build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public synchronized static SessionFactory getInstance() {
        if (sessionFactory == null) {
            setUp();
        }
        return sessionFactory;
    }

    
}
