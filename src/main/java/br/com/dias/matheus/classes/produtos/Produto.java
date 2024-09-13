package br.com.dias.matheus.classes.produtos;

import jakarta.persistence.*;

@Entity
@Table(name = "produtos")
@Inheritance(strategy = InheritanceType.JOINED)  // Estratégia de herança
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "modelo", nullable = false)
    public String modelo;
    @Column(name = "preco", nullable = false)
    public Double preco;

    @Column(name = "voltagem", nullable = false)
    public String voltagem;

    public TipoProduto tipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto() {}

    public Produto(String modelo, double preco)
    {
        this.modelo = modelo;
        this.preco = preco;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getVoltagem() {
        return voltagem;
    }

    public void setVoltagem(String voltagem) {
        this.voltagem = voltagem;
    }

    public TipoProduto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProduto tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "modelo='" + modelo + '\'' +
                ", preco=" + preco +
                '}';
    }
}
