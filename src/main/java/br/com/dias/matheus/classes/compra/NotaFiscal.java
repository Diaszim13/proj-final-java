package br.com.dias.matheus.classes.compra;

import br.com.dias.matheus.classes.cliente.Cliente;
import jakarta.persistence.*;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private int numero;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private double valorCompra;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
