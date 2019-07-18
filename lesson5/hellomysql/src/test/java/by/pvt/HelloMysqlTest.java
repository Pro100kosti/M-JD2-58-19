package by.pvt;

import static org.junit.Assert.*;

import by.pvt.dto.SystemUsers;
import by.pvt.service.SystemUsersService;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Date;

public class HelloMysqlTest extends DBTestCase {

    public HelloMysqlTest(String name) {
        super(name);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/hello_mysql_junit");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "gjhjkmghjcnjrjcnb");
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(HelloMysqlTest.class.getResourceAsStream("system_users.xml"));
    }

    @Test
    public void testConnection() {

        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/hello_mysql_junit", "root", "gjhjkmghjcnjrjcnb");
            PreparedStatement ps = connection.prepareStatement("select * from system_users");

            ResultSet rs = ps.executeQuery();
            assertNotNull(rs);

            int rawCount = 0;
            int activeUser = 0;

            while (rs.next()) {
                rawCount++;
                if (rs.getBoolean("active")) activeUser++;
            }
            assertEquals(4, rawCount);
            assertEquals(2, activeUser);

            rs.close();
            ps.close();
            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsert() {
        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/hello_mysql", "root", "root");

            SystemUsers systemUser = new SystemUsers();
            systemUser.setId(6);
            systemUser.setUsername("testUser");
            systemUser.setActive(false);
            systemUser.setDateofbirth(new Date());
            new SystemUsersService().add(systemUser);

            PreparedStatement ps = connection.prepareStatement("select * from system_users where id=6");
            ResultSet rs = ps.executeQuery();

            int id = 0;
            String username = "";
            boolean active = true;

            while (rs.next()) {
                id = rs.getInt("id");
                username = rs.getString("username");
                active = rs.getBoolean("active");
            }

            assertEquals(6, id);
            assertEquals("testUser", username);
            assertEquals(false, active);

            rs.close();
            ps.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate(){
        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/hello_mysql", "root", "root");

            SystemUsers systemUser = new SystemUsers();
            systemUser.setId(6);
            systemUser.setUsername("UpdatedTestUser");
            systemUser.setActive(true);
            new SystemUsersService().update(systemUser);

            PreparedStatement ps = connection.prepareStatement("select * from system_users where id=6");
            ResultSet rs = ps.executeQuery();

            int id = 0;
            String username = "";
            boolean active = true;

            while (rs.next()) {
                id = rs.getInt("id");
                username = rs.getString("username");
                active = rs.getBoolean("active");
            }

            assertEquals(6, id);
            assertEquals("UpdatedTestUser", username);
            assertEquals(true, active);

            rs.close();
            ps.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testDelete(){
        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/hello_mysql", "root", "root");

            new SystemUsersService().delete(6);

            PreparedStatement ps = connection.prepareStatement("select * from system_users");
            ResultSet rs = ps.executeQuery();

            int rowCount = 0;

            while (rs.next()) {
                rowCount++;
            }

            assertEquals(1, rowCount);

            rs.close();
            ps.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
