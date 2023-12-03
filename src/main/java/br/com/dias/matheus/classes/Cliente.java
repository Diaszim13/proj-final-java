package br.com.dias.matheus.classes;

public abstract class Cliente {

    public String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public abstract double calculaDesconto(double valor, int desc) throws Exception;
}
