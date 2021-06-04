package kr.ac.jejunu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

//@Controller("/userServlet")
@WebServlet(urlPatterns = "/user")
public class UserServlet extends GenericServlet {

    @Autowired
    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class); //현재 패키지 없음
        userDao = applicationContext.getBean("userDao",UserDao.class);
        System.out.println("***************init***************");
    }

    @Override
    public void destroy() {
        System.out.println("***************destroy***************");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("***************service***************");
        Integer id = Integer.parseInt(req.getParameter("id"));
        User user = userDao.findById(id).orElse(null);
        res.setContentType("text/html; charset=UTF-8"); //인코딩 문제 해결
        StringBuffer response = new StringBuffer();
        response.append("<html>");
        response.append("<Body>");
        response.append("<h1>");
        response.append(String.format("Hello %s!!!",user.getName()));
        response.append("</h1>");
        response.append("</Body>");
        response.append("</html>");
        res.getWriter().println(response.toString());
    }
}
