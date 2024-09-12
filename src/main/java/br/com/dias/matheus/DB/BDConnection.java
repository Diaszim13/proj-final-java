package br.com.dias.matheus.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConnection {


    private static BDConnection db;

    private Connection coon;
    private BDConnection() { Connection c = null;}

    public static Connection getConnection () throws SQLException {
        if(db == null)
        {
            db = new BDConnection();
            db.coon = DriverManager.getConnection("jdbc:mysql://localhost/db", "root", "");
        }
        return db.coon;
    }
}
