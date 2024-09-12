package br.com.dias.matheus.DB;

import br.com.dias.matheus.classes.produtos.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoMapper {

    public void saveProduto(Produto produto) throws SQLException
    {
        Connection coon = BDConnection.getConnection();

        PreparedStatement pst = coon.prepareStatement("INSERT INTO produto values (?, ?, ?)");
        pst.setString(1, produto.getModelo());
        pst.setDouble(2, produto.getPreco());
        pst.setString(3, produto.getVoltagem());

        pst.executeUpdate();

        pst.close();
        coon.close();

    }
}
