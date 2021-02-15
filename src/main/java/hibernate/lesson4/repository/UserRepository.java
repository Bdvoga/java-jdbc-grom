package hibernate.lesson4.repository;

import hibernate.lesson4.model.Seance;
import hibernate.lesson4.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserRepository extends GeneralRepository<User>{
    GeneralRepository<User> generalRepository = new GeneralRepository<>(User.class);

    public void login(String userName, String password) throws Exception {
        if (userName.equals(findByName(userName).getUserName()) && password.equals(findByName(userName).getPassword())) {
            Seance.setUser(findByName(userName));

            return;
        }
        System.err.println("Данные не найдены");
    }

    public User findByName (String name) {
        Session session = createSessionFactory().openSession();

        User user = (User) session.createQuery("from User where userName = " + "'" + name + "'").uniqueResult();

        session.close();

        return user;
    }

    public User findById(long id) {
        return generalRepository.findById(id);
    }

    public User update(User user) {
        Transaction tr = null;
        try(Session session = createSessionFactory().openSession()) {
            tr = session.getTransaction();
            tr.begin();

            session.get(User.class, user.getId()).setUserName(user.getUserName());
            session.get(User.class, user.getId()).setPassword(user.getPassword());
            session.get(User.class, user.getId()).setCountry(user.getCountry());
            session.get(User.class, user.getId()).setUserType(user.getUserType());

            tr.commit();

            return session.get(User.class, user.getId());
        } catch (HibernateException e) {
            System.err.println("Update is failed");
            System.err.println(e.getMessage());
            if (tr != null) {
                tr.rollback();
            }
        }

        return null;
    }
}