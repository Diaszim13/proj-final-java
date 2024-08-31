/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package br.com.dias.matheus.DB.Mapper;
import gameoflife.Tabuleiro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 *
 * @author rsautter
 */
public class TabuleiroMapper {
    
    private int[][] matrixFromString(String sMat){
        String[] rows = sMat.split(";");
        int[][] matrix = new int[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            String[] cols = rows[i].split(",");
            matrix[i] = Arrays.stream(cols).mapToInt(Integer::parseInt).toArray();
        }
        return matrix;
    }
    
    private String matrixToString(int[][] mat){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                sb.append(mat[i][j]);
                if (j < mat[i].length - 1)
                    sb.append(",");
            }
            if (i < mat.length - 1)
                sb.append(";");
        }
        return sb.toString();
    }
    
    public List<Tabuleiro> readTabuleiros() throws SQLException{
        List<Tabuleiro> tabs = new ArrayList<>();
        Connection c = BDConnection.getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT * FROM mydb.Tabuleiro;");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            String id = rs.getString("idTabuleiro");
            String matrix = rs.getString("matrix");
            tabs.add(new Tabuleiro(id,matrixFromString(matrix)));
        }
        rs.close();
        return tabs;
    }
    
    public Tabuleiro readTabuleiro(String id) throws SQLException{
        Connection c = BDConnection.getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT * FROM mydb.Tabuleiro WHERE idTabuleiro = \""+id+"\" ;");
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()){
            rs.close();
            return null;
        }
        String idTabuleiro = rs.getString("idTabuleiro");
        String matrix = rs.getString("matrix");
        Tabuleiro tab = new Tabuleiro(idTabuleiro,matrixFromString(matrix));
        rs.close();
        return tab;
    }
    
    public void saveTabuleiro(Tabuleiro t) throws SQLException{
        Connection c = BDConnection.getConnection();
        PreparedStatement stmt = c.prepareStatement("INSERT INTO mydb.Tabuleiro (idTabuleiro, matrix) VALUES (?,?)");
        stmt.setString(1, t.getId());
        stmt.setString(2, matrixToString(t.getMatrix()));
        stmt.execute();
    }
}
