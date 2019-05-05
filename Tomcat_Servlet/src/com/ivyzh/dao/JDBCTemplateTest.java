package com.ivyzh.dao;

import com.ivyzh.domain.User;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JDBCTemplateTest {


    @Test
    public void selectAll(){
        System.out.println("select all..");
        JdbcTemplate template = new JdbcTemplate(DruidUtils.getDataSource());
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        for (User user : users) {
            System.out.println(user);
        }
    }
}
