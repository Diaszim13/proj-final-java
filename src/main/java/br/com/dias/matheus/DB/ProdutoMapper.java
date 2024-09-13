package br.com.dias.matheus.DB;

import br.com.dias.matheus.classes.produtos.CaixaSomPortatil;
import br.com.dias.matheus.classes.produtos.CaixaSomResidencial;
import br.com.dias.matheus.classes.produtos.Produto;
import br.com.dias.matheus.classes.produtos.TipoProduto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoMapper {

   /* public void saveProduto(Produto produto) throws SQLException
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
*/

    public List<Produto> getAllProdutos() throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        Connection conn = BDConnection.getConnection();
        String sql = "SELECT * FROM produto";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet res = pst.executeQuery();

        while (res.next()) {
            Produto produto;
            String tipo = res.getString("tipo");

            if ("P".equals(tipo)) {
                produto = new CaixaSomPortatil();
                ((CaixaSomPortatil) produto).setBateria(res.getInt("bateria"));
            } else {
                produto = new CaixaSomResidencial();
                ((CaixaSomResidencial) produto).setVoltagem(res.getString("voltagem"));
            }

            produto.setId(res.getLong("id"));
            produto.setModelo(res.getString("modelo"));
            produto.setPreco(res.getDouble("preco"));
            produtos.add(produto);
        }

        return produtos;
    }
    public String formatProdutos(List<Produto> produtos) {
        StringBuilder sb = new StringBuilder();
        for (Produto produto : produtos) {
            sb.append("ID: ").append(produto.getId()).append("\n");
            sb.append("Modelo: ").append(produto.getModelo()).append("\n");
            sb.append("Preço: ").append(produto.getPreco()).append("\n");
            if (produto instanceof CaixaSomPortatil) {
                sb.append("Bateria: ").append(((CaixaSomPortatil) produto).getBateria()).append(" horas\n");
            } else if (produto instanceof CaixaSomResidencial) {
                sb.append("Voltagem: ").append(((CaixaSomResidencial) produto).getVoltagem()).append("V\n");
            }
            sb.append("-------------------------\n");
        }
        return sb.toString();
    }

    public String[] formatProdutosForSelection(List<Produto> produtos) {
        String[] options = new String[produtos.size()];

        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            String descricao = "ID: " + produto.getId() + " | Modelo: " + produto.getModelo() + " | Preço: " + produto.getPreco();
            if (produto instanceof CaixaSomPortatil) {
                descricao += " | Bateria: " + ((CaixaSomPortatil) produto).getBateria() + " horas";
            } else if (produto instanceof CaixaSomResidencial) {
                descricao += " | Voltagem: " + ((CaixaSomResidencial) produto).getVoltagem() + "V";
            }
            options[i] = descricao;
        }

        return options;
    }


    public Produto getProdutoById(Long id) throws SQLException {
        Connection conn = BDConnection.getConnection();
        String sql = "SELECT * FROM produto WHERE id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setLong(1, id);
        ResultSet res = pst.executeQuery();

        if (res.next()) {
            //AQ USA O CAMPO TIPO PARA DIFERENCIAR
            String codigoTipo = res.getString("tipo");
            TipoProduto tipoProduto = TipoProduto.fromCodigo(codigoTipo);

            if (tipoProduto.equals(TipoProduto.PORTATIL)) {
                CaixaSomPortatil portatil = new CaixaSomPortatil();
                portatil.setId(res.getLong("id"));
                portatil.setModelo(res.getString("modelo"));
                portatil.setPreco(res.getDouble("preco"));
                portatil.setBateria(res.getInt("bateria"));
                return portatil;
            } else if (tipoProduto.equals(TipoProduto.RESIDENCIAL)) {
                CaixaSomResidencial residencial = new CaixaSomResidencial();
                residencial.setId(res.getLong("id"));
                residencial.setModelo(res.getString("modelo"));
                residencial.setPreco(res.getDouble("preco"));
                residencial.setVoltagem(res.getString("voltagem"));
                return residencial;
            }
        }

        return null; // Retorna null se não encontrar o produto
    }

    public void saveProduto(Produto produto) throws SQLException {
        Connection conn = BDConnection.getConnection();
        String sql;

        if (produto instanceof CaixaSomPortatil) {
            // SQL para inserir CaixaDeSomPortatil
            sql = "INSERT INTO produto (modelo, preco, bateria, tipo) VALUES (?, ?, ?, 'P')";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, produto.getModelo());
            pst.setDouble(2, produto.getPreco());
            pst.setInt(3, ((CaixaSomPortatil) produto).getBateria());
            pst.executeUpdate();

        } else if (produto instanceof CaixaSomResidencial) {
            // SQL para inserir CaixaDeSomResidencial
            sql = "INSERT INTO produto (modelo, preco, voltagem, tipo) VALUES (?, ?, ?, 'R')";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, produto.getModelo());
            pst.setDouble(2, produto.getPreco());
            pst.setString(3, ((CaixaSomResidencial) produto).getVoltagem());
            pst.executeUpdate();
        }
    }


    public void updateProduto(Produto produto) throws SQLException {
        Connection conn = BDConnection.getConnection();
        String sql;

        //AQ VOU USAR A INSTANCIA PARA IDENTIFICAR O TIPO
        if (produto instanceof CaixaSomPortatil) {
            // SQL para atualizar CaixaDeSomPortatil
            sql = "UPDATE produto SET modelo = ?, preco = ?, bateria = ? WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, produto.getModelo());
            pst.setDouble(2, produto.getPreco());
            pst.setInt(3, ((CaixaSomPortatil) produto).getBateria());
            pst.setLong(4, produto.getId());
            pst.executeUpdate();

        } else if (produto instanceof CaixaSomResidencial) {
            // SQL para atualizar CaixaDeSomResidencial
            sql = "UPDATE produto SET modelo = ?, preco = ?, voltagem = ? WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, produto.getModelo());
            pst.setDouble(2, produto.getPreco());
            pst.setString(3, ((CaixaSomResidencial) produto).getVoltagem());
            pst.setLong(4, produto.getId());
            pst.executeUpdate();
        }
    }

    public void deleteProdutoById(Long id) throws SQLException {
        Connection conn = BDConnection.getConnection();
        String sql = "DELETE FROM produto WHERE id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setLong(1, id);
        pst.executeUpdate();
    }



}
