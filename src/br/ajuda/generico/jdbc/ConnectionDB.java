/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ajuda.generico.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jacob_lisboa
 */
public class ConnectionDB {

    private static Connection connection;
    private final static String username = "SA";
    private final static String password = "";
    private final static String url = "jdbc:hsqldb:file:/home/jacob/BasesDeDados/HSQLDB/ajuda_generico";
    private final static String driver = "org.hsqldb.jdbcDriver";
    private static final Logger log = Logger.getLogger(ConnectionDB.class.getName());

    
    public static void connect() throws Exception {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName(driver);
                log.log(Level.INFO, "Carregando driver hsqldb...");
            } catch (ClassNotFoundException ex) {
                throw new Exception("Driver hsqldb Não encontrado!");
            }
            try {
                connection = DriverManager.getConnection(url, username, password);
                //this.connection.setClientInfo("AWJ", "Aplicacao Web");
                connection.setAutoCommit(false);
                connection.setReadOnly(false);
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
                //System.out.println("Setando application_name p/ 'AWJ' ...");
                //log.log(Level.INFO, "Setando application_name p/ 'AWJ' ...");
                //PreparedStatement p = connection.prepareStatement("SET application_name TO 'AWJ'");
                //p.execute();
                //p.close();
                //System.out.println("Conectando ao banco clinica_test...");
                //log.log(Level.INFO, "Setando application_name p/ 'AWJ' ...");
            } catch (SQLException sql) {
                throw new Exception("Houve um erro na configuracao dos parametros "
                        + "ou base de dados desligada:\n" + sql.getMessage());
            }
        }
    }

    public static Connection getConnection() throws Exception {
        if (connection == null || connection.isClosed()) {
            connect();
            return connection;
        }
        return connection;
    }
}
