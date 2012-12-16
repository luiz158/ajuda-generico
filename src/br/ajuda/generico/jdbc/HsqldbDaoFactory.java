/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ajuda.generico.jdbc;

import br.ajuda.generico.dao.GenericDao;
import br.ajuda.generico.jdbc.annotation.ManagerAnnotationEntities;
import br.ajuda.generico.util.BeanHelper;
import br.ajuda.generico.util.SqlUtil;
import br.com.jacob.util.AbstractMapa.Pares;
import br.com.jacob.util.IMapa;
import br.com.jacob.util.StringHelper;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jacob_lisboa
 */
public class HsqldbDaoFactory<T> implements GenericDao<T> {

    private Statement st;
    private static HsqldbDaoFactory connectionBD;
    private ResultSet rs;
    private PreparedStatement cps;
    private static final Logger log = Logger.getLogger(HsqldbDaoFactory.class.getName());
    private ManagerAnnotationEntities managerAnnotationEntities;

    public HsqldbDaoFactory() throws Exception {
        this(true);
    }

    public HsqldbDaoFactory(boolean b) throws Exception {
        if (b) {
            ConnectionDB.connect();
            managerAnnotationEntities = new ManagerAnnotationEntities();
        }
    }

    @Override
    public void reconnect() throws Exception {
        if (getConnection() != null) {
            if (!getConnection().isClosed()) {
                getConnection().close();
            }
            ConnectionDB.connect();
        } else {
            ConnectionDB.connect();
        }
    }

    @Override
    public void disconnection() throws Exception {
        try {
            if (getConnection() != null) {
                getConnection().close();
            }
        } catch (SQLException sql) {
            throw new Exception("Houve erro ao tentar desconectar:\n" + sql.getMessage());
        }
    }

    @Override
    public PreparedStatement prepare(String sql) throws Exception {
        cps = getConnection().prepareStatement(sql);
        return cps;
    }

    @Override
    public ResultSet executeQuery(String sql) throws Exception {
        st = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        rs = st.executeQuery(sql);
        return rs;
    }

    @Override
    public T executeQueryReturnSingleBean(String sql, Class<? extends T> typeClass) throws Exception {
        st = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet = st.executeQuery(sql);
        resultSet.next();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int nCols = rsmd.getColumnCount();
        Map<String, Object> beanMap = new HashMap<String, Object>();
        for (int i = 1; i <= nCols; i++) {
            String colName = rsmd.getColumnName(i);

            Object value = resultSet.getObject(colName);

            beanMap.put(SqlUtil.getNameAttributeBean(colName), value);
        }

        resultSet.close();

        return (T) BeanHelper.parseMapToBean(beanMap, typeClass);
    }

    @Override
    public T prepareQueryReturnSingleBean(T bean) throws Exception {
        return pQueryReturnSingleBean(bean, false);
    }

    @Override
    public T prepareQueryPorIdsReturnSingleBean(T bean) throws Exception {
        return pQueryReturnSingleBean(bean, true);
    }

    private T pQueryReturnSingleBean(T bean, boolean flag) throws Exception {
        StringBuilder sql = new StringBuilder("SELECT * FROM " + managerAnnotationEntities.getNomeTabela(bean));

        IMapa camposMap = flag ? managerAnnotationEntities.getConverterBeanParaMapIds(bean) : managerAnnotationEntities.getConverterBeanParaMap(bean);

        StringBuilder camposStr = new StringBuilder();

        List<Pares> pares = camposMap.list();
        for (Pares par : pares) {
            Object v =camposMap.getValue(par.getKey());
            if (v != null) {
                if(v instanceof String){
                    camposStr.append("lower(").append(par.getKey()).append(")").append("=? AND ");
                }else{
                    camposStr.append(par.getKey()).append("=? AND ");
                }
            }
        }

        sql.append(" WHERE ").append(StringHelper.substringBeforeLast(camposStr.toString(), "AND"));

        cps = getConnection().prepareStatement(sql.toString());

        IMapa somenteCamposMap = flag ? managerAnnotationEntities.getIdsCampos(bean) : managerAnnotationEntities.getCampos(bean);
        setCamposPrepareQuery(somenteCamposMap, bean);

        ResultSet resultSet = cps.executeQuery();
        Map<String, Object> beanMap = new HashMap<String, Object>();
        if (resultSet.next()) {
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int nCols = rsmd.getColumnCount();
            for (int i = 1; i <= nCols; i++) {
                String colName = rsmd.getColumnName(i);

                Object value = resultSet.getObject(colName);

                beanMap.put(SqlUtil.getNameAttributeBean(colName), value);
            }
        } else {
            if (resultSet != null) {
                resultSet.close();
            }
            return null;
        }

        resultSet.close();

        return (T) BeanHelper.parseMapToBean(beanMap, bean.getClass());
    }

