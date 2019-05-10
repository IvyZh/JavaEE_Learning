package com.ivyzh.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivyzh.domain.User;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

/**
 1. JSON转为Java对象
 1. 导入jackson的相关jar包
 2. 创建Jackson核心对象 ObjectMapper
 3. 调用ObjectMapper的相关方法进行转换
 1. readValue(json字符串数据,Class)
 *
 */
public class JsonDemo2 {

    // json str --->user
    @Test
    public void test01() throws IOException {

        String str ="{\"username\":\"张三\",\"age\":18,\"birthDay\":\"2019-05-10\",\"audit\":true}";
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(str, User.class);
        System.out.println(user);//User{username='张三', age=18, isAudit=true, birthDay=Fri May 10 08:00:00 CST 2019, password='null'}
        // 一定要保证要有空参构造函数
    }


    // json str --->user list
    @Test
    public void test02() throws IOException {

        String str ="[{\"username\":\"张三\",\"age\":18,\"birthDay\":\"2019-05-10\",\"audit\":true},{\"username\":\"张三2\",\"age\":18,\"birthDay\":\"2019-05-10\",\"audit\":true},{\"username\":\"张三3\",\"age\":18,\"birthDay\":\"2019-05-10\",\"audit\":true}]\n";
        ObjectMapper mapper = new ObjectMapper();
        List<User> list = mapper.readValue(str, List.class);
        System.out.println(list);
        //[{username=张三, age=18, birthDay=2019-05-10, audit=true}, {username=张三2, age=18, birthDay=2019-05-10, audit=true}, {username=张三3, age=18, birthDay=2019-05-10, audit=true}]
        // 一定要保证要有空参构造函数
    }

    // json str --->map
    @Test
    public void test03() throws IOException {

        String str ="{\"gender\":\"男\",\"age\":22,\"username\":\"李四\"}";
        ObjectMapper mapper = new ObjectMapper();
        HashMap map = mapper.readValue(str, HashMap.class);
        System.out.println(map);
        //{gender=男, age=22, username=李四}
        // 一定要保证要有空参构造函数
    }
}
