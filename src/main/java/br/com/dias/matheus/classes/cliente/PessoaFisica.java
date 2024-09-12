package br.com.dias.matheus.classes.cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

public class PessoaFisica extends Cliente {

    private static String cpf;

    public PessoaFisica(Long id, String nome, String cpf, Double saldo) {
        super(id, nome, saldo);
        this.cpf = cpf;
    }

    public PessoaFisica(String nome, String cpf, Double saldo)
    {
        super( nome, saldo);
        this.cpf = cpf;
    }


    public static String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }



    @Override
    public double calculaDesconto(double valor) throws Exception {
		try
		{
			if(valor <= 0) throw new Exception("O  valor precisa ser maior que 0");
			return (valor * 15) / 100;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
    return 0;
    }

    @Override
    public String toString() {
        return "PessoaFisica{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", saldo='" + saldo + '\'' +
                '}';
    }
}
