package com.ivyzh.usercrm.service.impl;

import com.ivyzh.usercrm.dao.UserDao;
import com.ivyzh.usercrm.dao.impl.UserDaoImpl;
import com.ivyzh.usercrm.domain.PageBean;
import com.ivyzh.usercrm.domain.User;
import com.ivyzh.usercrm.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
  private UserDao userDao =   new UserDaoImpl();
    @Override
    public PageBean<User> findAllUser(String currentPage, String pageSize, Map<String, String[]> condition) {
        int size = Integer.parseInt(pageSize);
        int cpage = Integer.parseInt(currentPage);
        if(cpage==0){
            cpage=1;
        }


        PageBean<User>  page = new PageBean<>();
        List<User> list = userDao.findUserByPage(cpage,size,condition);
        Long totalCount = userDao.getAllCount(condition);
        page.setTotalCount(totalCount);//总数量
        page.setList(list);//数据
        page.setPageSize(size);//每页数据大小
        page.setCurrentPage(cpage);
       int totalPage = (int) (totalCount%size==0?totalCount/size:totalCount/size+1);
       page.setTotalPage(totalPage);//共多少页
        return page;
    }

    @Override
    public User login(User user) {
        return userDao.findByUserNameAndPwd(user);
    }

    @Override
    public boolean add(User user) {
        return userDao.add(user);
    }

    @Override
    public boolean delete(int id) {
        return userDao.delete(id);
    }

    @Override
    public User findUserById(int id) {
        return userDao.findUserById(id);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean deleteSeletedUser(String[] uids) {
        for (String uid : uids) {
            int id = Integer.parseInt(uid);
            userDao.delete(id);
        }
        return false;
    }
}
