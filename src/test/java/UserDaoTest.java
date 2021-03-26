

import org.junit.jupiter.api.Test;

import java.io.DataOutput;
import java.sql.SQLException;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserDaoTest {

    @Test
    public void get() throws SQLException, ClassNotFoundException {

        Integer id = 1;
        String name = "hulk";
        String password = "1234";

        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao =daoFactory.getUserDao();

        User user = userDao.findById(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        User user = new User();

        //값을 넣을 이름과 패스워드 셋팅
        String name = "ksb";
        String password = "1111";
        user.setName(name);
        user.setPassword(password);

        //셋팅한 값을 insert하기 위해 Dao로 넘어감
        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao =daoFactory.getUserDao();
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
//
//    @Test
//    public void getHalla() throws SQLException, ClassNotFoundException {
//        UserDao userDao = new UserDao(new HallaConnectionMaker());
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
//        UserDao userDao = new UserDao(new HallaConnectionMaker());
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
