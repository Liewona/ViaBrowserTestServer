package com.liuono.browser.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBHelper {

    public static String DBManagerUrl = "jdbc:mysql://localhost:3306/";
    public static String DBManagerUserName = "root";
    public static String DBManagerUserPassword = "root";

    private static Connection staticConnection = null;

    public static void setDBConnection(String DBName) {
        if(staticConnection == null) {
            staticConnection = connectDB(DBName);
        }
    }

    public static void setDBConnection(Connection connection) {
        staticConnection = connection;
    }

    public static Connection connectDB(String DBName) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DBManagerUrl + DBName + "?serverTimezone=GMT%2B8", DBManagerUserName, DBManagerUserPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            if(connection != null && ! connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void clearConnection() {
        try {
            if(staticConnection != null && ! staticConnection.isClosed()) {
                staticConnection.close();
                staticConnection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if(rs != null && ! rs.isClosed()) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet querySQL(Connection conn, String sql) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet querySQL(String DBName, String sql) {
        setDBConnection(DBName);
        return querySQL(staticConnection, sql);
    }
}
