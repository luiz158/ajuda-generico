/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

/**
 *
 * @author jacob
 */
public class TesteHSQLDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

//        Class.forName("org.hsqldb.jdbcDriver");
//        Connection conn = DriverManager.getConnection("jdbc:hsqldb:file:/home/jacob/BasesDeDados/HSQLDB/ajuda_generico");
//        conn.setAutoCommit(false);
//      //  conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//        Statement s = conn.createStatement();
//        int e = s.executeUpdate("insert into public.ag_tema(titulo_tema) values('Conexao Mysql2')");
//        conn.commit();
//        System.out.println("insert: "+e);
//        ResultSet r = s.executeQuery("select count(*) as c from public.ag_tema");
//        r.next();
//        System.out.println("count(): "+r.getInt("c"));
//  //      s.execute("shutdown");
//        s.close();
//        r.close();
//
//        conn.close();

//        DaoFactory daoFactory = DaoFactory.getDaoFactory(true, DaoFactory.HSQLDB);
//        Tema tema = daoFactory.executeQueryReturnSingleBean("select * from public.ag_tema where id_tema=6", Tema.class);
//        //Tema tema = new Tema();
//        tema.setDescricaoTema("alterando2...");
//
//        daoFactory.updatePrepare(tema);
//
//        daoFactory.commit();
//        daoFactory.disconnection();
    }
}
