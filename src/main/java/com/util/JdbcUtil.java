package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class JdbcUtil {
    private static Properties p = new Properties();

    private static InputStream in = null;

    private static Properties loaderProper() {
        in = JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            p.load(in);
            System.out.println("----->" + p.size());
            return p;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private static String driver = null;

    private static String url = null;

    private static String username = null;

    private static String password = null;
    static {
        try {
            loaderProper();
            driver = p.getProperty("jdbc.DriverClassName");
            url = p.getProperty("jdbc.url");
            username = p.getProperty("jdbc.username");
            password = p.getProperty("jdbc.password");
            System.out.println("driver-------->" + driver);
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Connection getConnection(String sql) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    public ResultSet selectList(String sql, Object[] ob) {
        Connection conn = getConnection(sql);
        PreparedStatement st = null;
        ResultSet rs=null;
        try {
            st = conn.prepareStatement(sql);
            if (ob != null) {
                for (int i = 0; i < ob.length; i++) {
                    st.setString(i + 1, ob[i].toString());
                }
            }
            rs=st.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rs;
    }
}
