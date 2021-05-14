import javax.servlet.*;
import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("************* Filter init *************");
    }

    @Override
    public void destroy() {
        System.out.println("************* Filter destroy *************");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("************* Filter before *************");
        chain.doFilter(request,response); //서블릿이 실행되는 위치
        System.out.println("************* Filter after *************");
    }
}
