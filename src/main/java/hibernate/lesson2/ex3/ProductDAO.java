package hibernate.lesson2.ex3;

import hibernate.lesson2.ex2.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDAO {

    private static SessionFactory sessionFactory;

    public static List findByPriceSortedDesc(int price, int delta) {
        Session session = createSessionFactory().openSession().getSession();

        List list = session.createSQLQuery("SELECT * FROM PRODUCT2 WHERE PRICE BETWEEN " + (price - delta) + " AND " + (price + delta) + " ORDER BY PRICE DESC")
                .addEntity(Product.class).list();
        session.close();

        return list;
    }

    public static List findByNameSortedDesc(String name) {
        Session session = createSessionFactory().openSession().getSession();

        List list = session.createSQLQuery("SELECT * FROM PRODUCT2 WHERE NAME LIKE " + "'%" + name + "%' ORDER BY NAME DESC")
                .addEntity(Product.class).list();
        session.close();

        return list;
    }

    public static List findByNameSortedAsc(String name) {
        Session session = createSessionFactory().openSession().getSession();

        List list = session.createSQLQuery("SELECT * FROM PRODUCT2 WHERE NAME LIKE " + "'%" + name + "%' ORDER BY NAME ASC")
                .addEntity(Product.class).list();

        session.close();

        return list;
    }

    public static List findByPrice(int price, int delta) {
        Session session = createSessionFactory().openSession().getSession();

        List list = session.createSQLQuery("SELECT * FROM PRODUCT2 WHERE PRICE BETWEEN " + (price - delta) + " AND " + (price + delta))
                .addEntity(Product.class).list();

        session.close();

        return list;
    }

    public static List findByContainedName(String name) {
        Session session = createSessionFactory().openSession().getSession();

        List list = session.createSQLQuery("SELECT * FROM PRODUCT2 WHERE NAME like " + "'%" + name + "%'")
                .addEntity(Product.class).list();

        session.close();

        return list;
    }

    public static List findByName(String name) {
        Session session = createSessionFactory().openSession().getSession();

        List list = session.createSQLQuery("SELECT * FROM PRODUCT2 WHERE NAME = " + "'" + name + "'")
                .addEntity(Product.class).list();

        session.close();

        return list;
    }

    public static Product findById(long id) {
        Session session = createSessionFactory().openSession().getSession();

        Product product = (Product) session.createSQLQuery("SELECT * FROM PRODUCT2 WHERE id = " + id)
                .addEntity(Product.class)
                .getSingleResult();

        session.close();

        return product;
    }

    public static SessionFactory createSessionFactory() {
        //singleton pattern
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}