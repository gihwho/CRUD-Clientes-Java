package model;

public class Endereco {
    private int idendereco;
    private String estado, cidade, bairro, logradouro, numero;

    public Endereco (int idendereco, String estado, String cidade, String bairro, String logradouro, String numero) {
        this.idendereco = idendereco;
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
    }

    public Endereco () {}

    @Override
    public String toString() {
        return "Estado: " + estado + ", Cidade: " + cidade + ", Bairro: " + bairro + ", Logradouro: " + logradouro + ", Número: " + numero;
    }

    public int getIdEndereco() {
        return idendereco;
    }

    public void setIdEndereco(int idendereco) {
        this.idendereco = idendereco;
    }

    public String getEstado () {
        return estado;
    }

    public void setEstado (String estado) {
        this.estado = estado;
    }

    public String getCidade () {
        return cidade;
    }

    public void setCidade (String cidade) {
        this.cidade = cidade;
    }

    public String getBairro () {
        return bairro;
    }

    public void setBairro (String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro () {
        return logradouro;
    }

    public void setLogradouro (String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero () {
        return numero;
    }

    public void setNumero (String numero) {
        this.numero = numero;
    }
}