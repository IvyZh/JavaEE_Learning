package com.ivyzh.usercrm.dao.impl;

import com.ivyzh.usercrm.dao.UserDao;
import com.ivyzh.usercrm.domain.User;
import com.ivyzh.usercrm.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDaoImpl implements UserDao {


   private JdbcTemplate template =  new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<User> findAll() {
        String sql = "select * from user";//todo 不能用 *
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    public List<User> findUserByPage(int currentPage, int pageSize, Map<String, String[]> condition) {
//        SELECT * FROM USER LIMIT 0,5;
//        SELECT * FROM USER LIMIT 5,5;
//        SELECT * FROM USER LIMIT 10,5;
        //SELECT * FROM USER  WHERE username LIKE  "%张%" LIMIT 0,2;

        String sql = "select * from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        ArrayList<Object> params = new ArrayList<>();
        Set<String> set = condition.keySet();
        for (String key : set) {
            String value = condition.get(key)[0];
            // 要过滤掉分页查询的条件
            if("currentPage".equalsIgnoreCase(key)||"pageSize".equalsIgnoreCase(key)){
                continue;
            }
            if(value!=null&&value.length()>0){

                sb.append(" and "+key+" like ?");
                params.add("%"+value+"%");
            }
        }
        sb.append(" LIMIT ?,? ");
        System.out.println("sql:"+sb.toString());
        int start = (currentPage-1)*pageSize;
        params.add(start);
        params.add(pageSize);
        for (int i = 0; i < params.size(); i++) {
            System.out.println(params.get(i));
        }
        List<User> users = template.query(sb.toString(), new BeanPropertyRowMapper<User>(User.class),params.toArray());
        return users;

    }


    @Override
    public User findByUserNameAndPwd(User user) {
        User u = null;
        try {
            String sql = "select * from user where username = ? and password = ?";
            u =  template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),user.getUsername(),user.getPassword());
        }catch (Exception e){
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public boolean add(User user) {
        String sql = "INSERT INTO USER VALUES(NULL,?,'123',?,?,?,?,?,?,NULL);";
        //INSERT INTO USER VALUES(NULL,"zhangsan","123","13888888888","tim@163.com",0,"723723823","杭州",55,NULL);
        int update = template.update(sql, user.getUsername()
                , user.getPhone(), user.getEmail(), user.getGender(), user.getQQ(), user.getAddress(), user.getAge());
        return update>0;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from user where id = ?";
        int update = template.update(sql, id);
        return update>0;
    }

    @Override
    public User findUserById(int id) {
        User u = null;
        try {
            String sql = "select * from user where id = ? ";
            u =  template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public boolean update(User user) {
        String sql = "UPDATE USER SET gender = ?,age = ?,address=?,QQ=?,email=? WHERE id = ?";
        //UPDATE USER SET  gender = 1,age = 18,address='福建',QQ='123213',email='12@1.com' WHERE id = 5;
        int update = template.update(sql, user.getGender()
                , user.getAge(), user.getAddress(), user.getQQ(), user.getEmail(), user.getId());
        return update>0;
    }

    @Override
    public Long getAllCount(Map<String, String[]> condition) {
//        String sql = "select count(*) from user";//SELECT COUNT(*) FROM USER WHERE username LIKE "%张%";
        //SELECT COUNT(*) FROM USER WHERE 1 = 1 and username LIKE "%张%";
        String sql = "select count(*) from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        ArrayList<Object> params = new ArrayList<>();
        Set<String> set = condition.keySet();
        for (String key : set) {
            // 要过滤掉分页查询的条件
            if("currentPage".equalsIgnoreCase(key)||"pageSize".equalsIgnoreCase(key)){
                continue;
            }
            String value = condition.get(key)[0];
            if(value!=null&&value.length()>0){

                sb.append(" and "+key+" like ?");
                params.add("%"+value+"%");
            }
        }
        System.out.println(sb.toString());
        Long num = template.queryForObject(sb.toString(), Long.class,params.toArray());
        return num;
    }


}
