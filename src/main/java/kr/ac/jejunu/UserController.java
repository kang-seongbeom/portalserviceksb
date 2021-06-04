package kr.ac.jejunu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class UserController {
    public final UserDao userDao;

    //검색과 같은 것은 restful(path에 의미가 있는 /id=1)한 @pathvatiable을 사용하지않고
    //@RequestPram(?id=1)을 사용한다.
    @GetMapping(value = "/user/{id}", produces = "application/json")
    public User getUser(@PathVariable("id") Integer id, HttpSession httpSession){
        System.out.println("********** User getUser **********");
        User user = userDao.findById(id).orElse(null);
        httpSession.setAttribute("user", user);
        return user;
    }
//    @GetMapping(value = "/user", produces = "application/json")
//    public void getUser(User user){ //파라미터에 @ModelAttribute이 생략되어 있음. ModelAttribute는 request와 response를 둘다 가능
//        User u = userDao.findById(user.getId());
//        user.setPassword(u.getPassword());
//    }

    @GetMapping(value = "/user/session", produces = "application/json")
    public User sessionUser(HttpSession httpSession){
        return (User)httpSession.getAttribute("user");
    }

    @GetMapping("upload") // @GetMapping("upload", params="id=1") -> id가 1인 것만 controller 작동
    public void upload(){

    }

    //업로드한 파일 저장
    //MultipartFileResolver는 binary한 파일을 MultipartFile로 변환시켜 준다
    @PostMapping("upload")
    public ModelAndView upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String path = request.getServletContext().getRealPath("/")
                + "/WEB-INF/static/" + file.getOriginalFilename();
        File saveFile = new File(path);
        FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write(file.getBytes());
        bufferedOutputStream.close();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url", "/images/" + file.getOriginalFilename());
        System.out.printf(path);
        return modelAndView;

    }

    @ExceptionHandler(Exception.class)
    public ModelAndView error(Exception e){
        ModelAndView modelAndView = new ModelAndView("error"); //error.jsp반환
        modelAndView.addObject("e", e);
        return modelAndView;
    }

}
