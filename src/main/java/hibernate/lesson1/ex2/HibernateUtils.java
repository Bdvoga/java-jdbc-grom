package hibernate.lesson1.ex2;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static SessionFactory sessionFactory;

    public static SessionFactory createSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }

    public static void shutDown() {
        sessionFactory.close();
    }
}
