import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserDaoTest {
    public static UserDao userDao;

    //ApplicationContext는 Bean을 다룬다.
    @BeforeAll
    public static void setup() {
//        ClassPathXmlApplicationContext applicationContext
//                = new ClassPathXmlApplicationContext("daoFactory.xml");

//        ApplicationContext applicationContext =
//                new AnnotationConfigApplicationContext("DaoFactory.class");

        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(DaoFactory.class);
        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {

        Integer id = 1;
        String name = "hulk";
        String password = "1234";
        User user = userDao.findById(id);
        System.out.printf("UserInfo: ",user);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        String hulk = "hulk";
        String password = "1234";
        User user  = User.builder().name(hulk).password(password).build();

        userDao.insert(user);

        //insert된 유저의 아이디를 가져옴
        User insertedUser = userDao.findById(user.getId());
        //가져온 아이디가 0보다 큰지에 대해 비교(id는 1부터 시작)
        assertThat(user.getId(), greaterThan(0));

        //해당 정보가 맞는지 확인
        assertThat(insertedUser.getId(), is(user.getId()));
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
    }


    @Test
    public void update() throws SQLException {
        //값을 넣을 이름과 패스워드 셋팅

        String hulk = "hulk";
        String password = "1234";
        User user  = User.builder().name(hulk).password(password).build();
        userDao.insert(user);

        user.setName("kkk");
        user.setPassword("0000");
        userDao.update(user);

        User updateUser = userDao.findById(user.getId());

        assertThat(updateUser.getId(), is(user.getId()));
        assertThat(updateUser.getName(), is(user.getName()));
        assertThat(updateUser.getPassword(), is(user.getPassword()));
    }

    @Test
    public void delete() throws SQLException {
        User user = new User();

        //값을 넣을 이름과 패스워드 셋팅
        String name = "ksb";
        String password = "1111";
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        //insert문에서 auto increment를 해 주고 셋팅을 해 주었다.
        //객체 자체는 call by reference이기 때문에, getInt를 해도 값을 가져올 수 있는 것이다.
        userDao.delete(user.getId());

        User deleteUser = userDao.findById(user.getId());
        assertThat(deleteUser, nullValue());
    }

//
//    @Test
//    public void getHalla() throws SQLException, ClassNotFoundException {
//        UserDao userDao = new UserDao(new kr.ac.jejunu.HallaConnectionMaker());
//        Integer id = 1;
//        String name = "hulk";
//        String password = "1234";
//
//        User user = userDao.findById(id);
//        assertThat(user.getId(), is(id));
//        assertThat(user.getName(), is(name));
//        assertThat(user.getPassword(), is(password));
//    }
//
//    @Test
//    public void insertHalla() throws SQLException, ClassNotFoundException {
//        User user = new User();
//
//        //값을 넣을 이름과 패스워드 셋팅
//        String name = "ksb";
//        String password = "1111";
//        user.setName(name);
//        user.setPassword(password);
//
//        //셋팅한 값을 insert하기 위해 Dao로 넘어감
//        UserDao userDao = new UserDao(new kr.ac.jejunu.HallaConnectionMaker());
//        userDao.insert(user);
//
//        //insert된 유저의 아이디를 가져옴
//        User insertedUser = userDao.findById(user.getId());
//
//        //가져온 아이디가 0보다 큰지에 대해 비교(id는 1부터 시작)
//        assertThat(user.getId(), greaterThan(0));
//
//        //해당 정보가 맞는지 확인
//        assertThat(insertedUser.getId(), is(user.getId()));
//        assertThat(insertedUser.getName(), is(user.getName()));
//        assertThat(insertedUser.getPassword(), is(user.getPassword()));
//    }
}
