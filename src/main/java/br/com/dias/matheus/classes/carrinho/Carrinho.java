package br.com.dias.matheus.classes.carrinho;

import br.com.dias.matheus.classes.cliente.Cliente;
import br.com.dias.matheus.classes.produtos.Produto;

import java.util.ArrayList;

public class Carrinho {


    public Cliente cliente;

    public ArrayList<Produto> produtos;

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
