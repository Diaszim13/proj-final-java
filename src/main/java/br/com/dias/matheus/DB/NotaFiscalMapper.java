package br.com.dias.matheus.DB;

import br.com.dias.matheus.classes.cliente.Cliente;
import br.com.dias.matheus.classes.cliente.PessoaFisica;
import br.com.dias.matheus.classes.cliente.PessoaJuridica;
import br.com.dias.matheus.classes.compra.NotaFiscal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotaFiscalMapper {

    public List<NotaFiscal> getAllNotasFiscais() throws SQLException {
        List<NotaFiscal> notasFiscais = new ArrayList<>();
        Connection conn = BDConnection.getConnection();
        String sql = "SELECT nf.id, nf.numero, nf.cliente_id, c.nome, c.tipo, c.cpf, c.cnpj " +
                "FROM nota_fiscal nf " +
                "JOIN cliente c ON nf.cliente_id = c.id";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet res = pst.executeQuery();

        while (res.next()) {
            NotaFiscal notaFiscal = new NotaFiscal();
            notaFiscal.setId(res.getLong("id"));
            notaFiscal.setNumero(res.getInt("numero"));

            // Criar e configurar o cliente associado
            Cliente cliente;
            String tipo = res.getString("tipo");
            if ("F".equals(tipo)) {
                cliente = new PessoaFisica();
                ((PessoaFisica) cliente).setCpf(res.getString("cpf"));
            } else {
                cliente = new PessoaJuridica();
                ((PessoaJuridica) cliente).setCnpj(res.getString("cnpj"));
            }
            cliente.setId(res.getLong("cliente_id"));
            cliente.setNome(res.getString("nome"));

            notaFiscal.setCliente(cliente);
            notasFiscais.add(notaFiscal);
        }

        return notasFiscais;
    }


    public NotaFiscal getNotaFiscalById(Long id) throws SQLException {
        Connection conn = BDConnection.getConnection();
        String sql = "SELECT * FROM nota_fiscal WHERE id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setLong(1, id);
        ResultSet res = pst.executeQuery();

        if (res.next()) {
            NotaFiscal notaFiscal = new NotaFiscal();
            notaFiscal.setId(res.getLong("id"));
            notaFiscal.setNumero(res.getInt("numero"));

            // Buscar o cliente associado
            Long clienteId = res.getLong("cliente_id");
            Cliente cliente = ClienteMapper.getClienteById(clienteId); // Supõe que você já tenha o método getClienteById
            notaFiscal.setCliente(cliente);

            return notaFiscal;
        }
        return null; // Retorna null se não encontrar a nota fiscal
    }

    public void createNotaFiscal(NotaFiscal notaFiscal) throws SQLException {
        Connection conn = BDConnection.getConnection();
        String sql = "INSERT INTO nota_fiscal (numero, cliente_id) VALUES (?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, notaFiscal.getNumero());
        pst.setLong(2, notaFiscal.getCliente().getId());
        pst.executeUpdate();
    }

    public void updateNotaFiscal(NotaFiscal notaFiscal) throws SQLException {
        Connection conn = BDConnection.getConnection();
        String sql = "UPDATE nota_fiscal SET numero = ?, cliente_id = ? WHERE id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, notaFiscal.getNumero());
        pst.setLong(2, notaFiscal.getCliente().getId());
        pst.setLong(3, notaFiscal.getId());
        pst.executeUpdate();
    }

    public void deleteNotaFiscalById(Long id) throws SQLException {
        Connection conn = BDConnection.getConnection();
        String sql = "DELETE FROM nota_fiscal WHERE id = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setLong(1, id);
        pst.executeUpdate();
    }

    public String[] formatNotasFiscaisForSelection(List<NotaFiscal> notasFiscais) {
        String[] options = new String[notasFiscais.size()];

        for (int i = 0; i < notasFiscais.size(); i++) {
            NotaFiscal notaFiscal = notasFiscais.get(i);
            Cliente cliente = notaFiscal.getCliente();
            String descricao = "ID: " + notaFiscal.getId() +
                    " | Número: " + notaFiscal.getNumero() +
                    " | Cliente: " + cliente.getNome() +
                    " | Tipo: " + (cliente instanceof PessoaFisica ? "Pessoa Física" : "Pessoa Jurídica");

            options[i] = descricao;
        }

        return options;
    }

    public String formatNotasFiscais(List<NotaFiscal> notasFiscais) {
        StringBuilder sb = new StringBuilder();

        for (NotaFiscal notaFiscal : notasFiscais) {
            sb.append("ID: ").append(notaFiscal.getId()).append("\n");
            sb.append("Número: ").append(notaFiscal.getNumero()).append("\n");

            Cliente cliente = notaFiscal.getCliente();
            if (cliente != null) {
                sb.append("Cliente: ").append(cliente.getNome()).append("\n");
                if (cliente instanceof PessoaFisica) {
                    sb.append("CPF: ").append(((PessoaFisica) cliente).getCpf()).append("\n");
                } else if (cliente instanceof PessoaJuridica) {
                    sb.append("CNPJ: ").append(((PessoaJuridica) cliente).getCnpj()).append("\n");
                }
            } else {
                sb.append("Cliente: Não disponível\n");
            }

            sb.append("-------------------------\n");
        }

        return sb.toString();
    }





}
