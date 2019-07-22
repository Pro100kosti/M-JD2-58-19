package by.pvt.service;

import by.pvt.dao.SystemUsersMapper;
import by.pvt.dto.SystemUsers;
import by.pvt.dto.SystemUsersExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
//import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SystemUsersService {

    private static Logger log = Logger.getLogger(SystemUsersService.class.getName());

    private SqlSessionFactory sqlSessionFactory;

    public SystemUsersService() {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(
                    Resources.getResourceAsStream("by/pvt/service/mybatis-config.xml")
                    //SystemUsersService.class.getResourceAsStream("mybatis-config.xml")
            );
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    protected void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<SystemUsers> getSystemUsers() {
        return getSystemUsers(null);
    }

    public List<SystemUsers> getSystemUsers(SystemUsersExample example) {
        SqlSession session = sqlSessionFactory.openSession();
        SystemUsersMapper dao =
                session.getMapper(SystemUsersMapper.class);

        List<SystemUsers> dtoUsers
                = dao.selectByExample(example);

        session.close();
        return dtoUsers;
    }

    public void add(SystemUsers systemUser) {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        int result = sqlSession
                .getMapper(SystemUsersMapper.class)
                .insert(systemUser);
        sqlSession.commit();
        sqlSession.close();

        log.info("Added new systemUser with result= " + result);
    }

    public void addAll(List<SystemUsers> systemUsers) {
        if (systemUsers == null) {
            log.info("The input systemUsers is null");
            return;
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        if (sqlSession == null){
            log.info("Session is null");
            return;
        }
        SystemUsersMapper dao = sqlSession.getMapper(SystemUsersMapper.class);
        try {
            systemUsers.stream()
                    .filter(Objects::nonNull)
                    .forEach(dao::insert);
            sqlSession.commit();
        } catch (Exception e) {
            log.log(Level.WARNING, e.getMessage(), e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    public void delete(int id) {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        sqlSession
                .getMapper(SystemUsersMapper.class)
                .deleteByPrimaryKey(id);
        sqlSession.commit();
        sqlSession.close();

        log.info("Deleted systemUser with id= " + id);
    }

    public void update(SystemUsers systemUser) {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        sqlSession
                .getMapper(SystemUsersMapper.class)
                .updateByPrimaryKey(systemUser);
        sqlSession.commit();
        sqlSession.close();

        log.info("Updated systemUser with id= " + systemUser.getId());
    }

//    public static void main(String[] args) {
//        SystemUsers systemUser = new SystemUsers();
//        systemUser.setId(2);
//        systemUser.setUsername("User2");
//        systemUser.setActive(false);
//        systemUser.setDateofbirth(new Date());
//
//        new SystemUsersService().add(systemUser);
//
//        systemUser.setUsername("Updated_User2");
//        new SystemUsersService().update(systemUser);
//
//        new SystemUsersService()
//                .getSystemUsers()
//                .forEach(user ->
//                        log.info(user.getId() + " "
//                                + user.getUsername() + " "
//                                + user.getActive()));
//
//        new SystemUsersService().delete(2);
//    }
}
