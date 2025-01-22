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
        String nomelongo = "Luuuuuuuuuuuuuuuuuuuuuuuaaaaaaaaaaaaaaaaaaaaaaaaaaaaaannnnnnnnnnnnnnnnnnn";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarNome(nomelongo));
        assertEquals("O nome não pode conter mais do que 50 caracteres ou ser nulo", exception.getMessage());
    }

    @Test
    void testeNomeNulo () {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarNome(null));
        assertEquals("O nome não pode conter mais do que 50 caracteres ou ser nulo", exception.getMessage());
    }

    @Test
    void testeEmailValido () {
        assertDoesNotThrow(() -> ClienteHelper.validarEmail("luanharoldo@gmail.com"));
        assertDoesNotThrow(() -> ClienteHelper.validarEmail("luan_haroldo01@yahoo.com.br"));
    }

    @Test
    void testeEmailInvalidoSemDominio () {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarEmail("luan3123"));
        assertEquals("O email está inválido", exception.getMessage());
    }

    @Test
    void testeEmailInvalidoSemCom () {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarEmail("luanharoldo@gmail"));
        assertEquals("O email está inválido", exception.getMessage());
    }

    @Test
    void testeEmailInvalido () {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarEmail("luanharoldo@.com"));
        assertEquals("O email está inválido", exception.getMessage());
    }

    @Test
    void testeEstadoValido () {
        assertDoesNotThrow(() -> ClienteHelper.validarEstado("SP", clienteController.siglas()));
    }

    @Test
    void testeEstadoInvalidoLetra () {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarEstado("a", clienteController.siglas()));
        assertEquals("O estado digitado não é valido", exception.getMessage());
    }

    @Test
    void testeEstadoInvalido () {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarEstado("HP", clienteController.siglas()));
        assertEquals("O estado digitado não é valido", exception.getMessage());
    }

    @Test
    void testeCidadeValida () {
        assertDoesNotThrow(() -> ClienteHelper.validarCidade("Guarulhos"));
    }

    @Test
    void testeCidadeInvalidaLonga () {
        String cidadelonga = "Ooooooooooooooossssssssssaaaaaaaaaaaasssssssssscccccccccoooooooooooooo";
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarCidade(cidadelonga));
        assertEquals("A cidade não pode conter mais do que 50 caracteres ou ser nula", exception1.getMessage());
    }

    @Test
    void testeCidadeInvalida () {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarCidade("Guaru1hos1234"));
        assertEquals("A cidade deve conter apenas letras", exception.getMessage());
    }

    @Test
    void testeBairroValido () {
        assertDoesNotThrow(() -> ClienteHelper.validarBairro("Tatuapé"));
    }

    @Test
    void testeBairroInvalido () {
        String bairrolongo = "Tatuapeeeeeeeeeeeeeeeeeeeeeeeeeeeeee Longoooooooooooooooooooooo";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarBairro(bairrolongo));
        assertEquals("O bairro não pode conter mais do que 50 caracteres ou ser nulo", exception.getMessage());
    }

    @Test
    void testeLogradouroValido () {
        assertDoesNotThrow(() -> ClienteHelper.validarLogradouro("Avenida"));
    }

    @Test
    void testeLogradouroInvalido () {
        String logradourolongo = "Llllllllllllllllloooooooooooooooooooooooooooooonnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnngggggggggggggggggggggggooooooooooooooooooooooooooooooooooooo";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarLogradouro(logradourolongo));
        assertEquals("O logradouro não pode conter mais do que 100 caracteres ou ser nulo", exception.getMessage());
    }

    @Test
    void testeNumeroValido () {
        assertDoesNotThrow(() -> ClienteHelper.validarNumero("510"));
    }

    @Test
    void testeNumeroInvalidoLongo () {
        String numerolongo = "1223334444";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarNumero(numerolongo));
        assertEquals("O número não pode conter mais do que 6 caracteres ou ser nulo", exception.getMessage());
    }

    @Test
    void testeNumeroInvalido () {
        String numeroLetra = "1L3A";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ClienteHelper.validarNumero(numeroLetra));
        assertEquals("O número do endereço deve conter apenas números", exception.getMessage());
    }
}