package by.pvt.repository;

import by.pvt.pojo.AppRole;
import by.pvt.pojo.RoleName;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class AppRoleRepository {

    private static Logger log = Logger.getLogger("AppUserRepository");

    @Autowired
    private SessionFactory sessionFactory;

    public void save(AppRole appRole) {
        sessionFactory.getCurrentSession().save(appRole);
    }

    public AppRole findByRoleName(RoleName roleName) {
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery("from AppRole where roleName like :param1", AppRole.class)
                    .setParameter("param1", roleName)
                    .getSingleResult();
        } catch (Exception e) {
            log.warning(e.getMessage());
            return null;
        }
    }

}
