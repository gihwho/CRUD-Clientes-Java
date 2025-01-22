package helper;

import java.util.List;

//classe que realiza validações | static = não depende de estados internos da classe
public class ClienteHelper {
    public static void validarNome (String nome) {
        if (nome == null || nome.length() > 50) {
            throw new IllegalArgumentException("O nome não pode conter mais do que 50 caracteres ou ser nulo");
        }
        String regex = "[a-zA-ZÀ-ÖØ-öø-ÿ\\s]+";
        if (!nome.matches(regex)) {     //regex que aceita apenas letras (acentuadas tbm) e espaço
            throw new IllegalArgumentException("O nome deve conter apenas letras");
        }
    }

    public static void validarEmail (String email) {
        if (email == null || email.length() > 100) {
            throw new IllegalArgumentException("O email não pode conter mais do que 100 caracteres ou ser nulo");
        }
        String regex = "^[\\w.%+-]+@[\\w.-]+\\.(com|com\\.br)$";
        if (!email.matches(regex)) {
            throw new IllegalArgumentException("O email está inválido");
        }
    }

    public static void validarEstado (String estado, List<String> siglas) {
        if (estado == null || estado.length() > 2) {
            throw new IllegalArgumentException("O estado não pode conter mais do que 2 caracteres ou ser nulo");
        }
        if (!siglas.contains(estado)) {
            throw new IllegalArgumentException("O estado digitado não é valido");
        }
    }

    public static void validarCidade (String cidade) {
        if (cidade == null || cidade.length() > 50) {
            throw new IllegalArgumentException("A cidade não pode conter mais do que 50 caracteres ou ser nula");
        }
        String regex = "[a-zA-ZÀ-ÖØ-öø-ÿ\\s]+";
        if (!cidade.matches(regex)) {
            throw new IllegalArgumentException("A cidade deve conter apenas letras");
        }
    }

    public static void validarBairro (String bairro) {
        if (bairro == null || bairro.length() > 50) {
            throw new IllegalArgumentException("O bairro não pode conter mais do que 50 caracteres ou ser nulo");
        }
        String regex = "[a-zA-ZÀ-ÖØ-öø-ÿ\\s]+";
        if (!bairro.matches(regex)) {
            throw new IllegalArgumentException("O bairro deve conter apenas letras");
        }
    }

    public static void validarLogradouro (String logradouro) {
        if (logradouro == null || logradouro.length() > 100) {
            throw new IllegalArgumentException("O logradouro não pode conter mais do que 100 caracteres ou ser nulo");
        }
        String regex = "[a-zA-ZÀ-ÖØ-öø-ÿ\\s]+";
        if (!logradouro.matches(regex)) {
            throw new IllegalArgumentException("O logradouro deve conter apenas letras");
        }
    }

    public static void validarNumero (String numero) {
        if (numero == null || numero.length() > 6) {
            throw new IllegalArgumentException("O número não pode conter mais do que 6 caracteres ou ser nulo");
        }

        String regex = "^\\d{1,6}$";
        if (!numero.matches(regex)) {
            throw new IllegalArgumentException("O número do endereço deve conter apenas números");
        }
    }
}