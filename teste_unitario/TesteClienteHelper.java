package teste_unitario;

import controller.ClienteController;
import helper.ClienteHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TesteClienteHelper {
    ClienteController clienteController = new ClienteController();

    @Test
    void testeNomeValido () {
        assertDoesNotThrow(() -> ClienteHelper.validarNome("Luan Haroldo Santana"));
    }

    @Test
    void testeNomeNumeros () {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarNome("Luan321"));
        assertEquals("O nome deve conter apenas letras", exception.getMessage());
    }

    @Test
    void testeNomeExcedeCarac () {
        String nomelongo = "Luuuuuuuuuuaaaaaaaaaaannnnnnnnnnnnn";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarNome(nomelongo));
        assertEquals("O nome excede 50 caracteres", exception.getMessage());
    }

    @Test
    void testeNomeNulo () {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarNome(null));
        assertEquals("O nome não pode ser nulo", exception.getMessage());
    }

    @Test
    void testeEmailValido () {
        assertDoesNotThrow(() -> ClienteHelper.validarEmail("luanharoldo@gmail.com"));
        assertDoesNotThrow(() -> ClienteHelper.validarEmail("luan_haroldo01@yahoo.com.br"));
    }

    @Test
    void testeEmailInvalido () {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarEmail("luan3123"));
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarEmail("luanharoldo@gmail"));
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarEmail("luanharoldo@.com"));
        assertEquals("O email está inválido", exception.getMessage());
        assertEquals("O email está inválido 2", exception1.getMessage());
        assertEquals("O email está inválido 3", exception2.getMessage());
    }

    @Test
    void testeEstadoValido () {
        assertDoesNotThrow(() -> ClienteHelper.validarEstado("SP", clienteController.siglas()));
    }

    @Test
    void testeEstadoInvalido () {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarEstado("a", clienteController.siglas()));
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarEstado("SPP", clienteController.siglas()));
        assertEquals("O estado inserido não é válido", exception.getMessage());
        assertEquals("O estado inserido não é válido", exception1.getMessage());
    }

    @Test
    void testeCidadeValida () {
        assertDoesNotThrow(() -> ClienteHelper.validarCidade("Guarulhos"));
    }

    @Test
    void testeCidadeInvalida () {
        String cidadelonga = "Osascooooooooooooooooooooooooooooooo";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarCidade("1234"));
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarCidade(cidadelonga));
        assertEquals("A cidade deve ter apenas letras", exception.getMessage());
        assertEquals("A cidade não pode ter mais que 50 caracteres", exception1.getMessage());
    }

    @Test
    void testeBairroValido () {
        assertDoesNotThrow(() -> ClienteHelper.validarBairro("Tatuapé"));
    }

    @Test
    void testeBairroInvalido () {
        String bairrolongo = "Tatuapeeeeeeeeee Longoooooooooooooooooooooo";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarBairro(bairrolongo));
        assertEquals("O bairro não pode ter mais que 50 caracteres", exception.getMessage());
    }

    @Test
    void testeLogradouroValido () {
        assertDoesNotThrow(() -> ClienteHelper.validarLogradouro("Avenida"));
    }

    @Test
    void testeLogradouroInvalido () {
        String logradourolongo = "Longoooooooooooooooooooooo";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarBairro(logradourolongo));
        assertEquals("O logradouro não pode ter mais que 50 caracteres", exception.getMessage());
    }

    @Test
    void testeNumeroValido () {
        assertDoesNotThrow(() -> ClienteHelper.validarNumero("510"));
    }

    @Test
    void testeNumeroInvalido () {
        String numerolongo = "1223334444";
        String numeroLetra = "123Aracajú";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarBairro(numerolongo));
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarBairro(numeroLetra));
        assertEquals("O bairro não pode ter mais que 50 caracteres", exception.getMessage());
        assertEquals("O número não pode conter letras", exception1.getMessage());
    }
}