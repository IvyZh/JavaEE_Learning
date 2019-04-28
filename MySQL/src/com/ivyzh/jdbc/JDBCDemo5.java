package com.ivyzh.jdbc;

import com.ivyzh.jdbc.domain.JDBCUtils;

import java.sql.*;

/**
 * * 解决sql注入问题：使用PreparedStatement对象来解决
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

public class JDBCDemo5 {
    public static void main(String[] args) {
//        boolean login = login("zhangsan", "1423' or 'a' = 'a");//SQL注入问题
        boolean login = login("zhangsan", "123");//SQL注入问题
        System.out.println("login:"+login);
    }

    public static boolean login(String user,String pwd){
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from user where username = ? and password = ?";
            statement = conn.prepareStatement(sql);
            System.out.println(sql);
            statement.setString(1,user);
            statement.setString(2,pwd);

            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(statement,conn,resultSet);
        }
        return false;
    }

}
