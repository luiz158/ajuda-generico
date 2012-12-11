/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import br.ajuda.generico.dao.ITemaDao;
import br.ajuda.generico.entities.Tema;
import br.ajuda.generico.jdbc.DaoFactory;

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

        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        ITemaDao temaDao = daoFactory.getTemaDao();
        Tema tema = temaDao.prepareQueryReturnSingleBean(new Tema(2L,"my girls"));
        System.out.println(tema);
        temaDao.commit();
        temaDao.disconnection();
    }
}
