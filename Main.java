import model.Cliente;
import DAO.ClienteDAO;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente novoCliente = new Cliente();
        Cliente atualizaCliente = new Cliente();

        //criação de clientes
//        novoCliente.setNome("Lucy Fernandes");
//        novoCliente.setEmail("lucyfernandes@outlook.com");
//        novoCliente.setEndereco("Gomes Barreto, 423");
//        clienteDAO.criar(novoCliente);

        //listagem de todos clientes
//       for (Cliente todosCliente : clienteDAO.listarClientes()) {     // todosCliente representará o próximo elemento da lista listarClientes()
//            System.out.println("ID: " + todosCliente.getIdcliente() + " | Nome: " + todosCliente.getNome() + " | Email: " + todosCliente.getEmail() + " | Endereço: " + todosCliente.getEndereco());
//       }

        //atualização de cliente
//        atualizaCliente.setIdcliente(3);
//        atualizaCliente.setNome("Joana Maria");
//        atualizaCliente.setEmail("joanamaria@yahoo.com.br");
//        atualizaCliente.setEndereco("Maré Alta, 444");
//        clienteDAO.atualizar(atualizaCliente);

        //exclusão de cliente
       clienteDAO.deletar(3);
    }
}