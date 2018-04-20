package tableDefinition;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionToDb {

    public  Connection getConnection() throws SQLException {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setServerName("localhost");
        ds.setPortNumber(3305);
        ds.setDatabaseName("my_new_schema");
        ds.setUser("root");
        ds.setPassword("123456");

        Connection conn = null;
         conn = ds.getConnection();
            return conn;



    }
}
