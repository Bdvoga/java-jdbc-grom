package hibernate.lesson4.Repository;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GeneralRepository {
    private SessionFactory sessionFactory;

    public SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
