package co.inventorsoft.servlets;

import co.inventorsoft.dao.CrudDao;
import co.inventorsoft.dao.UsersDaoJdbcTemplateImpl;
import co.inventorsoft.models.User;
import com.google.gson.Gson;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@WebServlet("/users")
public class UsersServletWithDao extends HttpServlet {
    private CrudDao usersDao;

    private Connection connection;
    @Override
    public void init() throws ServletException {
        Properties properties = new Properties();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        try {
            properties.load(new FileInputStream(getServletContext().getRealPath("/WEB-INF/classes/db.properties")));
            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String driverClassName = properties.getProperty("db.driverClassName");

            dataSource.setUsername(dbUsername);
            dataSource.setPassword(dbPassword);
            dataSource.setUrl(dbUrl);
            dataSource.setDriverClassName(driverClassName);

            usersDao = new UsersDaoJdbcTemplateImpl(dataSource);
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (IOException | SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<User> users = null;
        users = usersDao.findAll();
        String json = new Gson().toJson(users);
        req.setAttribute("usersFromServer", json);
        resp.getWriter().write(json);
        req.getServletContext().getRequestDispatcher("/jsp/users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("first-name");
        String lastName = req.getParameter("last-name");
        String codeWord = req.getParameter("code-word");
        String favFilm = req.getParameter("fav-film");

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO " +
                            "inv_user(first_name, last_name, code_word, fav_film) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, codeWord);
            preparedStatement.setString(4, favFilm);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
