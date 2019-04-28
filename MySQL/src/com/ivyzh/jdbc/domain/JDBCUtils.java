package com.ivyzh.jdbc.domain;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * * 目的：简化书写
 * * 分析：
 * 	1. 注册驱动也抽取
 * 	2. 抽取一个方法获取连接对象
 * 		* 需求：不想传递参数（麻烦），还得保证工具类的通用性。
 * 		* 解决：配置文件
 * 			jdbc.properties
 * 				url=
 * 				user=
 * 				password=
 * 3. 抽取一个方法释放资源
 */
public   class JDBCUtils {


    private static final String driver  ;
    private static final String url  ;
    private static final String user  ;
    private static final String password  ;

    static {
        Properties properties = new Properties();
        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("pro.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
          driver = properties.getProperty("driver");
          url = properties.getProperty("url");
          user = properties.getProperty("user");
          password = properties.getProperty("password");
        try {
            Class.forName(driver);//1. 注册驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * 获取连接对象
     * @return
     */
    public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(url, user, password);
    }



    /**
     * 关闭资源
     * @param statement
     * @param conn
     */
    public  static void close(Statement statement, Connection conn, ResultSet resultSet){
        if(resultSet!=null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(statement!=null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public  static void close(Statement statement, Connection conn ){
       close(statement,conn,null);
    }
}
