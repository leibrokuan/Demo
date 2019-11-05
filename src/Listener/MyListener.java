package Listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MyListener implements HttpSessionListener, ServletContextListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        //获取ServletContext中的count
        ServletContext sc = httpSessionEvent.getSession().getServletContext();
        int count = (int)sc.getAttribute("count")+1;
        sc.setAttribute("count",count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        //获取ServletContext中的count
        ServletContext sc = httpSessionEvent.getSession().getServletContext();
        int count = (int)sc.getAttribute("count")-1;
        //被销毁时人数自减
        sc.setAttribute("count",count);
    }
//application对象初始化
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //获取application
        ServletContext sc = servletContextEvent.getServletContext();

        //存储变量
        sc.setAttribute("count",0);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
