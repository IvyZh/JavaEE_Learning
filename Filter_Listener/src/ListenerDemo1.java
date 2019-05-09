import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;


//
//* 概念：web的三大组件之一。
//        * 事件监听机制
//        * 事件	：一件事情，-->点击按钮
//        * 事件源 ：事件发生的地方，-->按钮
//        * 监听器 ：一个对象-->function
//        * 注册监听：将事件、事件源、监听器绑定在一起。 当事件源上发生某个事件后，执行监听器代码
//        * ServletContextListener:监听ServletContext对象的创建和销毁
//        * 方法：
//        * void contextDestroyed(ServletContextEvent sce) ：ServletContext对象被销毁之前会调用该方法
//        * void contextInitialized(ServletContextEvent sce) ：ServletContext对象创建后会调用该方法

@WebListener()
public class ListenerDemo1 implements ServletContextListener{

    // Public constructor is required by servlet spec
    public ListenerDemo1() {
    }


    public void contextInitialized(ServletContextEvent sce) {
        //加载资源文件！
        ServletContext servletContext = sce.getServletContext();
        servletContext.getRealPath("WEB-INF/sensitive.txt");
        System.out.println("contextInitialized......");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed......");

    }


}
