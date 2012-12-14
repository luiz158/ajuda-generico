/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ajuda.generico.util;

import br.com.jacob.util.BeanHelper;
import br.com.jacob.util.StringHelper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jacob_lisboa
 */
public class SqlUtil {

    public static String a(String a) {
        return "'" + a + "'";
    }

    public static String getNameAttributeBean(String colName) {
        if (StringHelper.isBlank(colName)) {
            throw new NullPointerException("coluna do banco nula ou em branco para retirar o underline.");
        }
        colName = colName.toLowerCase();
        if (colName.indexOf("_") == -1) {
            return colName;
        }
        String[] s = colName.split("_");
        String s1 = s[0];
        String s2 = StringHelper.capitalize(s[1]);
        return s1 + s2;
    }

    public static List parseListMapToListBean(List<Map<String, Object>> listMap, Class typeClass) throws Exception {
        List beanList = new ArrayList();
        for (Map<String, Object> map : listMap) {
            Object bean = BeanHelper.parseMapToBean(map, typeClass);
            beanList.add(bean);
        }
        return beanList;
    }

    public static <T> T converterResultSetParaBean(PreparedStatement p, ResultSet rs, Class<?> classe) throws Exception {
        ResultSetMetaData rsmd = p.getMetaData();
        T bean = (T) BeanHelper.newInstance(classe);
        for (int c = 1; c <= rsmd.getColumnCount(); c++) {
            String nomeColuna = rsmd.getColumnName(c);
            BeanHelper.setPropriedade(bean, rs.getObject(nomeColuna), getNameAttributeBean(nomeColuna));
        }
        return bean;
    }
}
