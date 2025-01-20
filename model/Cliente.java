package model;

public class Cliente {
    private int idcliente;
    private String nome, email;
    private Endereco endereco;

    //permite criar um objeto Cliente com todos seus atributos já definidos
    public Cliente (int idcliente, String nome, String email, Endereco endereco) {
        this.idcliente = idcliente;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
    }

    //permite instanciar objetos sem valores iniciais obrigatórios
    public Cliente () {}

    public int getIdcliente () {
        return idcliente;
    }

    public void setIdcliente (int idcliente) {
        this.idcliente = idcliente;
    }

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public Endereco getEndereco () {
        return endereco;
    }

    public void setEndereco (Endereco endereco) {
        this.endereco = endereco;
    }
}
