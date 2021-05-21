package kr.ac.jejunu;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class SimpleController implements Controller {

    private final UserDao userDao;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = userDao.findById(Integer.valueOf(request.getParameter("id")));
        ModelAndView modelAndView = new ModelAndView("user"); //SimpleUrlHandlerMapping의경우 파라미터에 bean을 넣어줘야함(잘안씀)
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
