import java.sql.*;

public class UserDao {

    private final ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }


    public User findById(Integer id) throws ClassNotFoundException, SQLException {

        //mysql 연결
        Connection connection = connectionMaker.getConnection();

        //쿼리 작성
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from  userinfo where id = ?"
        );
        preparedStatement.setInt(1, id);

        //쿼리 실행
        ResultSet resultSet =preparedStatement.executeQuery();
        resultSet.next();

        //user 정보 셋팅
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        //자원해지
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return user;
    }

    public void insert(User user) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.getConnection();

        //쿼리 작성
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into userinfo(name,password) value(?,?)"
                , Statement.RETURN_GENERATED_KEYS
        );
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());

        //얼마나 update 되었는지 카운트 해서 반환해 줌
        preparedStatement.executeUpdate();

        //auto increment의 값을 빼내옴
        ResultSet resultSet= preparedStatement.getGeneratedKeys();
        resultSet.next();

        //빼내온 값을 셋팅
        user.setId(resultSet.getInt(1));

        //자원해지
        preparedStatement.close();
        connection.close();

    }

}
