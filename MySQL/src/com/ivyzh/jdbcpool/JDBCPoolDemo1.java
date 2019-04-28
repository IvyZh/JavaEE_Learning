package com.ivyzh.jdbcpool;

/**
 * 概念：其实就是一个容器(集合)，存放数据库连接的容器。
 * 	    当系统初始化好后，容器被创建，容器中会申请一些连接对象，当用户来访问数据库时，从容器中获取连接对象，用户访问完之后，会将连接对象归还给容器。
 *
 * 	3. 实现：
 * 	1. 标准接口：DataSource   javax.sql包下的
 * 		1. 方法：
 * 			* 获取连接：getConnection()
 * 			* 归还连接：Connection.close()。如果连接对象Connection是从连接池中获取的，那么调用Connection.close()方法，则不会再关闭连接了。而是归还连接
 *
 * 	2. 一般我们不去实现它，有数据库厂商来实现
 * 		1. C3P0：数据库连接池技术
 * 		2. Druid（德鲁伊）：数据库连接池实现技术，由阿里巴巴提供的
 */


import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**演示C3P0数据库连接池
 *4. C3P0：数据库连接池技术
 * 	* 步骤：
 * 		1. 导入jar包 (两个) c3p0-0.9.5.2.jar mchange-commons-java-0.2.12.jar ，
 * 			* 不要忘记导入数据库驱动jar包
 * 		2. 定义配置文件：
 * 			* 名称： c3p0.properties 或者 c3p0-config.xml
 * 			* 路径：直接将文件放在src目录下即可。
 * 		3. 创建核心对象 数据库连接池对象 ComboPooledDataSource
 * 		4. 获取连接： getConnection
 */
public class JDBCPoolDemo1 {
    public static void main(String[] args) throws SQLException {

        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        ComboPooledDataSource dataSource = new ComboPooledDataSource("otherc3p0");
//        Connection conn = dataSource.getConnection();
//        System.out.println(conn);

        // c3p0_配置演示
//    <property name="initialPoolSize">5</property>
//    <property name="maxPoolSize">10</property>
//    <property name="checkoutTimeout">3000</property>

        for (int i = 0; i < 11; i++) {
            Connection connection = dataSource.getConnection();
            System.out.println(i+"-->"+connection);
            if(i==5)
                connection.close();//归还连接到连接池中
        }


    }
}
