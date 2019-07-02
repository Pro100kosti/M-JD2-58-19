package by.pvt;

import static org.junit.Assert.*;
import org.junit.Test;

import java.sql.*;

public class HelloMysqlTest {

    @Test
    public void testConnection(){

        try {
            Connection connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/hello_mysql", "root", "gjhjkmghjcnjrjcnb");
            PreparedStatement ps = connection.prepareStatement("select * from system_users");

            ResultSet rs = ps.executeQuery();

            assertNotNull(rs);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
