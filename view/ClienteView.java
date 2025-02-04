package view;

import controller.ClienteController;
import model.Cliente;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ClienteView {
    private static ClienteController clienteController = new ClienteController();       //única instância, classe centralizadora
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        int op;
        do {
            menu();
            op = input.nextInt();
            input.nextLine();

            switch (op) {
                case 1:
                    inserirCliente();
                    break;
                case 2:
                    listarCliente();
                    break;
                case 3:
                    atualizarCliente();
                    break;
                case 4:
                    deletarCliente();
                    break;
                case 5:
                    System.out.println("Encerrando aplicação...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (op != 5);
    }

    public static void menu() {
        System.out.println("=============== MENU ===============");
        System.out.println("1. Inserir um cliente.");
        System.out.println("2. Listar um cliente.");
        System.out.println("3. Atualizar um cliente.");
        System.out.println("4. Deletar um cliente.");
        System.out.println("5. Sair.");
    }

    public static void inserirCliente() {
        System.out.println("Digite o nome do cliente: ");
        String nome = input.nextLine();
        System.out.println("Digite o email do cliente: ");
        String email = input.nextLine();
        System.out.println("Digite o estado em sigla (deve estar entre as opções listadas): ");
        clienteController.exibirSiglas();
        String estado = input.nextLine().toUpperCase();
        System.out.println("Digite a cidade: ");
        String cidade = input.nextLine();
        System.out.println("Digite o bairro: ");
        String bairro = input.nextLine();
        System.out.println("Digite o logradouro: ");
        String logradouro = input.nextLine();
        System.out.println("Digite o número: ");
        String numero = input.nextLine();

        clienteController.criaNovoCliente(nome, email, estado, cidade, bairro, logradouro, numero);
    }

    public static void listarCliente() {
        int opc;
        do {
            System.out.println("1. Listar todos clientes.");
            System.out.println("2. Listar cliente por ID.");
            System.out.println("3. Voltar ao menu.");
            opc = input.nextInt();
            input.nextLine();

            switch (opc) {
                case 1:
                    listarTodos();
                    break;
                case 2:
                    listarID();
                    break;
                case 3:
                    System.out.println("Voltando para o menu...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opc != 3);
    }

    public static void listarTodos() {
        try {
            List<Cliente> clientes = clienteController.listaCliente();
            for (Cliente cliente : clientes) {
                System.out.println("ID: " + cliente.getIdcliente() + " | Nome: " + cliente.getNome() + " | Email: " + cliente.getEmail() + " | Endereço -> " + cliente.getEndereco());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes" + e.getMessage());
        }
    }

    public static void listarID () {
        System.out.println("Digite o ID que gostaria de listar: ");
        int id = input.nextInt();
        input.nextLine();

        try {
            Cliente cliente = clienteController.listaID(id);
            if (cliente != null) {
                System.out.println("Cliente encontrado!");
                System.out.println("ID: " + cliente.getIdcliente() + " | Nome: " + cliente.getNome() + " | Email: " + cliente.getEmail() + " | Endereço: " + cliente.getEndereco());
            } else {
                System.out.println("Cliente não encontrado!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void atualizarCliente () throws SQLException {
        System.out.println("Digite o ID do cliente que gostaria de atualizar: ");
        int id = input.nextInt();
        input.nextLine();

        try {
            Cliente clienteExiste = clienteController.listaID(id);
            if (clienteExiste == null) {
                System.out.println("Cliente ID " + id + " não existe!");
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("Digite o novo nome: ");
        String nome = input.nextLine();
        System.out.println("Digite o novo email: ");
        String email = input.nextLine();
        System.out.println("Digite o novo estado em sigla (deve estar entre as opções listadas): \n");
        clienteController.exibirSiglas();
        String estado = input.nextLine().toUpperCase();
        System.out.println("Digite a nova cidade: ");
        String cidade = input.nextLine();
        System.out.println("Digite o novo bairro: ");
        String bairro = input.nextLine();
        System.out.println("Digite o novo logradouro: ");
        String logradouro = input.nextLine();
        System.out.println("Digite o novo número: ");
        String numero = input.nextLine();

        clienteController.atualizaCliente(id, nome, email, estado, cidade, bairro, logradouro, numero);
        System.out.println("Cliente atualizado com sucesso!");
    }

    public static void deletarCliente () throws SQLException {
        System.out.println("Digite o ID do cliente que deseja excluir: ");
        int id = input.nextInt();
        input.nextLine();

        try {
            Cliente clienteExiste = clienteController.listaID(id);
            if (clienteExiste == null) {
                System.out.println("Cliente ID " + id + " não existe!");
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        clienteController.deletaCliente(id);
        System.out.println("Cliente excluído com sucesso!");
    }
}