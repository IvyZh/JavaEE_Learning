package com.ivyzh.usercrm.dao;

import com.ivyzh.usercrm.domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    List<User> findAll();
    List<User> findUserByPage(int currentPage, int pageSize, Map<String, String[]> condition);
    User findByUserNameAndPwd(User user);
    boolean add(User user);
    boolean delete(int id);
    User findUserById(int id);
    boolean update(User user);
    Long getAllCount(Map<String, String[]> condition);
}
