package com.backend.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {
    private static Connection conn;
    private static boolean initialized = false;

    static {
        try {
            String h2Url = "jdbc:h2:mem:proyecto0006;DB_CLOSE_DELAY=-1";
            conn = DriverManager.getConnection(h2Url, "sa", "");
            initializeDatabase();
            initialized = true;
        } catch (SQLException e) {
            System.err.println("Failed to initialize database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static synchronized Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            String h2Url = "jdbc:h2:mem:proyecto0006;DB_CLOSE_DELAY=-1";
            conn = DriverManager.getConnection(h2Url, "sa", "");
            if (!initialized) {
                initializeDatabase();
                initialized = true;
            }
        }
        return conn;
    }

    private static void initializeDatabase() throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS docentes (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "codigoInstitucion VARCHAR(50)," +
                "nombreIES VARCHAR(255)," +
                "generoDocente VARCHAR(50)," +
                "tipoDocumento VARCHAR(50)," +
                "nivelFormacion VARCHAR(100)," +
                "tiempoDedicacion VARCHAR(100)," +
                "tipoContrato VARCHAR(100)," +
                "departamento VARCHAR(100)," +
                "municipio VARCHAR(100)," +
                "conteo2013 INT)");

            stmt.executeUpdate("INSERT INTO docentes (codigoInstitucion, nombreIES, generoDocente, tipoDocumento, nivelFormacion, tiempoDedicacion, tipoContrato, departamento, municipio, conteo2013) VALUES " +
                "('1101','UNIVERSIDAD NACIONAL DE COLOMBIA','FEMENINO','EXTRANJERO','DOCTORADO','CATEDRA','TERMINO INDEFINIDO','BOGOTA D.C.','BOGOTA D.C.',2)," +
                "('1101','UNIVERSIDAD NACIONAL DE COLOMBIA','FEMENINO','NACIONAL','TECNICO PROFESIONAL','CATEDRA','TERMINO FIJO','BOGOTA D.C.','BOGOTA D.C.',0)," +
                "('1101','UNIVERSIDAD NACIONAL DE COLOMBIA','MASCULINO','EXTRANJERO','DOCTORADO','CATEDRA','TERMINO FIJO','BOGOTA D.C.','BOGOTA D.C.',3)," +
                "('1101','UNIVERSIDAD NACIONAL DE COLOMBIA','MASCULINO','NACIONAL','TECNICO PROFESIONAL','CATEDRA','TERMINO FIJO','BOGOTA D.C.','BOGOTA D.C.',0)," +
                "('1102','UNIVERSIDAD NACIONAL DE COLOMBIA','FEMENINO','EXTRANJERO','DOCTORADO','TIEMPO COMPLETO','TERMINO INDEFINIDO','ANTIOQUIA','MEDELLIN',6)");
        } catch (SQLException e) {
            System.err.println("Error in initializeDatabase: " + e.getMessage());
        }
    }
}
