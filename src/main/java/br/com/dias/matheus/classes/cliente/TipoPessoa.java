package br.com.dias.matheus.classes.cliente;

import br.com.dias.matheus.classes.produtos.TipoProduto;

public enum TipoPessoa {
    FISICA("F"),
    JURIDICA("J");

    private String codigo;

    TipoPessoa(String p) {
        this.codigo = p;
    }

    public String getCodigo() {
        return codigo;
    }

    public static TipoPessoa fromCodigo(String codigo) {
        for (TipoPessoa tipo : values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de pessoa inválido: " + codigo);
    }
}
