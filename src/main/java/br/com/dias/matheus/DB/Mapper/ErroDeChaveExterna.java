/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dias.matheus.DB.Mapper;
import java.sql.SQLException;

/**
 *
 * @author rsautter
 */
public class ErroDeChaveExterna extends SQLException{
    public ErroDeChaveExterna(String nomeTabela, String chaveExterna){
        super("Erro ao procurar a chave externa: "+chaveExterna+" na tabela"+nomeTabela);
    }
}
