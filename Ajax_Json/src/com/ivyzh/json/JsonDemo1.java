package com.ivyzh.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivyzh.domain.User;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * java对象转json
 *2. Java对象转换JSON
 * 	1. 使用步骤：
 * 		1. 导入jackson的相关jar包
 * 		2. 创建Jackson核心对象 ObjectMapper
 * 		3. 调用ObjectMapper的相关方法进行转换
 * 			1. 转换方法：
 * 				* writeValue(参数1，obj):
 *                   	 参数1：
 *                          File：将obj对象转换为JSON字符串，并保存到指定的文件中
 *                          Writer：将obj对象转换为JSON字符串，并将json数据填充到字符输出流中
 *                          OutputStream：将obj对象转换为JSON字符串，并将json数据填充到字节输出流中
 *               * writeValueAsString(obj):将对象转为json字符串
 * 			2. 注解：
 * 				1. @JsonIgnore：排除属性。
 * 				2. @JsonFormat：属性值得格式化
 * 					* @JsonFormat(pattern = "yyyy-MM-dd")
 * 			3. 复杂java对象转换
 * 				1. List：数组
 * 				2. Map：对象格式一致
 *
 */
public class JsonDemo1 {

    // user ---> json str
    @Test
    public void test01() throws IOException {
        User user = new User("张三", 18, true, new Date(), "123");
        //创建Jackson核心对象 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();

        //mapper.writeValue(new File("user.txt"),user);//写入到文件
        // 将数据关联到Writer中
        //mapper.writeValue(new FileWriter("user_b.txt"),user);

        //FileOutputStream fos = new FileOutputStream(new File("user_c.txt"));
       // mapper.writeValue(fos,user);//并将json数据填充到字节输出流中
        String s = mapper.writeValueAsString(user);
        System.out.println(s);

    }


    // user ---> json str
//    1. @JsonIgnore：排除属性。
//            2. @JsonFormat：属性值得格式化
//		* @JsonFormat(pattern = "yyyy-MM-dd")
    @Test
    public void test02() throws IOException {
        User user = new User("张三", 18, true, new Date(), "123");
        //创建Jackson核心对象 ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        //mapper.writeValue(new File("user.txt"),user);//写入到文件,@JsonIgnore：排除属性 password
        String s = mapper.writeValueAsString(user);
        System.out.println(s);

    }


    // list ---> json
    @Test
    public void test03() throws IOException {
        User user = new User("张三", 18, true, new Date(), "123");
        User user2 = new User("张三2", 18, true, new Date(), "123");
        User user3 = new User("张三3", 18, true, new Date(), "123");

        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);
        users.add(user3);
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(users);
        System.out.println(s);

    }

    // map ---> json
    @Test
    public void test04() throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("username","李四");
        map.put("age",22);
        map.put("gender","男");

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(map);
        System.out.println(s);

    }
}
