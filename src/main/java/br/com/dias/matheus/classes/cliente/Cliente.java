package br.com.dias.matheus.classes.cliente;

public abstract class Cliente {
    //@Generated("id")
    //public Long id;
    public String nome;

    public Double saldo;

    public Cliente(String nome, Double saldo) {
        this.nome = nome;
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public abstract double calculaDesconto(double valor) throws Exception;


}
