package com.ivyzh.login;

import com.ivyzh.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class BeanUtilsTest {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Map<String, String[]> map = new HashMap<>();
        map.put("username",new String[]{"张三"});
        map.put("password",new String[]{"password"});
        map.put("nickname",new String[]{"zs"});

        User user = new User();
        BeanUtils.populate(user,map);

        System.out.println("u:"+user);
    }
}
