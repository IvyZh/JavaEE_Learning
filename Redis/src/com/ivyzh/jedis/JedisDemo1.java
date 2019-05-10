package com.ivyzh.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisDemo1 {

    @Test
    public void test01(){
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("name","zhangsan");
        jedis.close();
    }

    // Jedis_操作string
    @Test
    public void test02(){
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("name","zhangsan23");
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();
    }

    // Jedis_操作hash
    @Test
    public void test03(){
        Jedis jedis = new Jedis("localhost", 6379);

        jedis.hset("u1","name","tom");
        jedis.hset("u1","age","18");
        jedis.hset("u1","gender","男");

        String name = jedis.hget("u1","name");
        System.out.println(name);
        Map<String, String> u1 = jedis.hgetAll("u1");
        Set<String> set = u1.keySet();
        for (String s : set) {
            System.out.println(s+"------"+u1.get(s));
        }
        jedis.close();
    }

    // Jedis_操作hash
    @Test
    public void test03_1(){
        //1. 获取连接
        Jedis jedis = new Jedis();//如果使用空参构造，默认值 "localhost",6379端口
        //2. 操作
        // 存储hash
        jedis.hset("user","name","lisi");
        jedis.hset("user","age","23");
        jedis.hset("user","gender","female");

        // 获取hash
        String name = jedis.hget("user", "name");
        System.out.println(name);
        // 获取hash的所有map中的数据
        Map<String, String> user = jedis.hgetAll("user");

        // keyset
        Set<String> keySet = user.keySet();
        for (String key : keySet) {
            //获取value
            String value = user.get(key);
            System.out.println(key + ":" + value);
        }

        //3. 关闭连接
        jedis.close();
    }

    // Jedis_操作list
    @Test
    public void test04(){
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.lpush("myList","a","b","c");
        jedis.rpush("myList","a","b","c");// cbaabc

        // 输出
        List<String> myList = jedis.lrange("myList", 0, -1);
        for (String s : myList) {
            System.out.println(s);
        }

        System.out.println("-------------");
        // list 弹出
        String v = jedis.lpop("myList");
        System.out.println(v);
        String rv = jedis.rpop("myList");
        System.out.println(rv);
        System.out.println("-------------");
        myList = jedis.lrange("myList", 0, -1);
        for (String s : myList) {
            System.out.println(s);
        }


        jedis.close();
    }

    // Jedis_操作set
    @Test
    public void test05(){
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.sadd("myset","java","php","c++","php");
        Set<String> myset = jedis.smembers("myset");
        for (String s : myset) {
            System.out.println(s);//无序,不允许重复元素
        }


        jedis.close();
    }


    // Jedis_操作sortedset
    @Test
    public void test06(){
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.zadd("hero",10,"八戒");
        jedis.zadd("hero",30,"悟空");
        jedis.zadd("hero",20,"沙僧");
        Set<String> hero = jedis.zrange("hero", 0, -1);
        for (String s : hero) {
            System.out.println(s);//有序,不允许重复元素
        }


        jedis.close();
    }
}
