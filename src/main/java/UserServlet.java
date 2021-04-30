import javax.servlet.*;
import java.io.IOException;

public class UserServlet extends GenericServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("***************init***************");
    }

    @Override
    public void destroy() {
        System.out.println("***************destroy***************");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("***************service***************");
        StringBuffer response = new StringBuffer();
        response.append("<html>");
        response.append("<Body>");
        response.append("<h1>");
        response.append("hello world");
        response.append("</h1>");
        response.append("</Body>");
        response.append("</html>");
        res.getWriter().println(response.toString());
    }
}
