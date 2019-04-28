package com.ivyzh.jdbcpool;

import com.ivyzh.jdbcpool.domain.Emp;
import com.ivyzh.jdbcpool.domain.Employee;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
 *
 * 	创建数据：
 * 	-- 员工表
 * CREATE TABLE emp (
 *   id INT PRIMARY KEY, -- 员工id
 *   ename VARCHAR(50), -- 员工姓名
 *   job_id INT, -- 职务id
 *   mgr INT , -- 上级领导
 *   joindate DATE, -- 入职日期
 *   salary DECIMAL(7,2), -- 工资
 *   bonus DECIMAL(7,2), -- 奖金
 *   dept_id INT -- 所在部门编号
 * );
 *
 *
 *
 * -- 添加员工
 * INSERT INTO emp(id,ename,job_id,mgr,joindate,salary,bonus,dept_id) VALUES
 * (1001,'孙悟空',4,1004,'2000-12-17','8000.00',NULL,20),
 * (1002,'卢俊义',3,1006,'2001-02-20','16000.00','3000.00',30),
 * (1003,'林冲',3,1006,'2001-02-22','12500.00','5000.00',30),
 * (1004,'唐僧',2,1009,'2001-04-02','29750.00',NULL,20),
 * (1005,'李逵',4,1006,'2001-09-28','12500.00','14000.00',30),
 * (1006,'宋江',2,1009,'2001-05-01','28500.00',NULL,30),
 * (1007,'刘备',2,1009,'2001-09-01','24500.00',NULL,10),
 * (1008,'猪八戒',4,1004,'2007-04-19','30000.00',NULL,20),
 * (1009,'罗贯中',1,NULL,'2001-11-17','50000.00',NULL,10),
 * (1010,'吴用',3,1006,'2001-09-08','15000.00','0.00',30),
 * (1011,'沙僧',4,1004,'2007-05-23','11000.00',NULL,20),
 * (1012,'李逵',4,1006,'2001-12-03','9500.00',NULL,30),
 * (1013,'小白龙',4,1004,'2001-12-03','30000.00',NULL,20),
 * (1014,'关羽',4,1007,'2002-01-23','13000.00',NULL,10);
 *
 * * 需求：
 * 	1. 修改1号数据的 salary 为 10000
 * 	2. 添加一条记录
 * 	3. 删除刚才添加的记录
 * 	4. 查询id为1的记录，将其封装为Map集合
 * 	5. 查询所有记录，将其封装为List
 * 	6. 查询所有记录，将其封装为Emp对象的List集合
 * 	7. 查询总记录数
 *
 * 	JDBCTemplate_执行DML语句
 */
public class JDBCTemplateDemo1 {

    JdbcTemplate template = new JdbcTemplate(DruidUtils.getDataSource());


    //1. 修改1号数据的 salary 为 10000
    @Test
    public  void test01() {
        JdbcTemplate template = new JdbcTemplate(DruidUtils.getDataSource());
        String sql = "update emp set salary = ? where id = ?";
        int update = template.update(sql,999,1001);
        System.out.println("受影响的行数："+update);
    }
    // 2. 添加一条记录
    @Test
    public  void test02() {
        JdbcTemplate template = new JdbcTemplate(DruidUtils.getDataSource());
        String sql = "insert into emp(id,ename,salary) values (?,?,?)";
        int update = template.update(sql,1015,"zs",23423);
        System.out.println("受影响的行数："+update);
    }
    //3. 删除刚才添加的记录
    @Test
    public  void test03() {
        JdbcTemplate template = new JdbcTemplate(DruidUtils.getDataSource());
        String sql = "delete from emp where id = ?";
        int update = template.update(sql,1015);
        System.out.println("受影响的行数："+update);
    }
    //4. 查询id为1的记录，将其封装为Map集合
    @Test
    public  void test04() {
        JdbcTemplate template = new JdbcTemplate(DruidUtils.getDataSource());
        String sql = "select * from emp where id = ?";
        Map<String, Object> result = template.queryForMap(sql, 1001);
        System.out.println("查询结果："+result);
    }


    //5. 查询所有记录，将其封装为List
    @Test
    public  void test05() {
        JdbcTemplate template = new JdbcTemplate(DruidUtils.getDataSource());
        String sql = "select * from emp  ";
        List<Map<String, Object>> result = template.queryForList(sql);
        System.out.println("查询结果："+result);
    }


    //6. 查询所有记录，将其封装为Emp对象的List集合

    @Test
    public  void test06() {
        String sql = "select * from emp  ";

        List<Emp> result = template.query(sql, new RowMapper<Emp>() {
            @Override
            public Emp mapRow(ResultSet resultSet, int i) throws SQLException {
                Emp emp = new Emp();
                int id = resultSet.getInt("id");
                String ename = resultSet.getString("ename");
                int job_id = resultSet.getInt("job_id");
                int mgr = resultSet.getInt("mgr");
                Date joindate = resultSet.getDate("joindate");
                double salary = resultSet.getDouble("salary");
                double bonus = resultSet.getDouble("bonus");
                int dept_id = resultSet.getInt("dept_id");

                //----------------------------
                emp.setId(id);
                emp.setEname(ename);
                emp.setJob_Id(job_id);
                emp.setMgr(mgr);
                emp.setJoindate(joindate);
                emp.setSalary(salary);
                emp.setBonus(bonus);
                emp.setDept_id(dept_id);


                return emp;
            }
        });

//        System.out.println("查询结果："+result);
//        result = null;
        for (Emp emp : result) {
            System.out.println(emp);
        }

    }


    @Test
    public  void test06_1() {
        String sql = "select * from emp  ";
        List<Employee> query = template.query(sql, new BeanPropertyRowMapper<Employee>(Employee.class));
        for (Employee employee : query) {
            System.out.println(employee);
        }

    }

    //7. 查询总记录数
    @Test
    public  void test07() {
        String sql = "select count(*) from emp  ";
        Long number = template.queryForObject(sql, Long.class);
        System.out.println(number);
    }


}
