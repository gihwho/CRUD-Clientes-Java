package teste_unitario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteDatabaseCliente {
    private static final String URL = "jdbc:mysql://localhost:3306/teste_db"; //banco criado
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}