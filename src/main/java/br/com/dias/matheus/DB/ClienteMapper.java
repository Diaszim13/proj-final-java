package br.com.dias.matheus.DB;

import br.com.dias.matheus.classes.cliente.Cliente;
import br.com.dias.matheus.classes.cliente.PessoaFisica;
import br.com.dias.matheus.classes.cliente.PessoaJuridica;
import br.com.dias.matheus.classes.cliente.TipoPessoa;
import br.com.dias.matheus.classes.produtos.TipoProduto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteMapper {
   /* public void saveCliente(Cliente cliente) throws SQLException
    {
        Connection coon = BDConnection.getConnection();
        PreparedStatement pstmtCliente = coon.prepareStatement("insert into Cliente values (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        PreparedStatement pstmtPessoaFisica = coon.prepareStatement("INSERT INTO PessoaFisica (id, cpf) VALUES (?, ?)");;
        ResultSet rs = null;

        // Inserir na tabela Cliente (Superclasse)
        pstmtCliente.setLong(1, 1);
        pstmtCliente.setString(2, cliente.getNome());
        pstmtCliente.setDouble(3, cliente.getSaldo());
        pstmtCliente.executeUpdate();


        // Executar o insert
        int affectedRows = pstmtCliente.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Falha ao inserir Cliente, nenhuma linha afetada.");
        }

        // 3. Recuperar o ID gerado para o Cliente
        rs = pstmtCliente.getGeneratedKeys();
        Long clienteId = null;
        if (rs.next()) {
            clienteId = rs.getLong(1);
        } else {
            throw new SQLException("Falha ao inserir Cliente, ID não obtido.");
        }

        // 4. Inserir na tabela PessoaFisica (Subclasse) com o ID do Cliente
        pstmtPessoaFisica.setLong(1, clienteId);        // Usar o ID gerado para Cliente
        pstmtPessoaFisica.setString(2, "12345678900");  // Definir o CPF

        // Executar o insert na tabela PessoaFisica
        pstmtPessoaFisica.executeUpdate();


        pstmtCliente.close();
        pstmtPessoaFisica.close();
        coon.close();
    }
    */
   public void saveCliente(Cliente cliente) throws SQLException {
       Connection conn = BDConnection.getConnection();
       String sql;

       if (cliente instanceof PessoaFisica) {
           // SQL para inserir ou atualizar PessoaFisica
           if (cliente.getId() == null) {
               // Inserção
               sql = "INSERT INTO cliente (nome, tipo, cpf,saldo) VALUES (?, 'F', ?, ?)";
               PreparedStatement pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
               pst.setString(1, cliente.getNome());
               pst.setString(2, ((PessoaFisica) cliente).getCpf());
               pst.setDouble(3, cliente.getSaldo());
               pst.executeUpdate();

               // Obter o ID gerado
               ResultSet rs = pst.getGeneratedKeys();
               if (rs.next()) {
                   cliente.setId(rs.getLong(1));
               }
           } else {
               // Atualização
               sql = "UPDATE cliente SET nome = ?, cpf = ? WHERE id = ?";
               PreparedStatement pst = conn.prepareStatement(sql);
               pst.setString(1, cliente.getNome());
               pst.setString(2, ((PessoaFisica) cliente).getCpf());
               pst.setLong(3, cliente.getId());

               pst.executeUpdate();
           }

       } else if (cliente instanceof PessoaJuridica) {
           // SQL para inserir ou atualizar PessoaJuridica
           if (cliente.getId() == null) {
               // Inserção
               sql = "INSERT INTO cliente (nome, tipo, cnpj,saldo) VALUES (?, 'J', ?, ?)";
               PreparedStatement pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
               pst.setString(1, cliente.getNome());
               pst.setString(2, ((PessoaJuridica) cliente).getCnpj());
               pst.setDouble(3, cliente.getSaldo());

               pst.executeUpdate();

               // Obter o ID gerado
               ResultSet rs = pst.getGeneratedKeys();
               if (rs.next()) {
                   cliente.setId(rs.getLong(1));
               }
           } else {
               // Atualização
               sql = "UPDATE cliente SET nome = ?, cnpj = ? WHERE id = ?";
               PreparedStatement pst = conn.prepareStatement(sql);
               pst.setString(1, cliente.getNome());
               pst.setString(2, ((PessoaJuridica) cliente).getCnpj());
               pst.setLong(3, cliente.getId());
               pst.executeUpdate();
           }
       }
   }

    public List<Cliente> listCliente(Cliente cliente) throws SQLException
    {
        Connection coon = BDConnection.getConnection();
        PreparedStatement pst = coon.prepareStatement("SELECT * FROM CLIENTES where id >?");
        List<Cliente> clientes = new ArrayList<>();
        ResultSet res = pst.executeQuery();

        while (res.next())
        {
            clientes.add(res.getRow(), cliente);
        }

        return clientes;
    }

    public static Cliente getClienteById(Long id) throws SQLException {
        Connection conn = BDConnection.getConnection();
        String sql = "SELECT * FROM cliente WHERE id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setLong(1, id);
        ResultSet res = pst.executeQuery();

        if (res.next()) {
            //AQ USA O CAMPO TIPO PARA DIFERENCIAR
            String codigoTipo = res.getString("tipo");
            TipoPessoa tipoCliente = TipoPessoa.fromCodigo(codigoTipo);


            if (tipoCliente.equals(TipoPessoa.FISICA)) {
                // Mapeando para PessoaFisica
                PessoaFisica pf = new PessoaFisica();
                pf.setId(res.getLong("id"));
                pf.setNome(res.getString("nome"));
                pf.setCpf(res.getString("cpf"));
                return pf;
            } else if (tipoCliente.equals(TipoPessoa.JURIDICA)) {
                // Mapeando para PessoaJuridica
                PessoaJuridica pj = new PessoaJuridica();
                pj.setId(res.getLong("id"));
                pj.setNome(res.getString("nome"));
                pj.setCnpj(res.getString("cnpj"));
                return pj;
            }
        }

        // Retorna null se não encontrar o cliente com o ID fornecido
        return null;
    }

    public static List<Cliente> getAllClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        Connection conn = BDConnection.getConnection();
        String sql = "SELECT * FROM cliente";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet res = pst.executeQuery();

        while (res.next()) {
            Cliente cliente;
            String tipo = res.getString("tipo");

            if ("F".equals(tipo)) {
                cliente = new PessoaFisica();
                ((PessoaFisica) cliente).setCpf(res.getString("cpf"));
            } else {
                cliente = new PessoaJuridica();
                ((PessoaJuridica) cliente).setCnpj(res.getString("cnpj"));
            }

            cliente.setId(res.getLong("id"));
            cliente.setNome(res.getString("nome"));
            cliente.setSaldo(res.getDouble("saldo"));
            clientes.add(cliente);
        }

        return clientes;
    }

    public String formatClientes(List<Cliente> clientes) {
        StringBuilder sb = new StringBuilder();
        for (Cliente cliente : clientes) {
            sb.append("ID: ").append(cliente.getId()).append("\n");
            sb.append("Nome: ").append(cliente.getNome()).append("\n");
            if (cliente instanceof PessoaFisica) {
                sb.append("CPF: ").append(((PessoaFisica) cliente).getCpf()).append("\n");
            } else if (cliente instanceof PessoaJuridica) {
                sb.append("CNPJ: ").append(((PessoaJuridica) cliente).getCnpj()).append("\n");
            }
            sb.append("-------------------------\n");
        }
        return sb.toString();
    }

    public String[] formatClientesForSelection(List<Cliente> clientes) {
        String[] options = new String[clientes.size()];

        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            String descricao = "ID: " + cliente.getId() + " | Nome: " + cliente.getNome();
            if (cliente instanceof PessoaFisica) {
                descricao += " | CPF: " + ((PessoaFisica) cliente).getCpf();
            } else if (cliente instanceof PessoaJuridica) {
                descricao += " | CNPJ: " + ((PessoaJuridica) cliente).getCnpj();
            }
            options[i] = descricao;
        }

        return options;
    }



    public void updateCliente(Cliente cliente) throws SQLException {
        Connection conn = BDConnection.getConnection();
        String sql;

        //AQ vou validar se é uma pessoa fisica ou juridica vendo pela instancia que ele representa
        if (cliente instanceof PessoaFisica) {
            // SQL para atualizar PessoaFisica
            sql = "UPDATE cliente SET nome = ?, cpf = ? WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, cliente.getNome());
            pst.setString(3, ((PessoaFisica) cliente).getCpf());
            pst.setLong(4, cliente.getId());
            pst.executeUpdate();

        } else if (cliente instanceof PessoaJuridica) {
            sql = "UPDATE cliente SET nome = ?, cnpj = ? WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, cliente.getNome());
            pst.setString(3, ((PessoaJuridica) cliente).getCnpj());
            pst.setLong(4, cliente.getId());
            pst.executeUpdate();
        }
    }

    public void deleteClienteById(Long id) throws SQLException {
        Connection conn = BDConnection.getConnection();
        String sql = "DELETE FROM cliente WHERE id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setLong(1, id);
        pst.executeUpdate();
    }
}
