package kr.ac.jejunu;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {

    Connection getConnection() throws ClassNotFoundException, SQLException;
//    {
//        //mysql 연결
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        return DriverManager.getConnection(
//                "jdbc:mysql://localhost/jeju?" +
//                        "characterEncoding=utf-8&serverTimezone=UTC"
//                , "jeju", "jejupw"
//        );
//    }
}