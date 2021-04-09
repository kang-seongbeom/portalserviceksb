import javax.sql.DataSource;
import java.sql.*;

public class UserDao {


    private final JdbcContext jdbcContext;

    public UserDao (JdbcContext jdbcContext){
        this.jdbcContext = jdbcContext;
    }

    public User findById(Integer id) throws SQLException {
        StatementStrategy statementStrategy = new FindByIdStatementStrategy(id);
        return jdbcContext.jdbcContextForFindById(statementStrategy);
    }
    public void insert(User user) throws SQLException {
        StatementStrategy statementStrategy = new InsertStatementStrategy(user);
        jdbcContext.jdbcContextForInsert(user, statementStrategy);
    }
    public void update(User user) throws SQLException{
        StatementStrategy statementStrategy = new UpdateStatementStrategy(user);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }
    public void delete(Integer id) throws SQLException {
        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    private User jdbcContextForFindById(StatementStrategy statementStrategy) throws SQLException {

        return jdbcContext.jdbcContextForFindById(statementStrategy);
    }


    private void jdbcContextForInsert(User user, StatementStrategy statementStrategy) throws SQLException {
        jdbcContext.jdbcContextForInsert(user, statementStrategy);
    }



    private void jdbcContextForUpdate(StatementStrategy statementStrategy) throws SQLException {
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }
}
