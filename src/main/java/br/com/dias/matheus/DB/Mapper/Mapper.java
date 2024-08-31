/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dias.matheus.DB.Mapper;

import gameoflife.Acao;
import gameoflife.Ator;
import gameoflife.Tabuleiro;
import java.sql.SQLException;

/**
 *
 * @author rsautter
 */
public class Mapper {
    public static synchronized void save(Acao a) throws SQLException{
        AcaoMapper amp = new AcaoMapper();
        amp.saveAcao(a);
    }
    public static synchronized void save(Ator a) throws SQLException{
        AtorMapper amp = new AtorMapper();
        amp.saveAtor(a);
    }
    public static synchronized void save(Tabuleiro t) throws SQLException{
        TabuleiroMapper tmp = new TabuleiroMapper();
        tmp.saveTabuleiro(t);
    }
}
