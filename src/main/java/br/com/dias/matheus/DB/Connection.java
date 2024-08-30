package br.com.dias.matheus.DB;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {


    private static Connection db;

    private Connection coon;
    private Connection() { Connection c = null;}

    public static Connection getConnection () throws SQLException {
        if(db == null)
        {
            db = new Connection();
            db.coon = (Connection) DriverManager.getConnection("jdbc:mys/ql://localhost/db", "root", "");
        }
        return db.coon;
    }
}
