package br.com.dias.matheus;

import br.com.dias.matheus.classes.cliente.Cliente;
import br.com.dias.matheus.classes.cliente.ClienteDTO;
import br.com.dias.matheus.classes.cliente.PessoaFisica;
import br.com.dias.matheus.classes.cliente.PessoaJuridica;
import br.com.dias.matheus.classes.produtos.Produto;

import javax.swing.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        JOptionPane jp = new JOptionPane();
        jp.createDialog("Bem vindo ao sistema");

        Object[] opts = {"Cadastrar user",
                "Ver usuarios",
                "Cadastrar produtos",
                "Vender produto",
                "Emitir nota fiscal",
        };
        ClienteDTO DTO = new ClienteDTO();
        int opc = 55;
        do {
        opc = jp.showOptionDialog(null, "Escolha uma opção!", "Sistema", JOptionPane.DEFAULT_OPTION,
                JOptionPane.DEFAULT_OPTION, null, opts, opts[0]);
            System.out.println(opc);
        switch (opc)
        {
            case 0:
                //Vai cadastrar o usuario
                Object[] campos = {
                            "nome: ", new JTextField(),
                        "documento: ", new JTextField(),
                        new JCheckBox("Pessoa fisica"),
                        new JCheckBox("Pessoa juridica")
                };

                int opc1 = jp.showConfirmDialog(null, campos, "form", jp.OK_CANCEL_OPTION, jp.PLAIN_MESSAGE);

                if(opc1 == jp.OK_OPTION) {
                    try {
                        String field1 = ((JTextField) campos[1]).getText();
                        String field2 = ((JTextField) campos[3]).getText();
                        if (((JCheckBox)campos[4]).isSelected()) {
                            if(DTO.validateCPFCNPJ(field2, true)){
                                PessoaFisica pf = new PessoaFisica(field1, field2);
                                jp.showMessageDialog(null, "Cadastrado com sucesso:" + pf.toString());
                            } else {
                                throw new Exception("CPF invalido");

                            }
                        } else if (((JCheckBox) campos[5]).isSelected()) {
                            if (DTO.validateCPFCNPJ(field2, false)) {
                                PessoaJuridica pJ = new PessoaJuridica(field1, field2);
								jp.showMessageDialog(null, "Cadastrado com sucesso:" + pJ.toString());
                            } else {
                                throw new Exception("CPNJ invalido");
                            }
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                    //jp.showOptionDialog(null, "Cadastre os dados!", "Sistema", campos,
                    //      JOptionPane.DEFAULT_OPTION, null, campos, opts[0]);
                    break;
                    case 2:
                        //Vai listar os usiarios

                        break;
                    case 3:
                        //Vai cadastrar o usuario
                        break;
                    case 4:
                        //Vai cadastrar o usuario
                        break;
                }
        }while (opc >= 0);

    }
}
