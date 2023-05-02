package com.dosmakhambetbaktiyar.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
                return sessionFactory;
            }catch (Exception e){
                System.out.println("Connection failed: " + e.getMessage());
                System.exit(1);
            }
        }

        return sessionFactory;
    }
}