    @Override
    public List cexecuteQuery(String sql) throws Exception {
        st = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        List lista = new ArrayList();

        ResultSet resultSet = st.executeQuery(sql);

        ResultSetMetaData rsmd = resultSet.getMetaData();
        int nCols = rsmd.getColumnCount();
        while (resultSet.next()) {
            Map<String, Object> beanMap = new HashMap<String, Object>();
            for (int i = 1; i <= nCols; i++) {
                String colName = rsmd.getColumnName(i);

                Object value = resultSet.getObject(colName);

                beanMap.put(SqlUtil.getNameAttributeBean(colName), value);
            }
            lista.add(beanMap);
        }
        resultSet.close();
        return lista;
    }

    @Override
    public void savePrepare(T bean) throws Exception {
        managerAnnotationEntities.analisarCamposObrig(bean);
        String nomeTabela = managerAnnotationEntities.getNomeTabela(bean);
        if(StringHelper.isBlank(nomeTabela)){
            throw new NullPointerException("Informe o nome da tabela na anotacao da classe: "+bean.getClass().getName());
        }
        String sql = (new StringBuilder()).append("INSERT INTO ").append(nomeTabela).append(" (").toString();

        IMapa nomeCamposMap = managerAnnotationEntities.getCamposSemIds(bean);
        List<Pares> pares = nomeCamposMap.list();
        StringBuilder camposStr = new StringBuilder();
        int nCampos = 0;
        for (Pares par : pares) {
            camposStr.append(par.getKey()).append(",");
            nCampos++;
        }

        sql += (new StringBuilder()).append(StringHelper.removeEnd(camposStr.toString(), ",")).append(") VALUES(").toString();

        StringBuilder valuesStr = new StringBuilder();
        for (int i = 0; i < nCampos; i++) {
            valuesStr.append("?,");
        }
        sql += (new StringBuilder()).append(StringHelper.removeEnd(valuesStr.toString(), ",")).append(")").toString();

        cps = getConnection().prepareStatement(sql);

        //excluir campos transitorios,ids e devolver um Mapa
        IMapa beanMap = managerAnnotationEntities.getExCamposTransitoriosEIds(bean);

        Iterator<String> keys = beanMap.keys();
        int pCampo = 1;

        while (keys.hasNext()) {
            String name = keys.next().toString();
            Object value = BeanHelper.getPropriedade(bean, name);

            if (value instanceof BigDecimal) {
                cps.setBigDecimal(pCampo++, value == null ? (BigDecimal) null : new BigDecimal(String.valueOf(value)));
            } else if (value instanceof Date) {
                cps.setDate(pCampo++, value == null ? (java.sql.Date) null : new java.sql.Date(((Date) value).getTime()));
            } else if (value instanceof Double) {
                cps.setDouble(pCampo++, value == null ? 0.0d : ((Double) value).doubleValue());
            } else if (value instanceof Integer) {
                cps.setInt(pCampo++, value == null ? 0 : ((Integer) value).intValue());
            } else if (value instanceof String) {
                cps.setString(pCampo++, value == null ? (String) null : String.valueOf(value));
            } else if (value instanceof Long) {
                cps.setLong(pCampo++, value == null ? 0L : ((Long) value).longValue());
            } else if (value instanceof Short) {
                cps.setShort(pCampo++, value == null ? 0 : ((Short) value).shortValue());
            } else if (value instanceof Boolean) {
                cps.setBoolean(pCampo++, value == null ? false : Boolean.valueOf(String.valueOf(value)).booleanValue());
            } else {
                cps.setObject(pCampo++, value);
            }
        }

        cps.execute();
    }

