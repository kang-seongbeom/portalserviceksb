package kr.ac.jejunu;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class userRestController {

    private final UserDao userDao;

    @GetMapping("/{id}")
    public User get(@PathVariable Integer id){ //@ResponseBody를 선언하면 리졸버를 안타게함. object자체를 반환하게 함
        return userDao.findById(id);
    }

    @PostMapping
    public void save(User user){ //@requestBody는 RestController에 포함되에 있어서 생략 가능
        userDao.insert(user);
    }

}
