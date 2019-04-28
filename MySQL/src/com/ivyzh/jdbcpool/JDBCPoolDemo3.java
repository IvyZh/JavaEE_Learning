package com.ivyzh.jdbcpool;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.ivyzh.jdbc.domain.Account;
import com.ivyzh.jdbc.domain.JDBCUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Druid：数据库连接池实现技术，由阿里巴巴提供的
 * 	1. 步骤：
 * 		1. 导入jar包 druid-1.0.9.jar
 * 		2. 定义配置文件：
 * 			* 是properties形式的
 * 			* 可以叫任意名称，可以放在任意目录下
 * 		3. 加载配置文件：Properties
 * 		4. 获取数据库连接池对象：通过工厂来来获取  DruidDataSourceFactory
 * 		5. 获取连接：getConnection
 */
public class JDBCPoolDemo3 {
    public static void main(String[] args) throws Exception {
            Connection conn = null;
            Statement statement = null;
            ResultSet resultSet = null;
            try {
                conn = DruidUtils.getConn();
                statement = conn.createStatement();
                String sql = "select * from account";

                resultSet = statement.executeQuery(sql);
                ArrayList<Account> accounts = new ArrayList<>();
                Account account;
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    double balance = resultSet.getDouble("balance");
                    System.out.println(id+","+name+","+balance);
                    account = new Account();
                    account.setId(id);
                    account.setBalance(balance);
                    account.setName(name);
                    accounts.add(account);
                }
                statement.close();
                conn.close();
                System.out.println("-----------");
                System.out.println(accounts);


            }  catch (SQLException e) {
                e.printStackTrace();
            }finally {
                DruidUtils.close(resultSet,statement,conn);
            }

    }
}
