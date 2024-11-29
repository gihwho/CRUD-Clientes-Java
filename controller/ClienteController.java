package controller;

import DAO.ClienteDAO;
import model.Cliente;

import java.sql.SQLException;
import java.util.List;

//intermediário entre a View e o DAO
public class ClienteController {
    private ClienteDAO clienteDAO;      //escapsulamento, outra classe não acessa os atributos

    //construtor, faz inicialização do clienteDAO
    public ClienteController () {
        this.clienteDAO = new ClienteDAO();
    }

    public void criaNovoCliente (String nome, String email, String endereco) {
        Cliente novoCliente = new Cliente();
        novoCliente.setNome(nome);
        novoCliente.setEmail(email);
        novoCliente.setEndereco(endereco);
        clienteDAO.criar(novoCliente);
    }

    public List<Cliente> listaCliente () throws SQLException {
        return clienteDAO.listarClientes();
    }

    public Cliente listaID (int id) throws SQLException {
        return clienteDAO.listarPorID(id);
    }

    public Cliente atualizaCliente (int id, String nome, String email, String endereco) {
        Cliente atualizaCliente = new Cliente();
        atualizaCliente.setIdcliente(id);
        atualizaCliente.setNome(nome);
        atualizaCliente.setEmail(email);
        atualizaCliente.setEndereco(endereco);
        clienteDAO.atualizar(atualizaCliente);
        return atualizaCliente;
    }

    public void deletaCliente (int id) throws SQLException {
        clienteDAO.deletar(id);
    }
}