package hibernate.lesson2.ex2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class ProductDAO {

    private static SessionFactory sessionFactory = createSessionFactory();

    public static List findByPriceSortedDesc(int price, int delta) {
        Session session = createSessionFactory().openSession().getSession();

        List list = session.createQuery("from Product where price between " + (price - delta) + " and " + (price + delta) + " order by price desc").list();

        session.close();

        return list;
    }

    public static List findByNameSortedDesc(String name) {
        Session session = createSessionFactory().openSession().getSession();

        List list = session.createQuery("from Product where name like " + "'%" + name + "%' order by name desc").list();

        session.close();

        return list;
    }

    public static List findByNameSortedAsc(String name) {
        Session session = createSessionFactory().openSession().getSession();

        List list = session.createQuery("from Product where name like " + "'%" + name + "%' order by name asc").list();

        session.close();

        return list;
    }

    public static List findByPrice(int price, int delta) {
        Session session = createSessionFactory().openSession().getSession();

        List list = session.createQuery("from Product where price between " + (price - delta) + " and " + (price + delta)).list();

        session.close();

        return list;
    }

    public static List findByContainedName (String name) {
        Session session = createSessionFactory().openSession().getSession();

        List list = session.createQuery("from Product where name like " + "'%" + name + "%'").list();

        session.close();

        return list;
    }

    public static List findByName(String name) {
        Session session = createSessionFactory().openSession().getSession();

        List list = session.createQuery("from Product where name = " + "'" + name + "'").list();

        session.close();

        return list;
    }

    public static Product findById(long id) {
        Session session = sessionFactory.openSession().getSession();

        Product product = (Product) session.createQuery("from Product where id = " + id).getSingleResult();

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
