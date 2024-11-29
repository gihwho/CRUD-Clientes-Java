//package teste_unitario;
//
//import DAO.ClienteDAO;
//import database.DatabaseCliente;
//import model.Cliente;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TesteClienteDAO {
//    private ClienteDAO clienteDAO;          //clienteDAO é uma instancia da ClienteDAO para que se possar obter os métodos de teste para cada operacao CRUD
//
//    @BeforeEach                             //indica que este metodo deve ser executado sempre antes de cada teste
//    public void setup () {                  //inicializa o objeto clienteDAO
//        clienteDAO = new ClienteDAO();      //cria um novo objeto ClienteDAO
//    }
//
//    @Test
//    public void testeCriarCliente () throws SQLException {
//        TesteCliente testeCliente = new TesteCliente(1, "Fernando Matias", "fernando@gmail.com", "Piscinas Azuis, 853");
//        clienteDAO.criar(testeCliente);
//
//        List<Cliente> testeClientes = clienteDAO.listarClientes();
//        boolean achado = testeClientes.stream().anyMatch(cliente -> cliente.getNome().equals("Fernando Matias") &&
//                cliente.getEmail().equals("fernando@gmail.com") &&
//                cliente.getEndereco().equals("Piscinas Azuis, 853")
//        );
//        assertTrue(achado, "Cliente deve ser criado e listado com sucesso");
//    }
//
//    @Test
//    public void testeListarClientes () throws SQLException {
//        TesteCliente cliente1 = new TesteCliente(2, "Jarvas", "jarvas@gmail.com", "Flores Amarelas, 213");
//        TesteCliente cliente2 = new TesteCliente(3, "Helena", "helena@outlook.com", "Lampada Amarelis");
//
//        clienteDAO.criar(cliente1);
//        clienteDAO.criar(cliente2);
//
//        List<Cliente> clientes = clienteDAO.listarClientes();
//
//        assertEquals(2, clientes.size(), "Todos os clientes devem ser listados");
//    }
//
//    @Test
//    public void testeAtualizarCliente () throws SQLException {
//        TesteCliente cliente1 = new TesteCliente(3, "Agenlina", "angelina@yahoo.com.br", "Lampada Fria, 87");
//        clienteDAO.criar(cliente1);
//
//        TesteCliente testeAtualizadoCliente = new TesteCliente(3, "Angelina Atualizada", "angelina.att@yahoo.com.br", "ATUALIZADA Lampada Fria, 87");
//        clienteDAO.atualizar(testeAtualizadoCliente);
//
//        List<Cliente> attcliente = clienteDAO.listarClientes();
//        boolean atualizado = attcliente.stream().anyMatch(c ->
//                c.getIdcliente() == 3 &&
//                c.getNome().equals("Angelina Atualizada") &&
//                c.getEmail().equals("angelina.att@yahoo.com.br") &&
//                c.getEndereco().equals("ATUALIZADA Lampada Fria, 87")
//        );
//    }
//
//    @Test
//    public void testeDeletarCliente () throws SQLException {
//        TesteCliente cliente = new TesteCliente(3, "Angelina Atualizada", "angelina.att@yahoo.com.br", "ATUALIZADA Lampada Fria, 87");
//        clienteDAO.criar(cliente);
//
//        clienteDAO.deletar(3);
//
//        List<Cliente> clientes = clienteDAO.listarClientes();
//        boolean achado = clientes.stream().anyMatch(c -> c.getIdcliente() == 5);
//
//        assertFalse(achado, "Cliente deve ser deletado com sucesso");
//    }
//
//    @AfterEach
//    public void tearDown () throws SQLException {
//        try (Connection conn = TesteDatabaseCliente.getConnection(); Statement stmt = conn.createStatement()) {
//            stmt.execute("DELETE FROM cliente");
//        }
//    }
//}
