package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private DBUtil(){

    }
    public static final String url = "jdbc:postgresql://localhost:5432/online_store";
    public static final String userName = "postgres";
    public static final String userPassword = "postgres";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,userName,userPassword);
    }


}
