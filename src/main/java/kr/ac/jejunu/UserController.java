package kr.ac.jejunu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
@RequiredArgsConstructor
public class UserController {
    public final UserDao userDao;

    @GetMapping("/user")
    public User getUser(@RequestParam("id") Integer id){
        System.out.println("********** User getUser **********");
        return userDao.findById(id);
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
