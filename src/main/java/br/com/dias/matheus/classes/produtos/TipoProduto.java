package br.com.dias.matheus.classes.produtos;

public enum TipoProduto {
    PORTATIL("P"), RESIDENCIAL("R");

    private String codigo;

    TipoProduto(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static TipoProduto fromCodigo(String codigo) {
        for (TipoProduto tipo : values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de produto inválido: " + codigo);
    }
}

