package DAO;

import database.DatabaseCliente;
import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//lida com persistência de dados | acessa e manipula os dados no banco de dados
public class ClienteDAO {
    public void criar(Cliente novoCliente) {
        String query = "INSERT INTO cliente (nome, email, endereco) VALUES (?, ?, ?)";    //query
        try (Connection conn = DatabaseCliente.getConnection(); PreparedStatement stmt = conn.prepareStatement(query);)     //prepared p executar a query
        {
            stmt.setString(1, novoCliente.getNome());                   //set = define valores nos parâmetros da query | get = Recuperam o valor dos atributos do objeto novoCliente para que sejam usados como parâmetros SQL
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
            while (rs.next()) {     //resultset representa um registro retornado do db, rs.next() faz com que seja mostrado o próximo registro
                cliente.add(new Cliente(rs.getInt("idcliente"),rs.getString("nome"), rs.getString("email"), rs.getString("endereco")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente; //retorna a lista
    }

    public Cliente listarPorID (int id) throws SQLException {
        Cliente clienteID = new Cliente();
        String query = "SELECT * FROM cliente WHERE idcliente = ?";
        try (Connection conn = DatabaseCliente.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

                if (rs.next()) {    //se o cliente for encontrado, retorna isso
                    clienteID.setIdcliente(rs.getInt("idcliente"));
                    clienteID.setNome(rs.getString("nome"));
                    clienteID.setEmail(rs.getString("email"));
                    clienteID.setEndereco(rs.getString("endereco"));
                } else {
                    return null;
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clienteID;
    }

    public void atualizar(Cliente atualizaCliente) {
        String query = "UPDATE cliente SET nome = ?, email = ?, endereco = ? WHERE idcliente = ?";
        try (Connection conn = DatabaseCliente.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, atualizaCliente.getNome());
            stmt.setString(2, atualizaCliente.getEmail());
            stmt.setString(3, atualizaCliente.getEndereco());
            stmt.setInt(4, atualizaCliente.getIdcliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(int id) throws SQLException {
        String query = "DELETE FROM cliente WHERE idcliente = ?";
        try (Connection conn = DatabaseCliente.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}