package com.github.ivanbakurevich.dao;

import com.github.ivanbakurevich.model.User;

import java.util.List;

public interface UsersDao extends CrudDao<User>{
    List<User> findAllByFirstName(String firstname);
}
