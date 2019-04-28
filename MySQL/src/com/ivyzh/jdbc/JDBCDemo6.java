package com.ivyzh.jdbc;

import com.ivyzh.jdbc.domain.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * * JDBC管理事务_实现
 * 使用Connection对象来管理事务
 * 	* 开启事务：setAutoCommit(boolean autoCommit) ：调用该方法设置参数为false，即开启事务
 * 		* 在执行sql之前开启事务
 * 	* 提交事务：commit()
 * 		* 当所有sql都执行完提交事务
 * 	* 回滚事务：rollback()
 * 		* 在catch中回滚事务
 */

public class JDBCDemo6 {
    public static void main(String[] args)   {
        Connection connection = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);//调用该方法设置参数为false，即开启事务
            String sql1 = "update account set balance = balance - ? where id = ? ";
            String sql2 = "update account set balance = balance + ? where id = ? ";
            pstmt1 = connection.prepareStatement(sql1);
            pstmt2 = connection.prepareStatement(sql2);

            pstmt1.setInt(1,500);
            pstmt1.setInt(2,1);
            pstmt2.setInt(1,500);
            pstmt2.setInt(2,2);

            int count1 = pstmt1.executeUpdate();
            //int r  = 6/0;
            int count2 = pstmt2.executeUpdate();
            System.out.println("转账成功");
            connection.commit();

        } catch (Exception e) {
            System.out.println("转账失败,准备回滚");
            try {
                if(connection!=null)
                 connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt1,connection);
            JDBCUtils.close(pstmt2,connection);
        }

    }

}
