package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class ClienteDAO {
    public void criar(Cliente novoCliente) {
        //query
        String query = "INSERT INTO cliente (nome, email, endereco) VALUES ( , , )";
        try (Connection conn = DatabaseCliente.getConnection(); PreparedStatement stmt = conn.prepareStatement(query);)     //prepared p executar a query
        {
            stmt.setString(1, novoCliente.getNome());
            stmt.setString(2, novoCliente.getEmail());
            stmt.setString(3, novoCliente.getEndereco());

            stmt.execute(); //execução da query
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(int id) throws SQLException {
        String query = "DELETE FROM cliente WHERE idcliente = ";
        try (Connection conn = DatabaseCliente.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}