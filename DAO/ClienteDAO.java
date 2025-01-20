package DAO;

import database.DatabaseCliente;
import model.Cliente;
import model.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//lida com persistência de dados | acessa e manipula os dados no banco de dados
public class ClienteDAO {
    public void criar(Cliente novoCliente) {
        String queryCliente = "INSERT INTO cliente (nome, email, idendereco) VALUES (?, ?, ?)";
        String queryEndereco = "INSERT INTO endereco (estado, cidade, bairro, logradouro, numero) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseCliente.getConnection()) {
            conn.setAutoCommit(false); // Inicia uma transação

            try (PreparedStatement stmtEndereco = conn.prepareStatement(queryEndereco, Statement.RETURN_GENERATED_KEYS)) {
                Endereco endereco = novoCliente.getEndereco(); // Obter o objeto endereço do cliente
                stmtEndereco.setString(1, endereco.getEstado());
                stmtEndereco.setString(2, endereco.getCidade());
                stmtEndereco.setString(3, endereco.getBairro());
                stmtEndereco.setString(4, endereco.getLogradouro());
                stmtEndereco.setString(5, endereco.getNumero());
                stmtEndereco.executeUpdate();

                // Recuperar o ID gerado para o endereço
                try (ResultSet rs = stmtEndereco.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idEndereco = rs.getInt(1);

                        // Inserir o cliente
                        try (PreparedStatement stmtCliente = conn.prepareStatement(queryCliente)) {
                            stmtCliente.setString(1, novoCliente.getNome());
                            stmtCliente.setString(2, novoCliente.getEmail());
                            stmtCliente.setInt(3, idEndereco); // Associar o ID do endereço ao cliente
                            stmtCliente.executeUpdate();
                        }
                    }
                }
            }
            // Confirmar a transação
            conn.commit();
        } catch (SQLException e) {
            try {
                // Reverter transação em caso de falha
                DatabaseCliente.getConnection().rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> listarClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT c.idcliente, c.nome, c.email, c.idendereco, e.estado, e.cidade, e.bairro, e.logradouro, e.numero FROM cliente c " +
                "JOIN endereco e ON c.idendereco = e.idendereco";

        try (Connection conn = DatabaseCliente.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) { // Executa a query e obtém os resultados
            while (rs.next()) { // Itera pelos registros retornados
                // Cria o objeto Endereco e Cliente
                Endereco endereco = new Endereco(rs.getInt("idendereco"), rs.getString("estado"), rs.getString("cidade"), rs.getString("bairro"), rs.getString("logradouro"), rs.getString("numero"));
                Cliente cliente = new Cliente(rs.getInt("idcliente"), rs.getString("nome"), rs.getString("email"), endereco); // Objeto Endereco associado
                clientes.add(cliente); // Adiciona o cliente à lista
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes: " + e.getMessage(), e);
        }
        return clientes;
    }

    public Cliente listarPorID(int id) throws SQLException {
        Cliente cliente = null;
        String query = "SELECT c.idcliente, c.nome, c.email, c.idendereco, e.estado, e.cidade, e.bairro, e.logradouro, e.numero " +
                "FROM cliente c " +
                "JOIN endereco e ON c.idendereco = e.idendereco " +
                "WHERE c.idcliente = ?";

        try (Connection conn = DatabaseCliente.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Endereco endereco = new Endereco(
                        rs.getInt("idendereco"),
                        rs.getString("estado"),
                        rs.getString("cidade"),
                        rs.getString("bairro"),
                        rs.getString("logradouro"),
                        rs.getString("numero")
                );
                cliente = new Cliente(
                        rs.getInt("idcliente"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        endereco
                );
            } else {
                return null; // Cliente não encontrado
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar cliente por ID: " + e.getMessage(), e);
        }

        return cliente; // Retorna o cliente encontrado ou null
    }

    public void atualizar(Cliente atualizaCliente) throws SQLException {
        String queryCliente = "UPDATE cliente SET nome = ?, email = ? WHERE idcliente = ?";
        String queryEndereco = "UPDATE endereco SET estado = ?, cidade = ?, bairro = ?, logradouro = ?, numero = ?";
        Connection conn = null;

        try {
            conn = DatabaseCliente.getConnection();
            conn.setAutoCommit(false); // Inicia uma transação

            // Atualiza o endereço
            try (PreparedStatement stmtEndereco = conn.prepareStatement(queryEndereco)) {
                Endereco endereco = atualizaCliente.getEndereco(); // Obter o objeto endereço do cliente
                stmtEndereco.setString(1, endereco.getEstado());
                stmtEndereco.setString(2, endereco.getCidade());
                stmtEndereco.setString(3, endereco.getBairro());
                stmtEndereco.setString(4, endereco.getLogradouro());
                stmtEndereco.setString(5, endereco.getNumero());
                stmtEndereco.executeUpdate();
            }

            // Atualiza o cliente
            try (PreparedStatement stmtCliente = conn.prepareStatement(queryCliente)) {
                stmtCliente.setString(1, atualizaCliente.getNome());
                stmtCliente.setString(2, atualizaCliente.getEmail());
                stmtCliente.setInt(3, atualizaCliente.getIdcliente());
                stmtCliente.executeUpdate();
            }

            // Confirma a transação
            conn.commit();
        } catch (SQLException e) {
            try {
                // Reverte a transação em caso de erro
                if (conn != null) conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            throw new RuntimeException("Erro ao atualizar cliente e endereço: " + e.getMessage(), e);
        } finally {
            // Fecha a conexão para evitar vazamentos
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
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