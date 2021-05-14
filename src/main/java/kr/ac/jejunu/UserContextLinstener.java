package kr.ac.jejunu;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class UserContextLinstener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.printf("*************** context init ***************");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.printf("*************** context destroy ***************");
    }
}
