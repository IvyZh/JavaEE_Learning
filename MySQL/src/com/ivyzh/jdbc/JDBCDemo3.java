package com.ivyzh.jdbc;

import com.ivyzh.jdbc.domain.Account;
import com.ivyzh.jdbc.domain.JDBCUtils;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;

/**
 *  练习：
 * 1. account表 添加一条记录
 * 2. account表 修改记录
 * 3. account表 删除一条记录
 */

public class JDBCDemo3 {


    @Test
    public void add(){
        Connection conn = null;
        Statement statement = null;

        try {
            conn = JDBCUtils.getConnection();
            statement = conn.createStatement();
            String sql = "INSERT INTO account VALUES(NULL,'wangwu',7000)";
            int count = statement.executeUpdate(sql);
            System.out.println("影响的行数："+count);
            if(count > 0){
                System.out.println("添加成功！");
            }else{
                System.out.println("添加失败！");
            }
            statement.close();
            conn.close();
        }   catch (SQLException e) {
            e.printStackTrace();
        }finally {
          JDBCUtils.close(statement,conn);
        }
    }

    @Test
    public  void update(){
        Connection conn = null;
        Statement statement = null;

        try {
            conn = JDBCUtils.getConnection();
            statement = conn.createStatement();
            String sql = "UPDATE account SET balance = 10000 WHERE NAME = 'wangwu'";
            int count = statement.executeUpdate(sql);
            System.out.println("影响的行数："+count);
            if(count > 0){
                System.out.println("修改成功！");
            }else{
                System.out.println("修改失败！");
            }
            statement.close();
            conn.close();
        }   catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(statement,conn);


        }
    }

    @Test
    public  void delete(){
        Connection conn = null;
        Statement statement = null;

        try {

            conn = JDBCUtils.getConnection();
            statement = conn.createStatement();
            String sql = "DELETE FROM account WHERE id = 5";
            int count = statement.executeUpdate(sql);
            System.out.println("影响的行数："+count);
            if(count > 0){
                System.out.println("删除成功！");
            }else{
                System.out.println("删除失败！");
            }
            statement.close();
            conn.close();
        }   catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(statement,conn);


        }
    }


    // 定义一个方法，查询emp表的数据将其封装为对象，然后装载集合，返回
    @Test
    public  void query(){
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {

            conn = JDBCUtils.getConnection();
            statement = conn.createStatement();
            String sql = "select * from account";
//            int count = statement.executeUpdate(sql);
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
            JDBCUtils.close(statement,conn,resultSet);


        }
    }


}
