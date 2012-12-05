/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ajuda.generico.jdbc;

import br.ajuda.generico.dao.ITemaDao;

/**
 *
 * @author jacob_lisboa
 */
public abstract class DaoFactory {

//    public final static int POSTGRESQL = 1, MYSQL = 2, HSQLDB = 3;

    public abstract ITemaDao getTemaDao() throws Exception;

    public static DaoFactory getDaoFactory(){
        return new DaoFactoryImpl();
    }
//    public static DaoFactory getDaoFactory(boolean b, int escolhaFabrica) throws Exception {
//
//        switch (escolhaFabrica) {
//            case POSTGRESQL:
//                return null;
//            case MYSQL:
//                return null;
//            case HSQLDB:
//                return new HsqldbDaoFactory(b);
//            default:
//                return null;
//        }
//    }
}
