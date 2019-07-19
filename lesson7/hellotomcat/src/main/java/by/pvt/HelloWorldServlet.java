package by.pvt;

import by.pvt.dto.SystemUsers;
import by.pvt.service.SystemUsersService;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/helloWorld", name="helloWorldServlet")
public class HelloWorldServlet extends HttpServlet {

    private SystemUsersService systemUsersService;

    @Override
    public void init() throws ServletException {
        systemUsersService = new SystemUsersService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        resp.setHeader("Content-Type", "application/json");
        List<SystemUsers> users = systemUsersService.getSystemUsers();
        System.out.println("User size= " + users.size());
        resp.getWriter().print("{\"message\": \"hello, world!\"}");
    }

}
