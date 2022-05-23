package repository.hibernate;

import domain.Programmer;

import domain.Tester;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repository.IProgrammerRepository;
import repository.ITesterRepository;


import java.util.Collection;
import java.util.List;

public class TesterHbmRepository implements ITesterRepository {
    @Override
    public void add(Tester elem) throws Exception {
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(elem);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Error at add: "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        finally {
            close();
        }
    }

    @Override
    public void delete(Integer elem){
        Tester tester = findById(elem);
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.delete(tester);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Error at delete: "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        finally {
            close();
        }
    }

    @Override
    public void update(Tester elem, Integer id) {
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.update(elem);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Error at update: "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        finally {
            close();
        }
    }

    @Override
    public Tester findById(Integer id) {
        Tester tester = null;
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                tester = session.find(Tester.class, id);
                tx.commit();

            } catch (RuntimeException ex) {
                System.err.println("Error at findById: "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        finally {
            close();
        }
        return tester;
    }

    @Override
    public Collection<Tester> getAll() {
        List<Tester> testers = null;
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                testers = session.createQuery("from Tester", Tester.class).list();
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Error at getAll: "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        finally {
            close();
        }
        return testers;
    }



    static SessionFactory sessionFactory;
    static void initialize() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            System.err.println("Exception "+e);
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    static void close(){
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }

    }

    @Override
    public Tester findOneByUsername(String username) {
        Tester tester = null;
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                tester = session.createQuery("from Tester  where username=:username" , Tester.class).setParameter("username", username).setMaxResults(1) .uniqueResult();

                tx.commit();

            } catch (RuntimeException ex) {
                System.err.println("Error at findByUsername: "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        finally {
            close();
        }
        return tester;
    }
}
