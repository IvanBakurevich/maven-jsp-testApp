package com.github.ivanbakurevich.dao;

import com.github.ivanbakurevich.model.Car;
import com.github.ivanbakurevich.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersDaoJdbcTemplateImpl implements UsersDao{

    private JdbcTemplate template;

    private final String SQL_SELECT_ALL =
            "SELECT * FROM fix_user";

    private final String SQL_SELECT_BY_ID =
            "SELECT * FROM fix_user WHERE id = ?";

    private final String SQL_SELECT_BY_FIRST_NAME =
            "SELECT * FROM fix_user WHERE first_name = ?";
//language=SQL
    private final String SQL_SELECT_USER_WITH_CARS =
            "SELECT fix_user.*, fix_car.id as car_id, fix_car.model FROM fix_user LEFT JOIN fix_car ON fix_user.id = fix_car.owner_id WHERE fix_user.id = ?";

    private Map<Integer,User> usersMap = new HashMap<>();

    public UsersDaoJdbcTemplateImpl(DataSource dataSource){
        this.template = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userCarRowMapper = (resultSet, i) -> {
        Integer id = resultSet.getInt("id");

        if(!usersMap.containsKey(id)){
            User user = new User(
                    id,
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    new ArrayList<>()
            );
            usersMap.put(id,user);
        }

        Car car = new Car(
                resultSet.getInt("car_id"),
                resultSet.getString("model"),
                usersMap.get(id)
                );

        usersMap.get(id).getCars().add(car);
        return usersMap.get(id);
    };

    private RowMapper<User> userRowMapper = (resultSet, i) -> new User(
            resultSet.getInt("id"),
            resultSet.getString("first_name"),
            resultSet.getString("last_name")
    );

    @Override
    public List<User> findAllByFirstName(String firstName) {
        return template.query(SQL_SELECT_BY_FIRST_NAME,userRowMapper,firstName);
    }

    @Override
    public User find(Integer id) {
       template.query(SQL_SELECT_USER_WITH_CARS,userCarRowMapper,id);
       return usersMap.get(id);
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
