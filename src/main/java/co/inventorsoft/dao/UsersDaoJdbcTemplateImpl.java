package co.inventorsoft.dao;

import co.inventorsoft.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UsersDaoJdbcTemplateImpl implements CrudDao{

    private JdbcTemplate template;

    private final String SQL_SELECT_ALL = "SELECT * FROM inv_user";

    private Map<Integer, User> usersMap = new HashMap<>();

    public UsersDaoJdbcTemplateImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = (ResultSet resultSet, int i) -> {
        Integer id = resultSet.getInt("id");

        if (!usersMap.containsKey(id)) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String codeWord = resultSet.getString("code_word");
            String favFilm = resultSet.getString("fav_film");
            User user = new User(id, firstName, lastName, codeWord, favFilm);
            usersMap.put(id, user);
        }
        return usersMap.get(id);
    };

    @Override
    public Optional find(Integer id) {
        template.query(SQL_SELECT_ALL, userRowMapper, id);

        if (usersMap.containsKey(id)) {
            return Optional.of(usersMap.get(id));
        }
        return Optional.empty();
    }

    @Override
    public void save(Object model) {
    }

    @Override
    public void update(Object model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List findAll() {
        return template.query(SQL_SELECT_ALL, userRowMapper);
    }
}
