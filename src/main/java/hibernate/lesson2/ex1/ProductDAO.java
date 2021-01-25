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

    public static void main(String[] args) {
        Product product = new Product();
        product.setName("table new");
        product.setDescription("grey & blue");
        product.setPrice(70);

//        save(product);
//--------------------------------------------------
        Product product1 = new Product();
        product1.setName("table new111");
        product1.setDescription("grey & blue");
        product1.setPrice(70);

        Product product2 = new Product();
        product2.setName("table new222");
        product2.setDescription("grey & blue");
        product2.setPrice(70);

        Product product3 = new Product();
        product3.setName("table new333");
        product3.setDescription("grey & blue");
        product3.setPrice(70);

        List<Product> products = Arrays.asList(product1, product2, product3);

        for (Product pr : products) {
            System.out.println(pr);
        }

//        saveAll(products);
//----------------------------------------------------

//        delete(33);
//----------------------------------------------------

        Product product4 = new Product();
        product4.setId(111);
        product4.setName("table SUPER new333");
        product4.setDescription("BLACK");
        product4.setPrice(7000);

//        System.out.println(update(product4));
//-----------------------------------------------------

        Product product5 = new Product();
        product5.setId(110);
        product5.setName("car 111");
        product5.setDescription("COLOR");
        product5.setPrice(100);

        Product product6 = new Product();
        product6.setId(35);
        product6.setName("car new222");
        product6.setDescription("OLD");
        product6.setPrice(200);

        List<Product> products1 = Arrays.asList(product6, product5);

//        updateAll(products1);
//-------------------------------------------------------

        Product product7 = new Product();
        product7.setId(43);

        Product product8 = new Product();
        product8.setId(20);

        List<Product> products2 = Arrays.asList(product7, product8);

//        deleteAll(products2);

    }

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
