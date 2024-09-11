/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dias.matheus.DB;

import br.com.dias.matheus.classes.cliente.Cliente;
import br.com.dias.matheus.classes.produtos.Produto;
import gameoflife.Acao;
import gameoflife.Ator;
import gameoflife.Tabuleiro;
import java.sql.SQLException;

/**
 *
 * @author rsautter
 */
public class Mapper {
    public static synchronized void save(Cliente a) throws SQLException{
        ClienteMapper amp = new ClienteMapper();
        amp.saveCliente(a);
    }
//Fazer o mesmo para as outras funcoes e outras classes
    public static synchronized void save(Ator a) throws SQLException{
        AtorMapper amp = new AtorMapper();
        amp.saveAtor(a);
    }
    public static synchronized void save(Produto p) throws SQLException{
        ProdutoMapper tmp = new ProdutoMapper();
        tmp.saveTabuleiro(t);
    }
}
