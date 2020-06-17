package com.book.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

	// mysql jdbc url Protocol - jdbc:mysql://IP주소:포트번호/데이터베이스명?characterEncoding=UTF-8
    private static String jdbcURL = "jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9348308?characterEncoding=UTF-8&useSSL=false";
    private static String jdbcUsername = "sql9348308";
    private static String jdbcPassword = "5VHmnrQElp";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
