package app.connector;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseConnector {
    final static String url = "jdbc:mysql://localhost/test";
    final static String user = "test";
    final static String password = "test";

    public static void connect(String sql) {
        try {
            ResultSet rs = executeSql("show tables");
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        connect(null);
    }

    public static ResultSet executeSql(String sql) throws SQLException {
        Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement pstmt = con.prepareStatement(sql);
        return pstmt.executeQuery();
    }

    public static DatabaseMetaData getMetaData() throws SQLException {
        Connection con = DriverManager.getConnection(url, user, password);
        return con.getMetaData();
    }

}
