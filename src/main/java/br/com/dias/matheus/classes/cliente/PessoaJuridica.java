package br.com.dias.matheus.classes.cliente;

public class PessoaJuridica extends Cliente {
    public static String cnpj;

    public PessoaJuridica(String nome, String cnpj) {
        super(nome);
        this.cnpj = cnpj;
    }

    public static String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public double calculaDesconto(double valor) throws Exception {
        if(valor <= 0) throw new Exception("O numero precisa ser mais q 0");
        return (valor * 20) / 100;
    }

    @Override
    public String toString() {
        return "PessoaJuridica{" +
                "nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }
}

