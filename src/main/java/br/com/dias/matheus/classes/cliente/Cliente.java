package br.com.dias.matheus.classes.cliente;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Cliente")
public abstract class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    public Long id;
    public String nome;

    public Double saldo;

    protected Cliente() {
    }

    public Cliente(Long id, String nome, Double saldo) {
        this.id = id;
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

    public abstract double calculaDesconto(double valor) throws Exception;
}
