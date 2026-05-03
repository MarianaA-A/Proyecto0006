package com.backend.db;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnection {
    private static Connection conn;

    public static synchronized Connection getConnection() throws SQLException {
        try {
            if (conn == null || conn.isClosed()) {
                String host = System.getenv("MYSQL_HOST");
                String port = System.getenv("MYSQL_PORT");
                String db = System.getenv("MYSQL_DB");
                String user = System.getenv("MYSQL_USER");
                String pass = System.getenv("MYSQL_PASS");

                // fallback to BackEnd/config.properties if present
                try (InputStream is = new FileInputStream("BackEnd/config.properties")) {
                    Properties p = new Properties();
                    p.load(is);
                    if ((host == null || host.isEmpty()) && p.getProperty("MYSQL_HOST") != null) host = p.getProperty("MYSQL_HOST");
                    if ((port == null || port.isEmpty()) && p.getProperty("MYSQL_PORT") != null) port = p.getProperty("MYSQL_PORT");
                    if ((db == null || db.isEmpty()) && p.getProperty("MYSQL_DB") != null) db = p.getProperty("MYSQL_DB");
                    if ((user == null || user.isEmpty()) && p.getProperty("MYSQL_USER") != null) user = p.getProperty("MYSQL_USER");
                    if ((pass == null || pass.isEmpty()) && p.getProperty("MYSQL_PASS") != null) pass = p.getProperty("MYSQL_PASS");
                } catch (Exception ignored) {}

                if (host == null || host.isEmpty()) host = "localhost";
                if (port == null || port.isEmpty()) port = "3306";
                if (db == null || db.isEmpty()) db = "proyecto0006";
                if (user == null) user = "root";
                if (pass == null) pass = "";

                String url = String.format("jdbc:mysql://%s:%s/%s?useSSL=false&serverTimezone=UTC", host, port, db);
                conn = DriverManager.getConnection(url, user, pass);
            }
            return conn;
        } catch (SQLException e) {
            throw e;
        }
    }
}
