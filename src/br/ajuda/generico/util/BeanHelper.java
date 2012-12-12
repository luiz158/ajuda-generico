/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ajuda.generico.util;

import br.com.jacob.util.IMapa;
import br.com.jacob.util.Mapa;
import java.beans.IntrospectionException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import jfun.util.beans.Bean;
import jfun.util.beans.NoSuchPropertyException;
import jfun.util.beans.PropertyNotReadableException;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author jacob_lisboa
 */
public class BeanHelper extends BeanUtils {

    /**
     * busca o nome do atributo no bean
     * @param bean objeto a ser buscado o nome do atributo.
     * @param buscaNome nome do campo requerido.
     * @return caso exista o nome retorna true, senão false.
     */
    public static boolean isAtributoContemBean(Object bean, String buscaNome) {
        boolean r = false;
        
        Field[] fields = getAllFields(bean.getClass());
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getName().equals(buscaNome)) {
                r = true;
                break;
            }
        }
        return r;
    }

    public static Field[] getAllFields(Class<?> c){
        List<Field> fList = new ArrayList();
        addDeclaredAndInheritedFields(c, fList);
        return fList.toArray(new Field[fList.size()]);
    }

    /**
     * obtem todos os metodos declarados ate os herdados
     * @param c
     * @param fields
     */
    public static void addDeclaredAndInheritedFields(Class<?> c, Collection<Field> fields) {
        fields.addAll(Arrays.asList(c.getDeclaredFields()));
        Class<?> superClass = c.getSuperclass();
        if (superClass != null) {
            addDeclaredAndInheritedFields(superClass, fields);
        }
    }

    public static <T> Map<String, Object> parseListBeanToMap(List<T> listBeans) {
        Map<String, Object> mapBeans = new HashMap<String, Object>();
        for (T bean : listBeans) {
            Map<String, Object> map = parseMap(bean);
            Set<String> keys = map.keySet();
            for (String key : keys) {
                mapBeans.put(key, BeanHelper.getPropriedade(bean, key));
            }
        }
        return mapBeans;
    }

    public static <T> Map<String, Map<String, Object>> parseListBeanToMapCustomizado(List<T> listBeans) {
        Map<String, Map<String, Object>> mapBeans = new HashMap<String, Map<String, Object>>();
        int k = 0;
        for (T bean : listBeans) {
            mapBeans.put(bean.getClass().getSimpleName() + (k++), parseMap(bean));
        }
        return mapBeans;
    }

    public static boolean isTodosCamposNulos(Object bean, String... excluirCampos) {
        if (bean == null) {
            return false;
        }
        Map<String, Object> props = null;
        try {
            props = describe(bean);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BeanHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(BeanHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(BeanHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (String key : excluirCampos) {
            props.remove(key);
        }
        boolean flag = true;
        for (String key : props.keySet()) {
            Object value = props.get(key);
            if (isNotNullGeneric(value)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static void setPropriedade(Object bean, Object value, String name) {
        // throws IntrospectionException, NoSuchPropertyException, PropertyNotWritableException, InvocationTargetException, IllegalAccessException

        try {
            Bean.instance(bean).setProperty(name, value);
        } catch (Exception ex) {
            //ignorar
        }
    }

    public static <T> boolean isCampoExiste(String campo, T bean) {
        try {
            bean.getClass().getDeclaredField(campo).isAccessible();
            return true;
        } catch (NoSuchFieldException ex) {
            return false;
        } catch (NullPointerException ex) {
            return false;
        }
    }

    public static Object getPropriedade(Object clazz, String name) {
        try {
            try {
                return Bean.instance(clazz).getProperty(name);
            } catch (IntrospectionException ex) {
                //Logger.getLogger(BeanHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchPropertyException ex) {
            //Logger.getLogger(BeanHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PropertyNotReadableException ex) {
            // Logger.getLogger(BeanHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            // Logger.getLogger(BeanHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            //Logger.getLogger(BeanHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
        }
        return null;
    }

    public static boolean isNotNullGeneric(Object value) {

        if (value == null || value.equals("null")) {
            return false;
        }
        Class<?> fieldType = value.getClass();
        if (fieldType.isPrimitive()) {

            return NumberHelper.isNotNull(value);

        } else if (fieldType.isAssignableFrom(String.class) || value instanceof String) {

            return StringHelper.isNotBlank(value.toString().trim());

        } else if (fieldType.isAssignableFrom(Date.class) || value instanceof Date) {

            return StringHelper.isNotNull((Date) value);

        } else if (fieldType.isAssignableFrom(Collection.class)) {
        } else if (fieldType.isAssignableFrom(Array.class)) {
        } else if (fieldType.isAssignableFrom(Double.class) || value instanceof Double) {

            return NumberHelper.isNotNull((Double) value);

        } else if (fieldType.isAssignableFrom(Integer.class) || value instanceof Integer) {

            return NumberHelper.isNotNull((Integer) value);

        } else if (fieldType.isAssignableFrom(Float.class) || value instanceof Float) {

            return NumberHelper.isNotNull((Float) value);

        } else if (fieldType.isAssignableFrom(Short.class) || value instanceof Short) {

            return NumberHelper.isNotNull((Short) value);

        } else if (fieldType.isAssignableFrom(BigInteger.class) || value instanceof BigInteger) {

            return NumberHelper.isNotNull((BigInteger) value);

        } else if (fieldType.isAssignableFrom(Long.class) || value instanceof Long) {

            return NumberHelper.isNotNull((Long) value);

        } else if (fieldType.isAssignableFrom(BigDecimal.class) || value instanceof BigDecimal) {
            return NumberHelper.isNotNull((BigDecimal) value);
        }
        try {
            ((Object) value).toString().trim();
        } catch (NullPointerException ex) {
            return false;
        }
        return value != null;
    }

    public static Map parseMap(Object bean) {
        Map<String, Object> beanMap = new HashMap<String, Object>();
        if (bean == null) {
            return beanMap;
        }
        Class clazz = bean.getClass();
        Field[] fields = BeanHelper.getAllFields(clazz);
        for (Field field : fields) {
            String name = field.getName();
            field.setAccessible(true);
            Class type = field.getClass();
            Object value = getPropriedade(bean, field.getName());
            if (type.isAssignableFrom(BigDecimal.class) || value instanceof BigDecimal) {
                beanMap.put(name, (BigDecimal) value);
            } else if (type.isAssignableFrom(Date.class) || value instanceof Date) {
                beanMap.put(name, (Date) value);
            } else if (type.isAssignableFrom(Double.class) || value instanceof Double) {
                beanMap.put(name, (Double) value);
            } else if (type.isAssignableFrom(Integer.class) || value instanceof Integer) {
                beanMap.put(name, value == null ? null : (Integer) value);
            } else if (type.isAssignableFrom(String.class) || value instanceof String) {
                beanMap.put(name, value == null ? null : String.valueOf(value));
            } else if (type.isAssignableFrom(Long.class) || value instanceof Long) {
                beanMap.put(name, (Long) value);
            } else if (type.isAssignableFrom(Short.class) || value instanceof Short) {
                beanMap.put(name, (Short) value);
            } else if (type.isAssignableFrom(Boolean.class) || value instanceof Boolean) {
                beanMap.put(name, Boolean.valueOf(String.valueOf(value)));
            } else {
                beanMap.put(name, value);
            }

        }
        return beanMap;
    }

    public static IMapa parseIMapa(Object bean) {
        IMapa<String, Object> beanMap = new Mapa<String, Object>();
        if (bean == null) {
            return beanMap;
        }
        //inicializando indice com 1
        beanMap.setIndex(1);
        Class clazz = bean.getClass();
        Field[] fields = BeanHelper.getAllFields(clazz);
        for (Field field : fields) {
            String name = field.getName();
            field.setAccessible(true);
            Class type = field.getClass();
            Object value = getPropriedade(bean, field.getName());
            if (type.isAssignableFrom(BigDecimal.class) || value instanceof BigDecimal) {
                beanMap.adicionar(name, (BigDecimal) value);
            } else if (type.isAssignableFrom(Date.class) || value instanceof Date) {
                beanMap.adicionar(name, (Date) value);
            } else if (type.isAssignableFrom(Double.class) || value instanceof Double) {
                beanMap.adicionar(name, (Double) value);
            } else if (type.isAssignableFrom(Integer.class) || value instanceof Integer) {
                beanMap.adicionar(name, value == null ? null : (Integer) value);
            } else if (type.isAssignableFrom(String.class) || value instanceof String) {
                beanMap.adicionar(name, value == null ? null : String.valueOf(value));
            } else if (type.isAssignableFrom(Long.class) || value instanceof Long) {
                beanMap.adicionar(name, (Long) value);
            } else if (type.isAssignableFrom(Short.class) || value instanceof Short) {
                beanMap.adicionar(name, (Short) value);
            } else if (type.isAssignableFrom(Boolean.class) || value instanceof Boolean) {
                beanMap.adicionar(name, Boolean.valueOf(String.valueOf(value)));
            } else {
                beanMap.adicionar(name, value);
            }

        }
        return beanMap;
    }

    public static IMapa parseIMapa(Object bean, String... excluirCampos) {
        IMapa<String, Object> beanMap = new Mapa<String, Object>();
        if (bean == null) {
            return beanMap;
        }
        //inicializando indice com 1
        beanMap.setIndex(1);
        Class clazz = bean.getClass();
        Field[] fields = BeanHelper.getAllFields(clazz);
        for (Field field : fields) {

            String name = field.getName();

            if (Collections.frequency(Arrays.asList(excluirCampos), name) > 0) {
                continue;
            }

            field.setAccessible(true);
            Class type = field.getClass();
            Object value = getPropriedade(bean, field.getName());
            if (type.isAssignableFrom(BigDecimal.class) || value instanceof BigDecimal) {
                beanMap.adicionar(name, (BigDecimal) value);
            } else if (type.isAssignableFrom(Date.class) || value instanceof Date) {
                beanMap.adicionar(name, (Date) value);
            } else if (type.isAssignableFrom(Double.class) || value instanceof Double) {
                beanMap.adicionar(name, (Double) value);
            } else if (type.isAssignableFrom(Integer.class) || value instanceof Integer) {
                beanMap.adicionar(name, value == null ? null : (Integer) value);
            } else if (type.isAssignableFrom(String.class) || value instanceof String) {
                beanMap.adicionar(name, value == null ? null : String.valueOf(value));
            } else if (type.isAssignableFrom(Long.class) || value instanceof Long) {
                beanMap.adicionar(name, (Long) value);
            } else if (type.isAssignableFrom(Short.class) || value instanceof Short) {
                beanMap.adicionar(name, (Short) value);
            } else if (type.isAssignableFrom(Boolean.class) || value instanceof Boolean) {
                beanMap.adicionar(name, Boolean.valueOf(String.valueOf(value)));
            } else {
                beanMap.adicionar(name, value);
            }

        }
        return beanMap;
    }

    public static void copiarPropriedade(Object origem, Object destino) throws Exception {
        if (origem == null) {
            return;
        }
        Map<String, Object> origemMap = parseMap(origem);
        Set<String> keys = origemMap.keySet();
        for (String key : keys) {
            Object value = origemMap.get(key);

            try {
                setPropriedade(destino, value, key);
            } catch (Exception ex) {
            }
        }
    }

    public static void copiarPropriedade2(Object origem, Object destino) throws Exception {
        if (origem == null) {
            return;
        }
        Field[] fields = BeanHelper.getAllFields(origem.getClass());
        for (Field field : fields) {
            String name = field.getName();
            Object value = getPropriedade(origem, name);
            setPropriedade(destino, value, name);
        }
    }

    public static <T, D> D copyProps(T origem, D destino) throws Exception {
        if (origem == null) {
            return null;
        }
        Map<String, Object> origemMap = parseMap(origem);
        Set<String> keys = origemMap.keySet();
        for (String key : keys) {
            Object value = origemMap.get(key);
            setPropriedade(destino, value, key);
        }
        return destino;
    }

    public static void setPropertyDefaultValue(Object bean, Object value, String... remove) throws Exception {
        Map<String, Object> map = parseMap(bean);
        for (String key : remove) {
            map.remove(key);
        }

        for (String key : map.keySet()) {
            setPropriedade(bean, value, key);
        }
    }

    public static void clearBean(Object bean, String... remove) throws Exception {
        Map<String, Object> map = parseMap(bean);
        if (remove != null) {
            for (String key : remove) {
                map.remove(key);
            }
        }
        for (String key : map.keySet()) {
            Object value = getPropriedade(bean, key);
            setPropriedade(bean, getDefaultValue(value), key);
        }
    }

//      public static void instanceBean(Object bean, String... remove) {
//        Map<String, Object> map = parseMap(bean);
//        if (remove != null) {
//            for (String key : remove) {
//                map.remove(key);
//            }
//        }
//        for (String key : map.keySet()) {
//            Object value = getPropriedade(bean, key);
//            setPropriedade(bean, getDefaultValue(value), key);
//        }
//    }
    public static Object getDefaultValue(Object value) {
        if (value instanceof BigDecimal) {
            return (BigDecimal) null;
        } else if (value instanceof Date) {
            return (Date) null;
        } else if (value instanceof Double) {
            return (Double) null;
        } else if (value instanceof Float) {
            return (Float) null;
        } else if (value instanceof Integer) {
            return (Integer) null;
        } else if (value instanceof Enum) {
            return (Enum<?>) null;
        } else if (value instanceof String) {
            return (String) null;
        } else if (value instanceof Long) {
            return (Long) null;
        } else if (value instanceof Short) {
            return (Short) null;
        } else if (value instanceof Boolean) {
            return (Boolean) null;
        } else {
            return null;
        }
    }
//    public static void main(String args[]){
//        Map p = parseMap(new ConsumoCombustivelDto(1));
//        System.out.println(p);
//
//    }

    public static <T> T getObjectSelected(Object id, T[] arrays, String campo) {
        if (id == null || StringHelper.isBlank(id.toString())) {
            return null;
        }
        for (T find : arrays) {
            String valor = String.valueOf(getPropriedade(find, campo));
            String f = String.valueOf(id);
            if (f.equals(valor)) {
                return (T) find;
            }
        }
        return (T) null;
    }

    public static <T> Object getObjectSelected2(Object id, T[] arrays, String campo, String returnValue) {
        if (id == null || StringHelper.isBlank(id.toString())) {
            return null;
        }
        for (T find : arrays) {
            String valor = String.valueOf(getPropriedade(find, campo));
            Object r = getPropriedade(find, returnValue);
            String f = String.valueOf(id);
            if (f.equals(valor)) {
                return r;
            }
        }
        return null;
    }

    public static Object parseMapToBean(Map<String, Object> beanMap, Class typeClass) throws Exception {
        Set<String> keys = beanMap.keySet();

        Object bean = newInstance(typeClass);
        if (bean == null) {
            return null;
        }
        for (String key : keys) {
            Object value = beanMap.get(key);
            setPropriedade(bean, value, key);
        }
        return bean;
    }

    public static <T> T copyMapToBean(Map<String, Object> beanMap, Class<? extends T> typeClass) throws Exception {
        Set<String> keys = beanMap.keySet();

        T bean = newInstance(typeClass);
        if (bean == null) {
            return null;
        }
        for (String key : keys) {
            setPropriedade(bean, beanMap.get(key), key);
        }
        return bean;
    }

    public static <T> T newInstance(Class<? extends T> typeClass) throws Exception {
        return (T) Class.forName(typeClass.getName()).newInstance();
    }
}
