package com.ivyzh.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Jedis_连接池工具类
 */
public class JedisPoolUtils {
    private static JedisPool jedisPool;
    static {
        Properties pro = new Properties();
        InputStream is = JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        try {
            pro.load(is);
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
            config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));
            jedisPool = new JedisPool(config, pro.getProperty("host"), Integer.parseInt(pro.getProperty("port")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取jedis
     * @return
     */
    public static Jedis getJedis(){
        return jedisPool.getResource();//redis.clients.jedis.exceptions.JedisConnectionException: java.net.ConnectException: Connection refused: connect
    }

}
