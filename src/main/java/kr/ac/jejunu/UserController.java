package kr.ac.jejunu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    public final UserDao userDao;

    @RequestMapping("/user")
    public User getUser(@RequestParam("id") Integer id){
        return userDao.findById(id);
    }

    @RequestMapping(path="upload", method = RequestMethod.GET)
    public void upload(){

    }

    //업로드한 파일 저장
    //MultipartFileResolver는 binary한 파일을 MultipartFile로 변환시켜 준다
    @RequestMapping(path="upload", method = RequestMethod.POST)
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
