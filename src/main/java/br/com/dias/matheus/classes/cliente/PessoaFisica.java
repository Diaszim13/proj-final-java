package br.com.dias.matheus.classes.cliente;

public class PessoaFisica extends Cliente {

    private static String cpf;

    public PessoaFisica(String nome, String cpf) {
        super(nome);
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
        if(valor <= 0) throw new Exception("O  valor precisa ser maior que 0");

        return (valor * 15) / 100;
    }

    @Override
    public String toString() {
        return "PessoaFisica{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
