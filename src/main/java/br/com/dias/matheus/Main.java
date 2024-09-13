package br.com.dias.matheus;

import br.com.dias.matheus.DB.BDConnection;
import br.com.dias.matheus.DB.ClienteMapper;
import br.com.dias.matheus.DB.NotaFiscalMapper;
import br.com.dias.matheus.DB.ProdutoMapper;
import br.com.dias.matheus.classes.carrinho.Carrinho;
import br.com.dias.matheus.classes.cliente.*;
import br.com.dias.matheus.classes.compra.NotaFiscal;
import br.com.dias.matheus.classes.produtos.*;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        JOptionPane jp = new JOptionPane();
        Object[] opts = {"Cadastrar usuario",
                "Ver usuarios",
                "Cadastrar produtos",
                "Vender produto",
                "estornar compra",
                "Historico de compras",
                "Encerrar sistema"
        };

        cpfValidator validator = new cpfValidator();
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        ArrayList<NotaFiscal> notas = new ArrayList<NotaFiscal>();
        ClienteMapper mapper = new ClienteMapper();
        ProdutoMapper produtoMapper = new ProdutoMapper();
        NotaFiscalMapper notaMapper = new NotaFiscalMapper();
        int opc = 55;
        do {
            opc = jp.showOptionDialog(null, "Escolha uma opção!", "Sistema", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.DEFAULT_OPTION, null, opts, opts[0]);

            switch (opc) {
                case 0:
                    Object[] campos = {
                            "nome: ", new JTextField(),
                            "documento: ", new JTextField(),
                            "saldo: ", new JTextField(),
                            new JCheckBox("Pessoa fisica"),
                            new JCheckBox("Pessoa juridica")
                    };


                    int opc1 = jp.showConfirmDialog(null, campos, "form", jp.OK_CANCEL_OPTION, jp.PLAIN_MESSAGE);

                    if (opc1 == JOptionPane.OK_OPTION) {
                        try {
                            String field1 = ((JTextField) campos[1]).getText();
                            String field2 = ((JTextField) campos[3]).getText();
                            Double field3 = Double.parseDouble(((JTextField) campos[5]).getText());

                            if (field1.equals("") || field2.equals("")) throw new Exception("Campos vazios");
                            if (!((JCheckBox) campos[6]).isSelected() && !((JCheckBox) campos[7]).isSelected())
                                throw new Exception("Selecione uma opção");
                            if (((JCheckBox) campos[6]).isSelected() && ((JCheckBox) campos[7]).isSelected())
                                throw new Exception("Selecione apenas uma opção");

                            if (((JCheckBox) campos[6]).isSelected()) {
                                if (!validator.validateCPFCNPJ(field2, true)) throw new Exception("CPF invalido");

                                PessoaFisica pf = new PessoaFisica(field1, field2, field3);
                                clientes.add(pf);
                                mapper.saveCliente(pf);
                                jp.showMessageDialog(null, "Cadastrado com sucesso:" + pf.toString());

                            } else if (((JCheckBox) campos[7]).isSelected()) {
                                if (!validator.validateCPFCNPJ(field2, false)) throw new Exception("CPNJ invalido");
                                PessoaJuridica pj = new PessoaJuridica(field1, field2, field3);
                                clientes.add(pj);
                                mapper.saveCliente(pj);
                                jp.showMessageDialog(null, "Cadastrado com sucesso:" + pj.toString());
                            }
                        } catch (Exception ex) {
                            jp.showMessageDialog(null, ex.getMessage());
                        }
                    }
                    break;
                case 1:
                    try {
                        List<Cliente> clientesList = mapper.getAllClientes();
                        String clientesString = mapper.formatClientes(clientesList);
                        JOptionPane.showMessageDialog(null, clientesString, "Lista de Clientes", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao recuperar clientes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
            //jp.showMessageDialog(null, "Lista de usuarios: " + clientes.toString());
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

                    String field1 = (((JTextField) campos1[1]).getText());

                    Double field2 = Double.parseDouble(((JTextField) campos1[3]).getText());
                    Integer field3 = Integer.parseInt(((JTextField) campos1[5]).getText());

                    if (opc2 == JOptionPane.OK_OPTION) {
                        if (((JCheckBox) campos1[6]).isSelected()) {
                            CaixaSomPortatil cp = new CaixaSomPortatil(field1, field2, field3);

                            if (!cp.getModelo().equals("")) {
                                jp.showMessageDialog(null, "Cadastrado com sucesso:" + cp.toString());
                                produtos.add(cp);
                                produtoMapper.saveProduto(cp);
                            }

                        }
                        if (((JCheckBox) campos1[7]).isSelected()) {
                            CaixaSomResidencial cr = new CaixaSomResidencial(field1, field2, field3);
                            if (!cr.getModelo().equals("")) {
                                jp.showMessageDialog(null, "Cadastrado com sucesso:" + cr.toString());
                                produtos.add(cr);
                                produtoMapper.saveProduto(cr);
                            }
                        }
                    }
                    break;
                case 3:
                    // Recuperar lista de clientes
                    List<Cliente> clientesopcs = mapper.getAllClientes();
                    String[] clienteOptions = mapper.formatClientesForSelection(clientesopcs);

                    // Exibir caixa de diálogo para seleção
                    int clienteSelected = JOptionPane.showOptionDialog(null,
                            "Escolha um cliente",
                            "Sistema",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            clienteOptions,
                            clienteOptions[0]);
                    Cliente cliente = clientesopcs.get(clienteSelected);

                    if (clienteSelected >= 0) {
                        {
                            //Aq a compra vai ser feita em base do carrinho
                            Carrinho carrinho = new Carrinho(cliente, new ArrayList<>());

                            try {
                                Object[] lista = {};

                                /*for (Produto p : produtos) {
                                    //aq CRIA um array de produtos para mostrar no showOptionDialog
                                    lista = Arrays.copyOf(lista, lista.length + 1);
                                    lista[lista.length - 1] = p.getModelo() + " - " + p.getPreco();
                                }*/
                                int opc3 = 0;
                                try {
                                    List<Produto> produtosList = produtoMapper.getAllProdutos();
                                    String[] produtoOptions = produtoMapper.formatProdutosForSelection(produtosList);
                                    opc3 = JOptionPane.showOptionDialog(null,
                                            "Escolha um produto para o " + cliente.getNome() + " comprar",
                                            "Sistema",
                                            JOptionPane.DEFAULT_OPTION,
                                            JOptionPane.PLAIN_MESSAGE,
                                            null,
                                            produtoOptions,
                                            produtoOptions[0]);

                                    if (opc3 >= 0) {
                                        Produto produtoSelecionado = produtosList.get(opc3);
                                        // Aqui você pode fazer o que quiser com o produto selecionado
                                        JOptionPane.showMessageDialog(null, "Você selecionou: " + produtoSelecionado.getModelo());
                                        Produto p = produtosList.get(opc3);
                                        if (produtoSelecionado.getPreco() > cliente.getSaldo())
                                            throw new Exception("O  Cliente não tem dinheiro suficiente");

                                        int confirmCompra = jp.showConfirmDialog(null, "Produto escolhido: " + produtoSelecionado.toString(), "Deseja confirmar a compra?", jp.OK_CANCEL_OPTION, jp.PLAIN_MESSAGE);

                                        //Aqui eu vou fazer a compra
                                        if (confirmCompra == jp.OK_OPTION) {
                                            Double produtoNovoPreco = p.getPreco() - cliente.calculaDesconto(p.getPreco());
                                            int numero = (int) (Math.random() * 1000); //AQUI decidi gerar um numero aleatorio para ser o numero da nota fiscal
                                            if (notas.contains(numero)) numero = (int) (Math.random() * 1000);
                                            NotaFiscal nf = new NotaFiscal(numero, cliente, produtoNovoPreco);
                                            cliente.setSaldo(cliente.getSaldo() - p.getPreco());
                                            notas.add(nf);
                                            notaMapper.createNotaFiscal(nf);
                                            if (nf.getCliente() != null) {
                                                jp.showMessageDialog(null, "Compra realizada com sucesso: " + nf.toString());
                                            } else {
                                                jp.showMessageDialog(null, "Erro ao realizar a compra");
                                            }
                                        }
                                    }

                                } catch (SQLException e) {
                                    e.printStackTrace();
                                    JOptionPane.showMessageDialog(null, "Erro ao recuperar produtos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                                }


                            } catch (Exception $e) {
                                jp.showMessageDialog(null, $e.getMessage());
                            }
                        }
                    }

                    break;
                case 4:
                    Object[] produtosdevolveopcs = {};
                    try {
                        if (notas.isEmpty()) throw new Exception("Não foi realizada nenhuma compra ainda");

                        for (NotaFiscal nota : notas) {
                            //AQui criei um array das notas que ja foram criadas 'compras'
                            produtosdevolveopcs = Arrays.copyOf(produtosdevolveopcs, produtosdevolveopcs.length + 1);
                            produtosdevolveopcs[produtosdevolveopcs.length - 1] = nota.getNumero();
                        }

                        int produtoDevolvido = jp.showOptionDialog(null, "Escolha uma compra para reembolsar", "Reembolso", JOptionPane.DEFAULT_OPTION,
                                JOptionPane.DEFAULT_OPTION, null, produtosdevolveopcs, produtosdevolveopcs[0]);

                        if (produtoDevolvido > 0) {
                            NotaFiscal nota = notas.get(produtoDevolvido);
                            int confirmDevolucao = jp.showConfirmDialog(null, "Compra escolhida: " + nota.toString(), "Deseja confirmar a devolução?", jp.OK_CANCEL_OPTION, jp.PLAIN_MESSAGE);

                            if (confirmDevolucao == jp.OK_OPTION) {
                                nota.getCliente().setSaldo(nota.getCliente().getSaldo() + nota.getValorCompra());
                                notas.remove(nota);
                                jp.showMessageDialog(null, "Devolução realizada com sucesso");
                            }
                        }
                    } catch (Exception $e) {

                    }

                    break;
                case 5:
                    //Ver historico de compras
                    try {
                        // Recuperar lista de notas fiscais
                        List<NotaFiscal> notasFiscais = notaMapper.getAllNotasFiscais();

                        JOptionPane.showMessageDialog(null, notaMapper.formatNotasFiscais(notasFiscais));
                    } catch (SQLException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erro ao recuperar notas fiscais: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 6:
                    jp.showMessageDialog(null, "Sistema finalizado com sucesso");
                    opc = -1; // AQ ACHAR COMO SAIR DO SISTEMA
                    break;
            }
        } while (opc >= 0);

    }
}
