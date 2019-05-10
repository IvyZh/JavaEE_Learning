package com.ivyzh.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis_连接池
 * 	* 使用：
 * 		1. 创建JedisPool连接池对象
 * 		2. 调用方法 getResource()方法获取Jedis连接
 */
public class JedisDemo2 {

    @Test
    public  void  test01(){
        ////0.创建一个配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        config.setMaxIdle(10);


        JedisPool jedisPool = new JedisPool(config,"localhost",6379);//端口号写错，报错！JedisConnectionException: Could not get a resource from the pool
        Jedis jedis = jedisPool.getResource();
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();
    }

    // 使用jedis连接池工具优化
    @Test
    public  void  test02(){
        ////0.创建一个配置对象
        Jedis jedis = JedisPoolUtils.getJedis();
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();
    }
}
