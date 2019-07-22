package by.pvt;

import by.pvt.dto.SystemUsers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.*;

public class HelloWorldServletTest {

    @Test
    public void testMapper() {
        SystemUsers systemUsers = new SystemUsers();
        systemUsers.setDateofbirth(new Date());
        systemUsers.setActive(true);
        systemUsers.setUsername("TESTUser");
        systemUsers.setId(1001);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(systemUsers);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assertNotNull(json);

        String jsonInput = "{\"id\":1002," +
                "\"username\":\"TESTUser2\"," +
                "\"active\":false," +
                "\"dateofbirth\":1563812700000}";

        try {
           SystemUsers systemUsers2
                   = objectMapper.readValue(jsonInput, SystemUsers.class);
           assertEquals(1002, systemUsers2.getId(), 0);
           assertEquals("TESTUser2", systemUsers2.getUsername());
            System.out.println(systemUsers2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}