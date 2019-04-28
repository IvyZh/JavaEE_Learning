package com.ivyzh.jdbcpool.domain;

import java.util.Date;

/**
 * 使用BeanPropertyRowMapper，要写setter方法
 */
public class Employee {
    private int id;
    private String ename;
    private int job_ID;//todo，这样也可以
    private Integer mgr;//因为表中mgr 也存在null值
    private Date joindate;
    private Double salary;//todo,如果用BeanPropertyRowMapper，则需要用Double,
    private Double bonus;
    private int dept_id;

    public void setId(int id) {
        this.id = id;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public int getJob_ID() {
        return job_ID;
    }

    public void setJob_ID(int job_ID) {
        this.job_ID = job_ID;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", ename='" + ename + '\'' +
                ", job_Id=" + job_ID +
                ", mgr=" + mgr +
                ", joindate=" + joindate +
                ", salary=" + salary +
                ", bonus=" + bonus +
                ", dept_id=" + dept_id +
                '}';
    }
}
