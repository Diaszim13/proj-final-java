package br.com.dias.matheus.classes;

public class Produto {
    public String modelo;
    public Double preco;

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

    @Override
    public String toString() {
        return "Produto{" +
                "modelo='" + modelo + '\'' +
                ", preco=" + preco +
                '}';
    }
}
