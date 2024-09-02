package br.com.dias.matheus.classes.carrinho;

import br.com.dias.matheus.classes.cliente.Cliente;
import br.com.dias.matheus.classes.produtos.Produto;
import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "ds")
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    public Cliente cliente;

    @ManyToMany
    @JoinColumn(name = "produto_id")
    public ArrayList<Produto> produtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Carrinho(Cliente cliente, ArrayList<Produto> produtos) {
        this.cliente = cliente;
        this.produtos = produtos;
    }

    public Carrinho() {}

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return "Carrinho{" +
                "cliente=" + cliente +
                ", produtos=" + produtos +
                '}';
    }
}
