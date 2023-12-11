package br.com.dias.matheus.classes.produtos;

public class CaixaSomResidencial extends Produto {

    private Integer pw;

    public CaixaSomResidencial(String modelo, double preco, Integer pw)
    {
        super(modelo, preco);
        this.pw = pw;
    }

    public Integer getPw() {
        return pw;
    }

    public void setPw(Integer pw) {
        this.pw = pw;
    }

    @Override
    public String toString() {
        return "CaixaSomResidencial{" +
                "pw='" + pw + '\'' +
                ", modelo='" + modelo + '\'' +
					", preco='" + preco + '\'' +
					'}';
    }
}
