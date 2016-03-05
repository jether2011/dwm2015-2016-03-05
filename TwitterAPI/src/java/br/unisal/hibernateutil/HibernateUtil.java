package br.unisal.hibernateutil;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();
        } catch (HibernateException e) {
            System.err.print(e.getMessage());
            e.toString();
            sessionFactory = null;
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}