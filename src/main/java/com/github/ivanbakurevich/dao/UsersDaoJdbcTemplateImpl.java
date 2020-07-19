package com.github.ivanbakurevich.dao;

import com.github.ivanbakurevich.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsersDaoJdbcTemplateImpl implements UsersDao{

    private JdbcTemplate template;

    private final String SQL_SELECT_ALL =
            "SELECT * FROM fix_user";

    private final String SQL_SELECT_BY_ID =
            "SELECT * FROM fix_user WHERE id = ?";

    private final String SQL_SELECT_BY_FIRST_NAME =
            "SELECT * FROM fix_user WHERE first_name = ?";

    public UsersDaoJdbcTemplateImpl(DataSource dataSource){
        this.template = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = (resultSet, i) -> {
        return new User(
                resultSet.getInt("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        );
    };

    @Override
    public List<User> findAllByFirstName(String firstName) {
        return template.query(SQL_SELECT_BY_FIRST_NAME,userRowMapper,firstName);
    }

    @Override
    public User find(Integer id) {
        return template.query(SQL_SELECT_BY_ID,userRowMapper,id).get(0);
    }

    @Override
    public void save(User model) {

    }

    @Override
    public void update(User model) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<User> findAll() {
        return template.query(SQL_SELECT_ALL,userRowMapper);
    }
}
