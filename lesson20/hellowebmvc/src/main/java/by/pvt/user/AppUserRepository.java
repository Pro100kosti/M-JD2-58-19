package by.pvt.user;

//import by.pvt.pojo.AppUser;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class AppUserRepository {
//
//    @Autowired
//    SessionFactory sessionFactory;
//
//
//    public Integer findUserCountByLogin(String username) {
//        return sessionFactory.getCurrentSession()
//                .createQuery("from AppUser where username like :param1", AppUser.class)
//                .setParameter("param1", username)
//                .getFetchSize();
//    }
//
//    public void save(AppUser user) {
//        sessionFactory.getCurrentSession().persist(user);
//    }
//}
