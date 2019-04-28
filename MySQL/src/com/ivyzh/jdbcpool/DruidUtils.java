package com.ivyzh.jdbcpool;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 	1. 定义一个类 JDBCUtils
 * 	2. 提供静态代码块加载配置文件，初始化连接池对象
 * 	3. 提供方法
 * 		1. 获取连接方法：通过数据库连接池获取连接
 * 		2. 释放资源
 * 		3. 获取连接池的方法：有些地方只需要获取连接池，而不需要获取连接池对象！
 */
public class DruidUtils {
    private static   DataSource ds;
    static {
        Properties properties = new Properties();
        InputStream is = JDBCPoolDemo2.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(is);
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // 获取连接对象
    public static Connection getConn() throws SQLException {
        return ds.getConnection();
    }
    // 获取连接池对象
    public static DataSource getDataSource()   {
        return ds;
    }
    /**
     * 释放资源
     */
    public static void close(Statement stmt, Connection conn){
        close(null,stmt,conn);
    }

    public static void close(ResultSet rs , Statement stmt, Connection conn){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn != null){
            try {
                conn.close();//归还连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
