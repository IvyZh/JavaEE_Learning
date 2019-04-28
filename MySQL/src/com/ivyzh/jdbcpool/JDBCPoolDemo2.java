package com.ivyzh.jdbcpool;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
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
public class JDBCPoolDemo2 {
    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        InputStream is = JDBCPoolDemo2.class.getClassLoader().getResourceAsStream("druid.properties");
        properties.load(is);
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        Connection connection = dataSource.getConnection();

        System.out.println(connection);
    }
}
