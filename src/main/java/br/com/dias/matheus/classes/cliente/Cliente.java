package br.com.dias.matheus.classes.cliente;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Cliente")
@Inheritance(strategy = InheritanceType.JOINED)  // Estratégia de herança
public abstract class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    public Long id;
    public String nome;

    public Double saldo;

    public TipoPessoa tipo;

    protected Cliente() {
    }

    public Cliente(Long id, String nome, Double saldo) {
        this.id = id;
        this.nome = nome;
        this.saldo = saldo;
    }

    public Cliente(String nome, Double saldo) {
        this.nome = nome;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public TipoPessoa getTipo() {
        return tipo;
    }

    public void setTipo(TipoPessoa tipo) {
        this.tipo = tipo;
    }

    public abstract double calculaDesconto(double valor) throws Exception;
}
