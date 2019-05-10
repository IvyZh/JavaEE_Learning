package com.ivyzh.province.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivyzh.jedis.JedisPoolUtils;
import com.ivyzh.province.dao.ProvinceDao;
import com.ivyzh.province.dao.impl.ProvinceDaoImpl;
import com.ivyzh.province.domain.Province;
import com.ivyzh.province.service.ProvinceService;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.List;

public class ProvinceServiceImpl implements ProvinceService {
   ProvinceDao provinceDao =  new ProvinceDaoImpl();
    @Override
    public List<Province> getAllProvinces() {
        // 先在redis查询，如果没有在从数据查询
        String provinces=null;
        Jedis jedis=null;
        try {
            jedis = JedisPoolUtils.getJedis();// ConnectException: Connection refused: connect，如果连接异常，则不用redis！！！
            provinces = jedis.get("provinces");//直接在jedis里面存String！！
        }catch (Exception e){
            e.printStackTrace();
        }


        ObjectMapper mapper = new ObjectMapper();
        if(provinces==null||provinces.length()<=0){
            System.out.println("Jedis 里面没有Provices数据，数据库查询...");
            List<Province> list = provinceDao.findAll();
            //将list转成json str
            if(jedis!=null){
                try {
                    String provincesJson = mapper.writeValueAsString(list);
                    jedis.set("provinces",provincesJson);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }finally {
                    jedis.close();
                }
            }

            return list;
        }else {//
            System.out.println("Jedis 里面有Provices数据");
            // provinces json str --> list
            List<Province> list = null;
            try {
                list = mapper.readValue(provinces, List.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        }

    }
}
