package br.com.dias.matheus.classes.cliente;

import javax.annotation.processing.Generated;

public abstract class Cliente {
    //@Generated("id")
    //public Long id;
    public String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public static String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public abstract double calculaDesconto(double valor, int desc) throws Exception;
}
