import models.Cliente;
import models.ClienteDAO;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente novoCliente = new Cliente();
        Cliente atualizar = new Cliente();

        //criação de clientes
//        novoCliente.setNome("Lucy Fernandes");
//        novoCliente.setEmail("lucyfernandes@outlook.com");
//        novoCliente.setEndereco("Gomes Barreto, 423");
//        clienteDAO.criar(novoCliente);

        //listagem de todos clientes
//       for (Cliente todosCliente : clienteDAO.listarClientes()) {
//            System.out.println("ID: " + todosCliente.getIdcliente() + " | Nome: " + todosCliente.getNome() + " | Email: " + todosCliente.getEmail() + " | Endereço: " + todosCliente.getEndereco());
//       }

        //atualização de cliente
        atualizar.setIdcliente(3);
        atualizar.setNome("Maria Lemos Arruda");
        atualizar.setEmail("marialemosarruda@yahoo.com.br");
        atualizar.setEndereco("Ondas Azuis Cristalinas, 946");
        clienteDAO.atualizar(atualizar);

        //exclusão de cliente
//       clienteDAO.deletar(2);
    }
}