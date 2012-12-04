/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ajuda.generico.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author jacob
 */
public interface GenericDao<T> extends Serializable {

    public abstract PreparedStatement prepare(String sql) throws Exception;

    public abstract ResultSet executeQuery(String sql) throws Exception;

    public abstract T executeQueryReturnSingleBean(String sql, Class<? extends T> typeClass) throws Exception;

    public abstract List cexecuteQuery(String sql) throws Exception;

    public abstract void savePrepare(T bean) throws Exception;

    public abstract void savePrepare(T bean, String[] camposExtras) throws Exception;

    public abstract void savePrepare(T bean, String sql, String[] camposExtras, String... excluirCampos) throws Exception;

    public abstract void updatePrepare(Object bean, String sql, String[] camposExtras, String... excluirCampos) throws Exception;

    public abstract void updatePrepare(T bean) throws Exception;

    public abstract Connection getConnection() throws Exception;

    public abstract void commit() throws Exception;

    public abstract void rollback() throws Exception;

    public abstract void disconnection() throws Exception;

    public abstract void reconnect() throws Exception;
}
