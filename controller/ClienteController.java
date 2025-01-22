package controller;

import DAO.ClienteDAO;
import helper.ClienteHelper;
import model.Cliente;
import model.Endereco;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

//intermediário entre a View e o DAO
public class ClienteController {
    private ClienteDAO clienteDAO = new ClienteDAO();      //escapsulamento, outra classe não acessa os atributos
    private ClienteHelper clienteHelper;

    public ClienteController(ClienteDAO clienteDAO, ClienteHelper clienteHelper) {
        this.clienteDAO = clienteDAO;
        this.clienteHelper = clienteHelper;
    }

    public ClienteController() {}

    public void criaNovoCliente (String nome, String email, String estado, String cidade, String bairro, String logradouro, String numero) {
        Cliente novoCliente = new Cliente();
        Endereco novoEndereco = new Endereco();

        ClienteHelper.validarNome(nome);
        ClienteHelper.validarEmail(email);
        ClienteHelper.validarEstado(estado, siglas());
        ClienteHelper.validarCidade(cidade);
        ClienteHelper.validarBairro(bairro);
        ClienteHelper.validarLogradouro(logradouro);
        ClienteHelper.validarNumero(numero);

        novoEndereco.setEstado(estado);
        novoEndereco.setCidade(cidade);
        novoEndereco.setBairro(bairro);
        novoEndereco.setLogradouro(logradouro);
        novoEndereco.setNumero(numero);

        novoCliente.setNome(nome);
        novoCliente.setEmail(email);
        novoCliente.setEndereco(novoEndereco);
        clienteDAO.criar(novoCliente);
        System.out.println("Cliente criado!");
    }

    public List<Cliente> listaCliente () throws SQLException {
        return clienteDAO.listarClientes();
    }

    public Cliente listaID (int id) throws SQLException {
        return clienteDAO.listarPorID(id);
    }

    public Cliente atualizaCliente (int id, String nome, String email, String estado, String cidade, String bairro, String logradouro, String numero) throws SQLException {
        Cliente atualizaCliente = new Cliente();
        Endereco atualizaEndereco = new Endereco();

        ClienteHelper.validarNome(nome);
        ClienteHelper.validarEmail(email);
        ClienteHelper.validarEstado(estado, siglas());
        ClienteHelper.validarCidade(cidade);
        ClienteHelper.validarBairro(bairro);
        ClienteHelper.validarLogradouro(logradouro);
        ClienteHelper.validarNumero(numero);

        atualizaEndereco.setEstado(estado);
        atualizaEndereco.setCidade(cidade);
        atualizaEndereco.setBairro(bairro);
        atualizaEndereco.setLogradouro(logradouro);
        atualizaEndereco.setNumero(numero);

        atualizaCliente.setIdcliente(id);
        atualizaCliente.setNome(nome);
        atualizaCliente.setEmail(email);
        atualizaCliente.setEndereco(atualizaEndereco);
        clienteDAO.atualizar(atualizaCliente);
        return atualizaCliente;
    }

    public void deletaCliente (int id) throws SQLException {
        clienteDAO.deletar(id);
    }

    public List<String> siglas() {
        List<String> siglas = Arrays.asList(
                "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA",
                "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN",
                "RS", "RO", "RR", "SC", "SP", "SE", "TO"
        );
        return siglas;
    }

    public void exibirSiglas() {
        List<String> sigla = siglas();
        String siglasFormatadas = String.join(" | ", sigla);
        System.out.println(siglasFormatadas);
    }
}