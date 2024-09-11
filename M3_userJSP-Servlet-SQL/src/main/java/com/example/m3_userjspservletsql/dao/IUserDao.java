package com.example.m3_userjspservletsql.dao;

import com.example.m3_userjspservletsql.model.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    public void insertUser(User user) throws SQLException;
    public User selectUser(int id)  ;
    public List<User> selectAllUser();
    public boolean delete(int id) throws SQLException;
    public boolean update(User user) throws SQLException;
}
