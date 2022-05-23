package repository.hibernate;

import domain.Bug;
import domain.BugStatus;
import domain.Programmer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import repository.IBugRepository;
import repository.IProgrammerRepository;

import java.util.Collection;
import java.util.List;

public class BugHbmRepository implements IBugRepository {
    @Override
    public void add(Bug elem) throws Exception {
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
        Bug bug = findById(elem);
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.delete(bug);
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
    public void update(Bug elem, Integer id) {
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
    public Bug findById(Integer id) {
        Bug bug = null;
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                bug = session.find(Bug.class, id);
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
        return bug;
    }

    @Override
    public Collection<Bug> getAll() {
        List<Bug> bugs = null;
        initialize();
        try(Session session = sessionFactory.openSession()){
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                bugs = session.createQuery("from Bug", Bug.class).list();
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
        return bugs;
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

}
