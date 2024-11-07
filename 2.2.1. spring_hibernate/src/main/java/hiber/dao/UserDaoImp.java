package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User getUserFromTable(User user) {
        return sessionFactory.getCurrentSession().get(User.class, user.getId());
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User getUserWithModelAndSeries(String model, int series) {
        String hql = "SELECT c.user FROM Car c WHERE c.model = :model AND c.series = :series";
        Query<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        List<User> userList = query.getResultList();
        return userList.get(0);
    }

    @Override
    @SuppressWarnings("uncheked")
    public List getUsersList() {
        Query query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }
}
