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

		try {
			if(valor <= 0) throw new Exception("O  valor precisa ser maior que 0");
			return (valor * 20) / 100;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

    }

    @Override
    public String toString() {
        return "PessoaJuridica{" +
                "nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }
}

