package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlManager{


    private static final String dbPro = "jdbc:postgresql://";
    private static final String host = "127.0.0.1";
    private static final String port = "5432";
    private static final String dbName = "webgis";
    private static final String charset = "?useUnicode=true&charactsetEncoding=utf-8";

    private static final String url = dbPro + host + ":" + port + "/" + dbName
            + charset;
    private static final String user = "zhuzhuxia";
    private static final String password = "";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, user, password);
    }
}
