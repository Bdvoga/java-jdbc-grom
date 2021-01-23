package lesson5;

import org.hibernate.Session;

public class Demo {
    public static void main(String[] args) {

        Product product = new Product();
        product.setId(99);
        product.setName("table");
        product.setDescription("grey & blue");
        product.setPrice(70);

        save(product);

    }

    private static void save(Product product) {
        Session session = new HibernateUtils().createSessionFactory().openSession();
        session.getTransaction().begin(); //начинаем транзакцию

        session.save(product);

        session.getTransaction().commit();
        session.close();

        System.out.println("Done");
    }
}
