package app.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.config.Config;

public class DataBaseConnector {
    static Config conf = Config.getInstance();

    public static ResultSet executeSql(String sql) throws SQLException {
        Connection con = DriverManager.getConnection(conf.getUrl(), conf.getUser(), conf.getPassword());
        PreparedStatement pstmt = con.prepareStatement(sql);
        return pstmt.executeQuery();
    }

}
