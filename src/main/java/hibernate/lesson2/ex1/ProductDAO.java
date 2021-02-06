package hibernate.lesson2.ex1;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.Arrays;
import java.util.List;

public class ProductDAO {

    private static SessionFactory sessionFactory;

    public static void deleteAll(List<Product> products) {
        Session session = null;
        Transaction tr = null;
        try {
            session = createSessionFactory().openSession().getSession();
            tr = session.getTransaction();
            tr.begin();

            for (Product product : products) {
                session.delete(session.get(Product.class, product.getId()));
            }

            tr.commit();

        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            if (tr != null) {
                tr.rollback();
            }
        } finally {
           if (session != null) {
               session.close();
           }
        }
    }

    public static void updateAll(List<Product> products) {
        Session session = null;
        Transaction tr = null;
        try {
            session = createSessionFactory().openSession().getSession();
            tr = session.getTransaction();
            tr.begin();

            for (Product product : products) {
                session.get(Product.class, product.getId()).setName(product.getName());
                session.get(Product.class, product.getId()).setDescription(product.getDescription());
                session.get(Product.class, product.getId()).setPrice(product.getPrice());
            }

            tr.commit();

        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static Product update(Product product) {
        Session session = null;
        Transaction tr = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            session.get(Product.class, product.getId()).setName(product.getName());
            session.get(Product.class, product.getId()).setDescription(product.getDescription());
            session.get(Product.class, product.getId()).setPrice(product.getPrice());

            tr.commit();

            return session.get(Product.class, product.getId());

        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return null;
    }

    public static void delete(long id) {
        Session session = null;
        Transaction tr = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            session.delete(session.get(Product.class, id));

            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            if (tr != null) {
                tr.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void saveAll(List<Product> products) {
        //create session/transaction

        Session session = null;
        Transaction tr = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            //action
            for (Product product : products) {
                session.save(product);
            }

            //close session/transaction
            tr.commit();
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());

            if (tr != null) {
                tr.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        System.out.println("Save all is done");
    }

    public static Product save(Product product) {
        //create session/transaction

        Session session = null;
        Transaction tr = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            //action
            session.save(product);

            //close session/transaction
            tr.commit();

            return session.get(Product.class, product.getId());

        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());

            if (tr != null) {
                tr.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return null;
    }

    public static SessionFactory createSessionFactory() {
        //singleton pattern
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
