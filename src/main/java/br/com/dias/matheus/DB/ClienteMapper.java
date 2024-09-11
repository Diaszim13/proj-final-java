package br.com.dias.matheus.DB;

import br.com.dias.matheus.classes.cliente.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteMapper {
    public void saveCliente(Cliente cliente) throws SQLException
    {
        Connection coon = (Connection) br.com.dias.matheus.DB.Connection.getConnection();
        PreparedStatement pst = coon.prepareStatement("insert into Cliente values (?, ?)");

        pst.setString(1, cliente.getNome());
        pst.setDouble(2, cliente.getSaldo());

        pst.execute();
    }

    public List<Cliente> loadCliente(Cliente cliente) throws SQLException
    {
        Connection coon = (Connection) br.com.dias.matheus.DB.Connection.getConnection();
        PreparedStatement pst = coon.prepareStatement("SELECT * FROM CLIENTES where id >?");
        pst.setLong(1, cliente.getId());
        List<Cliente> clientes = new ArrayList<>();
        ResultSet res = pst.executeQuery();

        while (res.next())
        {
//            clientes.add();
        }

        return clientes;
    }


/*
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
 */

}
