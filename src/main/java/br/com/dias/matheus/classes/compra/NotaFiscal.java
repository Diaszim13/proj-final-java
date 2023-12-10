package br.com.dias.matheus.classes.compra;

import br.com.dias.matheus.classes.cliente.Cliente;

public class NotaFiscal {

    private int numero;

    private Cliente cliente;

    private double valorCompra;

    public NotaFiscal(int numero, Cliente cliente, double valorCompra)
    {
        this.cliente = cliente;
        this.numero = numero;
        this.valorCompra = valorCompra;
    }

    public NotaFiscal() {

    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    @Override
    public String toString() {
        return "NotaFiscal{" +
                "numero=" + numero +
                ", cliente=" + cliente +
                ", valorCompra=" + valorCompra +
                '}';
    }
}
