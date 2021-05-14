package kr.ac.jejunu;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Component
@RequiredArgsConstructor
public class UserDao {

    private final JdbcTemplate jdbcTemplate;


    //@Autowired
//    public ac.kr.jejunu.UserDao(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    public User findById(Integer id) {
        String sql = "select * from  userinfo where id = ?";
        Object[] params = new Object[]{id};
        User query = jdbcTemplate.query(sql, rs -> {
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }
            return user;
        }, id);
        return query;
    }

    public void insert(User user) {
        String sql = "insert into userinfo(name,password) value(?,?)";
        Object[] params = new Object[]{user.getName(),user.getPassword()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con ->{
            //db마다 auto increament방법이 다르기 때문에 직접 만들어 야 한줘다.
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement;
        }, keyHolder);
        user.setId(keyHolder.getKey().intValue());
    }

    public void update(User user) {
        String sql = "update userinfo set name=?,password=? where id=?";
        Object[] params = new Object[]{user.getName(),user.getPassword(),user.getId()};
        jdbcTemplate.update(sql, params);
    }

    public void delete(Integer id) {
        String sql = "delete from userinfo where id=?";
        Object[] params = new Object[]{id};
        jdbcTemplate.update(sql, params);
    }

}
