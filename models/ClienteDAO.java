package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Cliente> listarClientes() throws SQLException {
        List<Cliente> cliente = new ArrayList<>();
        String query = "SELECT * FROM cliente";
        try (Connection conn = DatabaseCliente.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) { //rs executa a query e armazena
            while (rs.next()) {
                cliente.add(new Cliente(rs.getInt("id"),rs.getString("nome"), rs.getString("email"), rs.getString("endereco")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente; //retorna a lista
    }

    public void atualizar(Cliente novocliente) {
        String query = "UPDATE cliente SET nome = , email = , endereco = WHERE id = ";
        try (Connection conn = DatabaseCliente.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, novocliente.getNome());
            stmt.setString(2, novocliente.getEmail());
            stmt.setString(3, novocliente.getEndereco());
            stmt.setInt(4, novocliente.getId());
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