package br.com.dias.matheus.classes;

public class CaixaSomPortatil extends Produto {

    private int bateria;

    public CaixaSomPortatil(String modelo, double preco, int bateria)
    {
        super(modelo, preco);
        this.bateria = bateria;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    @Override
    public String toString() {
        return "CaixaSomPortatil{" +
                "bateria=" + bateria +
                ", modelo='" + modelo + '\'' +
                '}';
    }
}
