package core.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/db_livraria";
        String user = "postgres";
        String password = "Abc123456";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, user, password);

        return conn;

    }

}