    @Override
    public void savePrepare(T bean, String[] camposExtras) throws Exception {

        String sql = (new StringBuilder()).append("INSERT INTO ").append(managerAnnotationEntities.getNomeTabela(bean)).append(" (").toString();

        IMapa nomeCamposMap = managerAnnotationEntities.getCamposSemIds(bean);
        List<Pares> pares = nomeCamposMap.list();
        StringBuilder camposStr = new StringBuilder();
        int nCampos = 1;
        for (Pares par : pares) {
            camposStr.append(par.getKey()).append(",");
            nCampos++;
        }

        sql += (new StringBuilder()).append(StringHelper.removeEnd(camposStr.toString(), ",")).append(") VALUES(").toString();

        StringBuilder valuesStr = new StringBuilder();
        for (int i = 0; i < nCampos; i++) {
            valuesStr.append("?,");
        }
        sql += (new StringBuilder()).append(StringHelper.removeEnd(valuesStr.toString(), ",")).append(")").toString();

        cps = getConnection().prepareStatement(sql);

        //excluir campos transitorios e devolver um Mapa
        IMapa beanMap = managerAnnotationEntities.getExCamposTransitorios(bean);

        Iterator<String> keys = beanMap.keys();
        int pCampo = 1;

        while (keys.hasNext()) {
            String name = keys.next().toString();
            Object value = BeanHelper.getPropriedade(bean, name);

            if (value instanceof BigDecimal) {
                cps.setBigDecimal(pCampo++, value == null ? (BigDecimal) null : new BigDecimal(String.valueOf(value)));
            } else if (value instanceof Date) {
                cps.setDate(pCampo++, value == null ? (java.sql.Date) null : new java.sql.Date(((Date) value).getTime()));
            } else if (value instanceof Double) {
                cps.setDouble(pCampo++, value == null ? 0.0d : ((Double) value).doubleValue());
            } else if (value instanceof Integer) {
                cps.setInt(pCampo++, value == null ? 0 : ((Integer) value).intValue());
            } else if (value instanceof String) {
                cps.setString(pCampo++, value == null ? (String) null : String.valueOf(value));
            } else if (value instanceof Long) {
                cps.setLong(pCampo++, value == null ? 0L : ((Long) value).longValue());
            } else if (value instanceof Short) {
                cps.setShort(pCampo++, value == null ? 0 : ((Short) value).shortValue());
            } else if (value instanceof Boolean) {
                cps.setBoolean(pCampo++, value == null ? false : Boolean.valueOf(String.valueOf(value)).booleanValue());
            } else {
                cps.setObject(pCampo++, value);
            }
        }

        if (camposExtras != null) {
            for (String campo : camposExtras) {
                cps.setObject(pCampo++, BeanHelper.getPropriedade(bean, campo));
            }
        }

        cps.execute();
    }

    @Override
    public void savePrepare(T bean, String sql, String[] camposExtras, String... excluirCampos) throws Exception {
        managerAnnotationEntities.analisarCamposObrig(bean);

        cps = getConnection().prepareStatement(sql);

        IMapa beanMap = BeanHelper.parseIMapa(bean);

        if (excluirCampos != null) {
            for (String ex : excluirCampos) {
                beanMap.remove(ex);
            }
        }

        Iterator<String> keys = beanMap.keys();
        int pCampo = 1;

        while (keys.hasNext()) {
            String name = keys.next().toString();
            Object value = BeanHelper.getPropriedade(bean, name);

            if (value instanceof BigDecimal) {
                cps.setBigDecimal(pCampo++, value == null ? (BigDecimal) null : new BigDecimal(String.valueOf(value)));
            } else if (value instanceof Date) {
                cps.setDate(pCampo++, value == null ? (java.sql.Date) null : new java.sql.Date(((Date) value).getTime()));
            } else if (value instanceof Double) {
                cps.setDouble(pCampo++, value == null ? 0.0d : ((Double) value).doubleValue());
            } else if (value instanceof Integer) {
                cps.setInt(pCampo++, value == null ? 0 : ((Integer) value).intValue());
            } else if (value instanceof String) {
                cps.setString(pCampo++, value == null ? (String) null : String.valueOf(value));
            } else if (value instanceof Long) {
                cps.setLong(pCampo++, value == null ? 0L : ((Long) value).longValue());
            } else if (value instanceof Short) {
                cps.setShort(pCampo++, value == null ? 0 : ((Short) value).shortValue());
            } else if (value instanceof Boolean) {
                cps.setBoolean(pCampo++, value == null ? false : Boolean.valueOf(String.valueOf(value)).booleanValue());
            } else {
                cps.setObject(pCampo++, value);
            }
        }

        if (camposExtras != null) {
            for (String campo : camposExtras) {
                cps.setObject(pCampo++, BeanHelper.getPropriedade(bean, campo));
            }
        }

        cps.execute();
    }

