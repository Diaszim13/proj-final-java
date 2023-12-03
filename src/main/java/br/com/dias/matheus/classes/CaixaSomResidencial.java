package br.com.dias.matheus.classes;

public class CaixaSomResidencial extends Produto {

    private String pw;

    public CaixaSomResidencial(String modelo, double preco, String pw)
    {
        super(modelo, preco);
        this.pw = pw;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    @Override
    public String toString() {
        return "CaixaSomResidencial{" +
                "pw='" + pw + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }
}
