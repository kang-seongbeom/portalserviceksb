package kr.ac.jejunu;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class UserRequestLinstener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.printf("*************** request destroy ***************");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.printf("*************** request init ***************");
    }

}
