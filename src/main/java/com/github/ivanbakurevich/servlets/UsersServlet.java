package com.github.ivanbakurevich.servlets;

import com.github.ivanbakurevich.dao.UsersDao;
import com.github.ivanbakurevich.dao.UsersDaoJdbcImpl;
import com.github.ivanbakurevich.dao.UsersDaoJdbcTemplateImpl;
import com.github.ivanbakurevich.model.User;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private UsersDao usersDao;

    @Override
    public void init() throws ServletException {
        Properties properties = new Properties();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        try {
            properties.load(new FileInputStream(getServletContext().getRealPath("WEB-INF/classes/db.properties")));
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String dbConnectionUrl = properties.getProperty("db.url");
            String dbDriverClassName = properties.getProperty("db.driverClassName");

            dataSource.setUsername(dbUsername);
            dataSource.setPassword(dbPassword);
            dataSource.setUrl(dbConnectionUrl);
            dataSource.setDriverClassName(dbDriverClassName);


            usersDao = new UsersDaoJdbcTemplateImpl(dataSource);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users;
        if (req.getParameter("firstName") != null) {
            String firstName = req.getParameter("firstName");
            users = usersDao.findAllByFirstName(firstName);
        } else if (req.getParameter("id") != null) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            User user = usersDao.find(id);
            users = new ArrayList<>();
            users.add(user);
        } else {
            users = usersDao.findAll();
        }
        req.setAttribute("usersFromServer", users);
        req.getServletContext().getRequestDispatcher("/jsp/users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
