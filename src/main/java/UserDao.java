import javax.sql.DataSource;
import java.sql.*;

public class UserDao {

    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User findById(Integer id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;
        User user = null;

        try {
            //mysql 연결
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new FindByIdStatementStrategy();
            preparedStatement = statementStrategy.makeStatement(id, connection);
            //쿼리 실행
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                //user 정보 셋팅
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }

        } finally {

            //자원해지
            try {
                resultSet.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }

        return user;
    }

    public void insert(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();

            StatementStrategy statementStrategy = new InsertStatementStrategy();
            preparedStatement = statementStrategy.makeStatement(user, connection);

            //얼마나 update 되었는지 카운트 해서 반환해 줌
            preparedStatement.executeUpdate();

            //auto increment의 값을 빼내옴
            ResultSet resultSet= preparedStatement.getGeneratedKeys();
            resultSet.next();

            //빼내온 값을 셋팅
            user.setId(resultSet.getInt(1));
        } finally {

            //자원해지
            try {
                preparedStatement.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }


    }

    public void update(User user) throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new UpdateStatementStrategy();
            preparedStatement = statementStrategy.makeStatement(user, connection);

            //얼마나 update 되었는지 카운트 해서 반환해 줌
            preparedStatement.executeUpdate();
        } finally {

            //자원해지
            try {
                preparedStatement.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public void delete(Integer id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            StatementStrategy statementStrategy = new DeleteStatementStrategy();
            preparedStatement = statementStrategy.makeStatement(id, connection);

            preparedStatement.executeUpdate();
        } finally {

            //자원해지
            try {
                preparedStatement.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
