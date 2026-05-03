package com.backend.db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;

public class SchemaRunner {
    public static void main(String[] args) {
        try (Connection c = getAdminConnection()) {
            runSqlFile(c, "BackEnd/sql/schema.sql");
            try (Connection c2 = MySQLConnection.getConnection()) {
                runSqlFile(c2, "BackEnd/sql/seed.sql");
            }
            System.out.println("Schema and seed executed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static Connection getAdminConnection() throws Exception {
        String host = System.getenv("MYSQL_HOST");
        String port = System.getenv("MYSQL_PORT");
        String user = System.getenv("MYSQL_USER");
        String pass = System.getenv("MYSQL_PASS");
        java.util.Properties p = new java.util.Properties();
        try (java.io.InputStream is = new java.io.FileInputStream("BackEnd/config.properties")) {
            p.load(is);
            if (host == null || host.isEmpty()) host = p.getProperty("MYSQL_HOST");
            if (port == null || port.isEmpty()) port = p.getProperty("MYSQL_PORT");
            if (user == null || user.isEmpty()) user = p.getProperty("MYSQL_USER");
            if (pass == null || pass.isEmpty()) pass = p.getProperty("MYSQL_PASS");
        } catch (Exception ignored) {}
        if (host == null || host.isEmpty()) host = "localhost";
        if (port == null || port.isEmpty()) port = "3306";
        if (user == null) user = "root";
        if (pass == null) pass = "";
        String url = String.format("jdbc:mysql://%s:%s/?useSSL=false&serverTimezone=UTC", host, port);
        return java.sql.DriverManager.getConnection(url, user, pass);
    }

    private static void runSqlFile(Connection c, String path) throws Exception {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        String[] statements = sb.toString().split(";\\s*\\n");
        try (Statement st = c.createStatement()) {
            for (String s : statements) {
                String sql = s.trim();
                if (sql.isEmpty()) continue;
                st.execute(sql);
            }
        }
    }
}
