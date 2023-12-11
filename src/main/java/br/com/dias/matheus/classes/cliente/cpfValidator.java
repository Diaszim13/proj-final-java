package br.com.dias.matheus.classes.cliente;

public class cpfValidator {

    // AQ vai ser o seguinte vai validar se o cara é pf ou pj pela flag isFisica
    public boolean validateCPFCNPJ(String documento, boolean isFisica) {
        if (isFisica) {
            if(documento.equals(PessoaFisica.getCpf())) return false;
            if(documento.equals("") || documento == null) return false;
            if(!documento.matches("[0-9]+")) return false;
            if(documento.length() != 11) return false;

            String cpf = documento.replaceAll("[^0-9]", "");
            if (cpf.length() != 11) {
                return false;
            }

            //AQ vai verificar se todos os itens sao iguais
            if (cpf.matches("(\\d)\\1{10}")) {
                return false;
            }
        } else {
            if(documento.equals(PessoaJuridica.getCnpj())) return false;
            if(!documento.equals("")) return false;
            if(documento.length() != 14) return false;
            if(!documento.matches("[0-9]+")) return false;

            String cnpj = documento.replaceAll("[^0-9]", "");
            if (cnpj.length() != 14) {
                return false;
            }
            //AQ vai verificar se todos os itens sao iguais
            if (cnpj.matches("(\\d)\\1{10}")) {
                return false;
            }
        }

        return true;
    }

}
