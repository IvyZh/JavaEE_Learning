package com.ivyzh.province.dao.impl;

import com.ivyzh.province.dao.ProvinceDao;
import com.ivyzh.province.domain.Province;
import com.ivyzh.province.utils.DruidPoolUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ProvinceDaoImpl implements ProvinceDao {

    private JdbcTemplate template =  new JdbcTemplate(DruidPoolUtils.getDataSource());

    @Override
    public List<Province> findAll() {
        String sql = "select * from province";
        List<Province> provinces = template.query(sql, new BeanPropertyRowMapper<Province>(Province.class));
        return provinces;
    }
}