    @Override
    public void updatePrepare(T bean) throws Exception {

        managerAnnotationEntities.analisarCamposObrig(bean);
        String nomeTabela = managerAnnotationEntities.getNomeTabela(bean);
        if(StringHelper.isBlank(nomeTabela)){
            throw new NullPointerException("Informe o nome da tabela na anotacao da classe: "+bean.getClass().getName());
        }
        String sql = (new StringBuilder()).append("UPDATE ").append(nomeTabela).append(" SET ").toString();

        //obtenho os campos sem os ids da entidade mapeada
        IMapa nomeCamposMap = managerAnnotationEntities.getCamposSemIds(bean);
        List<Pares> pares = nomeCamposMap.list();
        StringBuilder camposStr = new StringBuilder();
        for (Pares par : pares) {
            camposStr.append(par.getKey()).append("=?,");
        }

        sql += (new StringBuilder()).append(StringHelper.removeEnd(camposStr.toString(), ",")).append(" WHERE ").toString();

        //obtenho somente os ids da entidade mapeada
        IMapa<String, String> idsCamposMap = managerAnnotationEntities.getIdsCampos(bean);
        List<Pares> idsPares = idsCamposMap.list();
        StringBuilder idsCamposStr = new StringBuilder();
        for (Pares ids : idsPares) {
            idsCamposStr.append(ids.getKey()).append("=? AND");
        }

        sql += (new StringBuilder()).append(StringHelper.removeEnd(idsCamposStr.toString(), "AND"));

        cps = getConnection().prepareStatement(sql);

        //excluir campos transitorios e devolver um Mapa
        IMapa<String, Object> beanMap = managerAnnotationEntities.getExCamposTransitoriosEIds(bean);

        Iterator<Integer> keys = beanMap.indexs();

        while (keys.hasNext()) {
            int index = keys.next().intValue();
            String name = beanMap.getKey(index);
            Object value = BeanHelper.getPropriedade(bean, name);

            if (value instanceof BigDecimal) {
                cps.setBigDecimal(index, value == null ? (BigDecimal) null : new BigDecimal(String.valueOf(value)));
            } else if (value instanceof Date) {
                cps.setDate(index, value == null ? (java.sql.Date) null : new java.sql.Date(((Date) value).getTime()));
            } else if (value instanceof Double) {
                cps.setDouble(index, value == null ? 0.0d : ((Double) value).doubleValue());
            } else if (value instanceof Integer) {
                cps.setInt(index, value == null ? 0 : ((Integer) value).intValue());
            } else if (value instanceof String) {
                cps.setString(index, value == null ? (String) null : String.valueOf(value));
            } else if (value instanceof Long) {
                cps.setLong(index, value == null ? 0L : ((Long) value).longValue());
            } else if (value instanceof Short) {
                cps.setShort(index, value == null ? 0 : ((Short) value).shortValue());
            } else if (value instanceof Boolean) {
                cps.setBoolean(index, value == null ? false : Boolean.valueOf(String.valueOf(value)).booleanValue());
            } else {
                cps.setObject(index, value);
            }
        }

        for (Pares ids : idsPares) {
            int pCampo = beanMap.getUltimoIndex();
            Object id = BeanHelper.getPropriedade(bean, (String) ids.getValue());
            cps.setObject(++pCampo, id);
        }

//        if (camposExtras != null) {
//            int pCampo = beanMap.getUltimoIndex();
//            for (String campo : camposExtras) {
//                cps.setObject(++pCampo, BeanHelper.getPropriedade(bean, campo));
//            }
//        }

        cps.execute();

    }

