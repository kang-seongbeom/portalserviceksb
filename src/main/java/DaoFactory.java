import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//spring에서 bean을 등록하기 위해 필요한 어노테이션
@Configuration
public class DaoFactory {

    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    @Bean
    public JejuConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }
}
