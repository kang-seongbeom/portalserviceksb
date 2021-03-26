import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HallaUserDao extends UserDao {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        //mysql 연결
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost/jeju?" +
                        "characterEncoding=utf-8&serverTimezone=UTC"
                , "jeju", "jejupw"
        );
    }
}
