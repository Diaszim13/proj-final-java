package br.com.dias.matheus.classes.cliente;

public class cpfValidator {

    // AQ vai ser o seguinte vai validar se o cara é pf ou pj pela flag isFisica
    public boolean validateCPFCNPJ(String documento, boolean isFisica) {
        if (isFisica) {
            String cpf = documento.replaceAll("[^0-9]", "");
            if(cpf.equals(PessoaFisica.getCpf())) return false;
            if(cpf.isEmpty()) return false;
            if(cpf.length() != 11) return false;
            if(!cpf.matches("[0-9]+")) return false;
        } else {
            String cnpj = documento.replaceAll("[^0-9]", "");
            if (cnpj.equals(PessoaJuridica.getCnpj())) return false;
            if (cnpj.isEmpty()) return false;
            if (cnpj.length() != 14) return false;
            if (!cnpj.matches("[0-9]+")) return false;
        }
        return true;
    }

}
