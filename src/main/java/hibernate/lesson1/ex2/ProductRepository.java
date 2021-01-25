package hibernate.lesson1.ex2;

import org.hibernate.Session;

public class ProductRepository {

    public static Product save(Product product) {
        Session session = startSession();

        session.save(product);

        finishSession(session);

        return product;
    }

    public static void delete(long id) {
        Session session = startSession();

        session.delete(findById(session, id));

        finishSession(session);
    }

    public static Product update(Product product) {
        Session session = startSession();

        Product newProduct = (findById(session, product.getId()));
        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setPrice(product.getPrice());

        finishSession(session);

        return newProduct;
    }

    public static Product findById(Session session, long id) {
        return session.get(Product.class, id);
    }

    public static Session startSession() {
        Session session = new HibernateUtils().createSessionFactory().openSession();
        session.getTransaction().begin(); //начинаем транзакцию

        return session;
    }

    public static void finishSession(Session session) {
        session.getTransaction().commit();
        session.close();
    }
}