    @Override
    public void updatePrepare(Object bean, String sql, String[] camposExtras, String... excluirCampos) throws Exception {
        managerAnnotationEntities.analisarCamposObrig(bean);

        cps = getConnection().prepareStatement(sql);

        IMapa<String, Object> beanMap = BeanHelper.parseIMapa(bean, excluirCampos);

        Iterator<Integer> keys = beanMap.indexs();

        while (keys.hasNext()) {
            int index = keys.next().intValue();
            String name = beanMap.getKey(index);
            Object value = BeanHelper.getPropriedade(bean, name);

            if (value instanceof BigDecimal) {
                cps.setBigDecimal(index, value == null ? (BigDecimal) null : new BigDecimal(String.valueOf(value)));
            } else if (value instanceof Date) {
                cps.setDate(index, value == null ? (java.sql.Date) null : new java.sql.Date(((Date) value).getTime()));
            } else if (value instanceof Double) {
                cps.setDouble(index, value == null ? 0.0d : ((Double) value).doubleValue());
            } else if (value instanceof Integer) {
                cps.setInt(index, value == null ? 0 : ((Integer) value).intValue());
            } else if (value instanceof String) {
                cps.setString(index, value == null ? (String) null : String.valueOf(value));
            } else if (value instanceof Long) {
                cps.setLong(index, value == null ? 0L : ((Long) value).longValue());
            } else if (value instanceof Short) {
                cps.setShort(index, value == null ? 0 : ((Short) value).shortValue());
            } else if (value instanceof Boolean) {
                cps.setBoolean(index, value == null ? false : Boolean.valueOf(String.valueOf(value)).booleanValue());
            } else {
                cps.setObject(index, value);
            }
        }

        if (camposExtras != null) {
            int pCampo = beanMap.getUltimoIndex();
            for (String campo : camposExtras) {
                cps.setObject(++pCampo, BeanHelper.getPropriedade(bean, campo));
            }
        }

        cps.execute();

    }

    @Override
    public void rollback() throws Exception {
        if (getConnection() != null) {
            getConnection().rollback();
        }
    }

    @Override
    public void commit() throws Exception {
        String savepointName = "commit()" + hashCode();
        Savepoint savePoint = getConnection().setSavepoint(savepointName);
        if (getConnection() != null) {
            try {
                getConnection().commit();
                disconnection();
                log.log(Level.INFO, "Executando commit...");
            } catch (Exception ex) {
                log.log(Level.SEVERE, "Executando rollback...", ex);
                getConnection().rollback(savePoint);
                throw new Exception("commit(): " + ex.getMessage());
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (cps != null) {
                    cps.close();
                }

                if (st != null) {
                    st.close();
                }
            }
        }
    }

    /**
     * @return the connection
     */
    @Override
    public Connection getConnection() throws Exception {
        return ConnectionDB.getConnection();
    }

    public static HsqldbDaoFactory getInstance(boolean b) throws Exception {
        if (connectionBD == null) {
            connectionBD = new HsqldbDaoFactory(b);
        }
        return connectionBD;
    }

    public static HsqldbDaoFactory getInstance() throws Exception {
        if (connectionBD == null) {
            connectionBD = new HsqldbDaoFactory();
        }
        return connectionBD;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HsqldbDaoFactory other = (HsqldbDaoFactory) obj;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    private void setCamposPrepareQuery(IMapa map, T bean) throws SQLException {
        Iterator<String> keys = map.keys();
        int pCampo = 1;

        while (keys.hasNext()) {
            String key = keys.next();
            Object value = BeanHelper.getPropriedade(bean, (String) map.getValue(key));
            if (value == null) {
                continue;
            }
            if (value instanceof BigDecimal) {
                cps.setBigDecimal(pCampo++, (BigDecimal) value);
            } else if (value instanceof Date) {
                cps.setDate(pCampo++, new java.sql.Date(((Date) value).getTime()));
            } else if (value instanceof Double) {
                cps.setDouble(pCampo++, ((Double) value).doubleValue());
            } else if (value instanceof Integer) {
                cps.setInt(pCampo++, ((Integer) value).intValue());
            } else if (value instanceof String) {
                cps.setString(pCampo++, ((String) value).toLowerCase());
            } else if (value instanceof Long) {
                cps.setLong(pCampo++, ((Long) value).longValue());
            } else if (value instanceof Short) {
                cps.setShort(pCampo++, ((Short) value).shortValue());
            } else if (value instanceof Boolean) {
                cps.setBoolean(pCampo++, (Boolean) value);
            } else {
                cps.setObject(pCampo++, value);
            }
        }
    }
}
