package br.com.ccb.hibernate.util;

import java.security.NoSuchAlgorithmException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class TabelasFactory {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        Configuration conf = new Configuration();
        conf.configure();
        SchemaExport se = new SchemaExport(conf);
        se.create(true, true);
               
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        
        System.out.println("BANCO DE DADOS CRIADO COM SUCESSO");
    }
}