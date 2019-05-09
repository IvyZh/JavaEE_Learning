package com.ivyzh.filter.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        Lenovo lenovo = new Lenovo();
        lenovo.sale(999);
        lenovo.show();

        System.out.println("--------------");
        SaleComputer proxy_lenovo = (SaleComputer) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(), lenovo.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String name = method.getName();
                if(name.equals("sale")){
                   int m = (int) args[0];
                    System.out.println("专车接你");
                    Object obj = method.invoke(lenovo, m-100);
                    System.out.println("免费送货");

                    return obj+"_鼠标垫";
                }else {
                    Object obj = method.invoke(lenovo,args);
                    return obj;
                }


            }
        });


        String sale = proxy_lenovo.sale(999);
        System.out.println(sale);
    }
}
