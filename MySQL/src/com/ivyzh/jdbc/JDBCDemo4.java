package com.ivyzh.jdbc;

import com.ivyzh.jdbc.domain.Account;
import com.ivyzh.jdbc.domain.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * * 练习：
 * 	* 需求：
 * 		1. 通过键盘录入用户名和密码
 * 		2. 判断用户是否登录成功
 * 			* select * from user where username = "" and password = "";
 * 			* 如果这个sql有查询结果，则成功，反之，则失败
 *
 * 	* 步骤：
 * 		1. 创建数据库表 user
 * 			CREATE TABLE USER(
 * 				id INT PRIMARY KEY AUTO_INCREMENT,
 * 				username VARCHAR(32),
 * 				PASSWORD VARCHAR(32)
 *
 * 			);
 *
 * 			INSERT INTO USER VALUES(NULL,'zhangsan','123');
 * 			INSERT INTO USER VALUES(NULL,'lisi','234');
 */

public class JDBCDemo4 {
    public static void main(String[] args) {
        boolean login = login("zhangsan", "1423' or 'a' = 'a");//SQL注入问题
        System.out.println("login:"+login);
    }

    public static boolean login(String user,String pwd){
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtils.getConnection();
            statement = conn.createStatement();
            String sql = "select * from user where username = '"+user+"'"+"and password = '"+pwd+"'";
            System.out.println(sql);
             resultSet = statement.executeQuery(sql);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(statement,conn,resultSet);
        }
        return false;
    }

}
