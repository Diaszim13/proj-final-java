/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dias.matheus.DB.Mapper;
import gameoflife.Acao;
import gameoflife.Ator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author rsautter
 */
public class AcaoMapper {
    
    public void saveAcao(Acao acao) throws SQLException{
        Connection c = BDConnection.getConnection();
        PreparedStatement stmt = c.prepareStatement("INSERT INTO Acao (idAcao, x, y, idAtor) VALUES (?,?,?,?)");
        stmt.setString(1, acao.getIdAcao());
        stmt.setInt(2, acao.getX());
        stmt.setInt(3, acao.getY());
        stmt.setString(4, acao.getAtor().getIdAtor());
        stmt.executeUpdate();
    }
    
    public List<Acao> loadAcao(Acao acao) throws SQLException{
        Connection c = BDConnection.getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT * FROM mydb.Acao");
        ResultSet rs = stmt.executeQuery();
        List<Acao> acoes = new ArrayList<Acao>();
        AtorMapper amp = new AtorMapper();
        while(rs.next()){
            String idAcao = rs.getString("idAcao");
            int x = rs.getInt("x");
            int y = rs.getInt("y");
            String idAtor = rs.getString("idAtor");
            
            /// Para manter a consistencia, procuramos recursivamente um Ator com o idAtor
            Ator a = amp.findAtor(idAtor);
            if(a==null){
                throw new ErroDeChaveExterna("Ator", idAtor); 
            }
            acoes.add(new Acao(a,x,y));
        }
        return acoes;
    }
    
}
