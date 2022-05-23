package repository.hibernate;

import domain.Programmer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repository.IProgrammerRepository;


import java.util.Collection;
import java.util.List;

public class ProgrammerHbmRepository implements IProgrammerRepository {
    @Override
    public void add(Programmer elem) throws Exception {
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
        Programmer programmer = findById(elem);
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.delete(programmer);
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
    public void update(Programmer elem, Integer id) {
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
    public Programmer findById(Integer id) {
        Programmer programmer = null;
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                programmer = session.find(Programmer.class, id);
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
        return programmer;
    }

    @Override
    public Collection<Programmer> getAll() {
        List<Programmer> programmers = null;
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                programmers = session.createQuery("from Programmer", Programmer.class).list();
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
        return programmers;
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
    public Programmer findOneByUsername(String username) {
        Programmer programmer = null;
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                programmer = session.createQuery("from Programmer  where username=:username" , Programmer.class).setParameter("username", username).setMaxResults(1) .uniqueResult();

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
        return programmer;
    }
}
