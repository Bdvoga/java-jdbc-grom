package lesson5.ex1;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.ExternalSessionFactoryConfig;

public class HibernateUtils {

    private SessionFactory sessionFactory;

    public SessionFactory createSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }

    public void shutDown() {
        sessionFactory.close();
    }
}
