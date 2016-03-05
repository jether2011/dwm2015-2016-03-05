/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisal.twitter.dao;

import br.unisal.hibernateutil.HibernateUtil;
import br.unisal.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author jether
 */
public class TweetDao {

    public void insert(Tweet s) {
        Session session = HibernateUtil.getSessionFactory()
                .openSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            session.save(s);
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("Exception TweetDao.insert(): " 
                    + e.getMessage());
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public void update(Tweet s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            session.update(s);
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("Exception TweetDao.update(): " + e.getMessage());
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public List<Tweet> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Tweet> sensores = new ArrayList<>();
        try {
            tx.begin();
            Query query = session.createQuery("FROM Tweet");
            sensores = query.list();
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("Exception TweetDao.getAll(): " 
                    + e.getMessage());
            tx.rollback();
        } finally {
            session.close();
        }
        return sensores;
    }

    public Tweet getById(Tweet s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Tweet sensor = new Tweet();
        try {
            tx.begin();
            Query query = session
                    .createQuery("FROM Tweet WHERE id = :id");
            query.setParameter("id", s.getId());
            sensor = (Tweet) query.uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("Exception TweetDao.getById(): " + e.getMessage());
            tx.rollback();
        } finally {
            session.close();
        }
        return sensor;
    }

    public void remove(Tweet s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        try {
            tx.begin();
            session.delete(s);
            tx.commit();
        } catch (HibernateException e) {
            System.out.println("Exception TweetDao.remove(): " + e.getMessage());
            tx.rollback();
        } finally {
            session.close();
        }
    }

}
