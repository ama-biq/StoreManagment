package tableDefinition;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionToDb {

    public  MysqlDataSource getDataSource(){
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName("localhost");
        ds.setPortNumber(3305);
        ds.setDatabaseName("my_new_schema");
        ds.setUser("root");
        ds.setPassword("123456");
        return ds;
    }
    public  Connection getNewConnection(String str) throws SQLException {

        Connection conn = getDataSource().getConnection();
        return conn;
//        Statement stmt = conn.createStatement();
//        ResultSet rs = stmt.executeQuery("SELECT version()");
//        if (rs.next()) {
//            System.out.println("Database Version : " + rs.getString(1));
//        }

    }
}
