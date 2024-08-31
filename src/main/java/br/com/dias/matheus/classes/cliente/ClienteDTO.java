package br.com.dias.matheus.classes.cliente;

import br.com.dias.matheus.DB.Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDTO {

	public ClienteDTO() {}

	public void saveCliente(Cliente cliente) throws SQLException
	{
		Connection coon = Connection.getConnection();
		PreparedStatement pst = coon.prepare()

	}

}
