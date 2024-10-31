import models.Cliente;
import models.ClienteDAO;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ClienteDAO clienteDAO = new ClienteDAO();

        Cliente novoCliente = new Cliente();
        novoCliente.setNome("Lucy Fernandes");
        novoCliente.setEmail("lucyfernandes@outlook.com");
        novoCliente.setEndereco("Gomes Barreto, 423");
        clienteDAO.criar(novoCliente);

//        for (Cliente todosCliente : clienteDAO.listarClientes()) {
//            System.out.println("Clientes encontrados: " + novoCliente.getNome());
//        }

//        clienteDAO.deletar(1);
    }
}