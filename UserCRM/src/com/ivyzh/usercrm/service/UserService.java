package com.ivyzh.usercrm.service;

import com.ivyzh.usercrm.domain.PageBean;
import com.ivyzh.usercrm.domain.User;

import java.util.Map;

public interface UserService {
    PageBean<User> findAllUser(String currentPage, String pageSize, Map<String, String[]> condition);
    User login(User user);
    boolean add(User user);
    boolean delete(int id);
    User findUserById(int id);

    boolean update(User user);

    boolean deleteSeletedUser(String[] uids);
}
