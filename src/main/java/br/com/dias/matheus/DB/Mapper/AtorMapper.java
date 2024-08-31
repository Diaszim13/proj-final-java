/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dias.matheus.DB.Mapper;
import gameoflife.Ator;
import gameoflife.Tabuleiro;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rsautter
 */
public class AtorMapper {
    public List<Ator> getAtores() throws SQLException {
        Connection c = BDConnection.getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT * FROM mydb.Ator");
        ResultSet rs = stmt.executeQuery();
        List<Ator> output = new ArrayList<>();
        TabuleiroMapper tbm = new TabuleiroMapper();
        while(rs.next()){
            String idAtor = rs.getString("idAtor");
            long nivelDeSono = rs.getLong("nivelDeSono");
            String idTabuleiro = rs.getString("idTabuleiro");
            Tabuleiro t = tbm.readTabuleiro(idTabuleiro);
            if (t==null){
                throw new ErroDeChaveExterna("Tabuleiro", idTabuleiro);
            }
            output.add(new Ator(t, nivelDeSono, idAtor));
        }
        return output;
    }
    
    public Ator findAtor(String id) throws SQLException{
        Ator encontrado = null;
        Connection c = BDConnection.getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT * FROM mydb.Ator");
        ResultSet rs = stmt.executeQuery();
        List<Ator> output = new ArrayList<>();
        TabuleiroMapper tbm = new TabuleiroMapper();
        while(rs.next()){
            String idAtor = rs.getString("idAtor");
            long nivelDeSono = rs.getLong("nivelDeSono");
            String idTabuleiro = rs.getString("idTabuleiro");
            Tabuleiro t = tbm.readTabuleiro(idTabuleiro);
            if (t==null){
                throw new ErroDeChaveExterna("Tabuleiro", idTabuleiro);
            }
            encontrado = new Ator(t, nivelDeSono, idAtor);
        }
        return encontrado;
    }
    
    public void saveAtor(List<Ator> atores) throws SQLException {
        Connection c = BDConnection.getConnection();
        PreparedStatement stmt = c.prepareStatement("INSERT INTO Ator (idAtor, nivelDeSono, idTabuleiro) VALUES (?,?,?)");
        for(Ator ator: atores){
            stmt.setString(1, ator.getIdAtor());
            stmt.setLong(2, ator.getNivelDeSono());
            stmt.setString(3, ator.getTabuleiro().getId());
            stmt.addBatch();
        }
        stmt.executeBatch();
        stmt.clearBatch();
    }
    
    public void saveAtor(Ator ator) throws SQLException {
        Connection c = BDConnection.getConnection();
        PreparedStatement stmt = c.prepareStatement("INSERT INTO Ator (idAtor, nivelDeSono, idTabuleiro) VALUES (?,?,?)");
        stmt.setString(1, ator.getIdAtor());
        stmt.setLong(2, ator.getNivelDeSono());
        stmt.setString(3, ator.getTabuleiro().getId());
        stmt.executeUpdate();
    }
}
