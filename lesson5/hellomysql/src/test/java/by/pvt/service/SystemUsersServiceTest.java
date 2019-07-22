package by.pvt.service;

import by.pvt.HelloMysqlTest;
import by.pvt.dto.SystemUsers;
import by.pvt.dto.SystemUsersExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class SystemUsersServiceTest extends DBTestCase {

    SystemUsersService objUnderTest;

    public SystemUsersServiceTest(String name) {
        super(name);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/hello_mysql_junit");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "gjhjkmghjcnjrjcnb");
    }

    @Override
    public IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(HelloMysqlTest.class.getResourceAsStream("system_users.xml"));
    }

    @Test
    public void testGetSystemUsers() throws IOException {
        objUnderTest = new SystemUsersService();
        objUnderTest.setSqlSessionFactory(
                new SqlSessionFactoryBuilder().build(
                Resources.getResourceAsStream("by/pvt/service/mybatis-config-junit.xml")
        ));

        List<SystemUsers> list = objUnderTest.getSystemUsers();

        assertEquals(4, list.size());

        SystemUsersExample systemUsersExample = new SystemUsersExample();
        systemUsersExample.createCriteria().andActiveEqualTo(true);
        List<SystemUsers> list2 = objUnderTest.getSystemUsers(systemUsersExample);

        assertEquals(2, list2.size());
    }

    @Test
    public void add() {
    }

    @Test
    public void addAll() {
    }
}