package com.ivyzh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * JDBC的快速入门
 * Java DataBase Connectivity  Java 数据库连接， Java语言操作数据库
 * JDBC本质：其实是官方（sun公司）定义的一套操作所有关系型数据库的规则，即接口。
 * 各个数据库厂商去实现这套接口，提供数据库驱动jar包。我们可以使用这套接口（JDBC）编程，真正执行的代码是驱动jar包中的实现类。
 */

public class JDBCDemo1 {
    public static void main(String[] args) throws Exception {
        // 1. 导入mysql的jar包
        // 2. 注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //3. 获取数据库连接对象Connection
        //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1","root","123456");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///db1","root","123456");
        // connection的功能：
        // 3.1  获取执行sql 的对象,Statement ，PreparedStatement
        // 3.2 管理事务，setAutoCommit，commit，rollback

        //4. 定义sql
        String sql = "select * from student";
        // 5. 通过数据库连接对象Connection获取sql的执行对象Statement
        Statement statement = connection.createStatement();
        //6.执行sql
        ResultSet resultSet = statement.executeQuery(sql);
        //7. 处理返回数据
        while (resultSet.next()){
            String name = resultSet.getString("name");
            int id = resultSet.getInt("id");
            System.out.println(id+"-->"+name);
        }
        //8.释放资源
        statement.close();
        connection.close();

    }
}
