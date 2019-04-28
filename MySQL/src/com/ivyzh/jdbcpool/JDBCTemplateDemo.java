package com.ivyzh.jdbcpool;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 *  Spring框架对JDBC的简单封装。提供了一个JDBCTemplate对象简化JDBC的开发
 *  * 步骤：
 * 	1. 导入jar包（5个）
 * 	2. 创建JdbcTemplate对象。依赖于数据源DataSource
 * 		* JdbcTemplate template = new JdbcTemplate(ds);
 *
 * 	3. 调用JdbcTemplate的方法来完成CRUD的操作
 * 		* update():执行DML语句。增、删、改语句
 * 		* queryForMap():查询结果将结果集封装为map集合，将列名作为key，将值作为value 将这条记录封装为一个map集合
 * 			* 注意：这个方法查询的结果集长度只能是1
 * 		* queryForList():查询结果将结果集封装为list集合
 * 			* 注意：将每一条记录封装为一个Map集合，再将Map集合装载到List集合中
 * 		* query():查询结果，将结果封装为JavaBean对象
 * 			* query的参数：RowMapper
 * 				* 一般我们使用BeanPropertyRowMapper实现类。可以完成数据到JavaBean的自动封装
 * 				* new BeanPropertyRowMapper<类型>(类型.class)
 * 		* queryForObject：查询结果，将结果封装为对象
 * 			* 一般用于聚合函数的查询

 */
public class JDBCTemplateDemo {

    public static void main(String[] args) {
        // JDBCTemplate 快速入门
        JdbcTemplate template = new JdbcTemplate(DruidUtils.getDataSource());
        String sql = "update account set balance = 888";
        int update = template.update(sql);
        System.out.println("受影响的行数："+update);


    }
}
