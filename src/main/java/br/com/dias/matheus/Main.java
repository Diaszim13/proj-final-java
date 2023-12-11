package br.com.dias.matheus;

import br.com.dias.matheus.classes.cliente.*;
import br.com.dias.matheus.classes.compra.NotaFiscal;
import br.com.dias.matheus.classes.produtos.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        JOptionPane jp = new JOptionPane();

        Object[] opts = {"Cadastrar user",
                "Ver usuarios",
                "Cadastrar produtos",
                "Vender produto",
                "Emitir nota fiscal",
        };

        cpfValidator validator = new cpfValidator();
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        ArrayList<NotaFiscal> notas = new ArrayList<NotaFiscal>();

        int opc = 55;
        do {
        opc = jp.showOptionDialog(null, "Escolha uma opção!", "Sistema", JOptionPane.DEFAULT_OPTION,
                JOptionPane.DEFAULT_OPTION, null, opts, opts[0]);
            System.out.println(opc);

        switch (opc)
        {
            case 0:
                Object[] campos = {
                            "nome: ", new JTextField(),
                        "documento: ", new JTextField(),
                        new JCheckBox("Pessoa fisica"),
                        new JCheckBox("Pessoa juridica")
                };


                int opc1 = jp.showConfirmDialog(null, campos, "form", jp.OK_CANCEL_OPTION, jp.PLAIN_MESSAGE);

                if(opc1 == JOptionPane.OK_OPTION) {
                    try {
                        String field1 = ((JTextField) campos[1]).getText();
                        String field2 = ((JTextField) campos[3]).getText();

                        if(field1.equals("") || field2.equals("")) throw new Exception("Campos vazios");
                        if(!((JCheckBox)campos[4]).isSelected() && !((JCheckBox)campos[5]).isSelected()) throw new Exception("Selecione uma opção");
                        if(((JCheckBox)campos[4]).isSelected() && ((JCheckBox)campos[5]).isSelected()) throw new Exception("Selecione apenas uma opção");

                        if (((JCheckBox)campos[4]).isSelected()) {
                            if(!validator.validateCPFCNPJ(field2, true)) throw new Exception("CPF invalido");

                            PessoaFisica pf = new PessoaFisica(field1, field2);
                            clientes.add(pf);
                            jp.showMessageDialog(null, "Cadastrado com sucesso:" + pf.toString());

                        } else if (((JCheckBox) campos[5]).isSelected()) {
                            if(!validator.validateCPFCNPJ(field2, false)) throw new Exception("CPNJ invalido");
                            PessoaJuridica pJ = new PessoaJuridica(field1, field2);
                            clientes.add(pJ);
                            jp.showMessageDialog(null, "Cadastrado com sucesso:" + pJ.toString());
                        }
                    } catch (Exception ex) {
                        jp.showMessageDialog(null, ex.getMessage());
                    }
                }
                    break;
            case 1:
                jp.showMessageDialog(null, "Lista de usuarios: " + clientes.toString());
                break;
            case 2:
					Object[] campos1 = {
						"modelo: ", new JTextField(),
						"preco: ", new JTextField(),
						"potencia: ", new JTextField(),
						new JCheckBox("Portatil"),
						new JCheckBox("Residencial")
					};
					
					int opc2 = jp.showConfirmDialog(null, campos1, "form", jp.OK_CANCEL_OPTION, jp.PLAIN_MESSAGE);

					String field1 = (((JTextField)campos1[1]).getText());

					Double field2 = Double.parseDouble(((JTextField)campos1[3]).getText());
					Integer field3 = Integer.parseInt(((JTextField)campos1[5]).getText());

					if(opc2 == JOptionPane.OK_OPTION)
					{
						if(((JCheckBox)campos1[6]).isSelected())
						{
							CaixaSomPortatil cp = new CaixaSomPortatil(field1, field2, field3);

							if(!cp.getModelo().equals(""))
							{
                                jp.showMessageDialog(null, "Cadastrado com sucesso:" + cp.toString());
                                produtos.add(cp);
                            }
						
						}
						if(((JCheckBox)campos1[7]).isSelected())
						{
							CaixaSomResidencial cr = new CaixaSomResidencial(field1, field2, field3);
							if(!cr.getModelo().equals(""))
							{
                                jp.showMessageDialog(null, "Cadastrado com sucesso:" + cr.toString());
                                produtos.add(cr);
							}
						}
					}
                        break;
                    case 3:
                        Object [] lista = {};

                        for (Produto p : produtos) {
                            lista = Arrays.copyOf(lista, lista.length + 1);
                            lista[lista.length - 1] = p.getModelo();
                        }

                        int opc3 = jp.showOptionDialog(null, "Escolha uma opção!", "Sistema", JOptionPane.DEFAULT_OPTION,
                                JOptionPane.PLAIN_MESSAGE, null, lista, lista[0]);

                        if(opc3 >= 0)
                        {
                            Produto p = produtos.get(opc3);
                            int confirmCompra = jp.showConfirmDialog(null, "Produto escolhido: " + p.toString(), "Deseja confirmar a compra?", jp.OK_CANCEL_OPTION, jp.PLAIN_MESSAGE);

                            //Aqui eu vou fazer a compra
                            if(confirmCompra == jp.OK_OPTION) {
                                Object[] clientesopcs = {};

                                for (Cliente c : clientes) {
                                    clientesopcs = Arrays.copyOf(clientesopcs, clientesopcs.length + 1);
                                    clientesopcs[clientesopcs.length - 1] = c.getNome();
                                }
                                int clienteSelected = jp.showOptionDialog(null, "Escolha uma opção!", "Sistema", JOptionPane.DEFAULT_OPTION,
                                        JOptionPane.DEFAULT_OPTION, null, clientesopcs, clientesopcs[0]);


								Cliente cliente = clientes.get(clienteSelected);

                                if (clienteSelected >= 0)
                                {
                                    NotaFiscal nf = new NotaFiscal(1, cliente, p.getPreco()); 
								    notas.add(nf);
									if(nf.getCliente() != null)
									{
										jp.showMessageDialog(null, "Compra realizada com sucesso: " + nf.toString());
									}
									else
									{
										jp.showMessageDialog(null, "Erro ao realizar a compra");
									}

								}
                            }
                        }
						break;
                    case 4:
                        //Ver historico de compras
                        jp.showMessageDialog(null, "Historico de compras: " + notas.toString());
                        break;
                }
        }while (opc >= 0);

    }
}
