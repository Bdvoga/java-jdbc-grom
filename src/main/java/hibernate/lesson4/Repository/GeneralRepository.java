package hibernate.lesson4.Repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class GeneralRepository <T extends IdEntity> {
    private Class<T> type;
    private SessionFactory sessionFactory;

    public GeneralRepository() {
    }

    public GeneralRepository(Class<T> type) {
        this.type = type;
    }

    public T findById(long id) {
        Session session = createSessionFactory().openSession();

        T t = session.get(type, id);
        session.close();

        return t;
    }

    public void delete(long id) {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            if (findById(id) == null) {
                return;
            } else {
                session.delete(findById(id));
            }

            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());

            if (tr != null) {
                tr.rollback();
            }
        }
    }

    public T save(T t)  {
        Transaction tr = null;
        try (Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();
            session.save(t);
            session.getTransaction().commit();

            return findById(t.getId());
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            if (tr != null) {
                tr.rollback();
            }
        }

        return null;
    }

    public SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

}