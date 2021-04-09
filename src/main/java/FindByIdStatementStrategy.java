import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.IntUnaryOperator;

public class FindByIdStatementStrategy implements StatementStrategy {
    private Integer id;
    public FindByIdStatementStrategy(Integer id) {
        this.id = id;
    }
    @Override
    public PreparedStatement makeStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from  userinfo where id = ?"
        );
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }
}
