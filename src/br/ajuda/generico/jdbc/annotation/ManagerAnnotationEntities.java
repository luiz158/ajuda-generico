/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ajuda.generico.jdbc.annotation;

import br.ajuda.generico.util.BeanHelper;
import br.ajuda.generico.util.LogSis;
import br.ajuda.generico.util.NumberHelper;
import br.com.jacob.util.CollectionsUtil;
import br.com.jacob.util.IMapa;
import br.com.jacob.util.Mapa;
import br.com.jacob.util.StringHelper;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author jacob
 */
public class ManagerAnnotationEntities {

    private LogSis logSis;

    public ManagerAnnotationEntities() {
        logSis = new LogSis();
    }

    /**
     * retorna o nome da tabela mapeada
     * @param <T>
     * @param bean entidade mapeada
     * @return uma string com o nome da tabela
     */
    public <T> String getNomeTabela(T bean) {
        Class classe = bean.getClass();
        if (classe.isAnnotationPresent(Tabela.class)) {
            Annotation[] annotations = classe.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Tabela) {
                    Tabela tabela = (Tabela) annotation;
                    String prefixoSchema = !tabela.schema().equals("") ? tabela.schema() + "." : "";
                    return prefixoSchema + tabela.nome();
                }
            }
        }
        return null;
    }

    /**
     *
     *  este metodo serve para retorna os nomes definidos na anotacao dos campos da classe
     *  Exemplo:
     *  @CampoBD(nome="nome_completo")
     *  retorna a string "nome_completo" em um Mapa<String,String>
     * 
     * @param <T>
     * @param bean classe contendo setters e getters
     * @return retorna uma classe Mapa<String,String> contendo o nome do campo do banco e o nome do campo do bean(respectivamente)
     */
    public <T> IMapa<String, String> getCampos(T bean) {
        return getCampos(bean, false);
    }

    /**
     *
     *  este metodo serve para retorna os nomes definidos na anotacao dos campos da classe
     *  sem os campos ids do banco
     *  Exemplo:
     *  @CampoBD(nome="nome_completo")
     *  retorna a string "nome_completo" em um Mapa<String,String>
     *
     * @param <T>
     * @param bean classe contendo setters e getters
     * @return retorna uma classe Mapa<String,String> contendo o nome do campo do banco e o nome do campo do bean(respectivamente)
     */
    public <T> IMapa<String, String> getCamposSemIds(T bean) {
        return getCampos(bean, true);
    }

    private <T> IMapa<String, String> getCampos(T bean, boolean flag) {
        //TODO se caso nao for preenchido o campo 'nome' da interface CampoBD, considerar o nome do atributo como 'nome'
        IMapa<String, String> nomeCamposMap = new Mapa();
        Field[] campos = BeanHelper.getAllFields(bean.getClass());
        for (Field field : campos) {
            if (field.isAnnotationPresent(Id.class) && flag) {
                continue;
            }
            if (field.isAnnotationPresent(CampoBD.class)) {
                field.setAccessible(true);
                CampoBD campoBD = field.getAnnotation(CampoBD.class);
                nomeCamposMap.adicionar(campoBD.nome(), field.getName());
            }
        }
        return nomeCamposMap;
    }

    public <T> IMapa<String, Object> getConverterBeanParaMap(T bean) {
        return getCBPMap(bean, false);
    }

    public <T> IMapa<String, Object> getConverterBeanParaMapIds(T bean) {
        return getCBPMap(bean, true);
    }

    private <T> IMapa<String, Object> getCBPMap(T bean, boolean flag) {
        //TODO se caso nao for preenchido o campo 'nome' da interface CampoBD, considerar o nome do atributo como 'nome'
        IMapa<String, Object> beanMap = new Mapa();
        Field[] campos = BeanHelper.getAllFields(bean.getClass());
        for (Field field : campos) {
            if (field.isAnnotationPresent(Id.class) && flag) {
                field.setAccessible(true);
                CampoBD campoBD = field.getAnnotation(CampoBD.class);
                Object valor = BeanHelper.getPropriedade(bean, field.getName());
                beanMap.adicionar(campoBD.nome(), valor);
                continue;
            }
            if (field.isAnnotationPresent(CampoBD.class)&&!flag) {
                field.setAccessible(true);
                CampoBD campoBD = field.getAnnotation(CampoBD.class);
                Object valor = BeanHelper.getPropriedade(bean, field.getName());
                beanMap.adicionar(campoBD.nome(), valor);
            }
        }
        return beanMap;
    }

    /**
     *  obtem somentes os ids mapeados na entidade
     * @param <T> generico 
     * @param bean entidade mapeada
     * @return um Map com os ids da entidade
     */
    public <T> IMapa<String, String> getIdsCampos(T bean) {
        IMapa<String, String> idsCamposMap = new Mapa();
        Field[] campos = BeanHelper.getAllFields(bean.getClass());
        for (Field field : campos) {
            if (field.isAnnotationPresent(CampoBD.class) && field.isAnnotationPresent(Id.class)) {
                field.setAccessible(true);
                CampoBD campoBD = field.getAnnotation(CampoBD.class);
                idsCamposMap.adicionar(campoBD.nome(), field.getName());
            }
        }
        return idsCamposMap;
    }

    public <T> IMapa<String, Object> getExCamposTransitorios(T bean) {
        return getExCampos(bean, true, false);
    }

    public <T> IMapa<String, Object> getExCamposTransitoriosEIds(T bean) {
        return getExCampos(bean, true, true);
    }

    private <T> IMapa<String, Object> getExCampos(T bean, boolean flag1, boolean flag2) {
        IMapa<String, Object> beanMap = new Mapa<String, Object>();
        if (bean == null) {
            return beanMap;
        }
        //inicializando indice com 1
        beanMap.setIndex(1);
        Class clazz = bean.getClass();
        Field[] fields = BeanHelper.getAllFields(clazz);
        for (Field field : fields) {
            if (field.isAnnotationPresent(Transitorio.class) && flag1) {
                continue;
            }

            if (field.isAnnotationPresent(Id.class) && flag2) {
                continue;
            }

            String name = field.getName();
            field.setAccessible(true);
            Class type = field.getClass();
            Object value = BeanHelper.getPropriedade(bean, field.getName());
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

    public void analisarCamposObrig(Object entidade) throws CampoObrigException {
        CampoBD campo = null;
        CampoObrigException camposEx = new CampoObrigException();
        Field[] fields = BeanHelper.getAllFields(entidade.getClass());
        for (Field field : fields) {
            if (field.isAnnotationPresent(CampoBD.class)) {
                field.setAccessible(true);
                try {
                    campo = field.getAnnotation(CampoBD.class);
                    if (!campo.obrigatorio()) {
                        continue;
                    }
                    Object valor = field.get(entidade);

                    if (valor == null) {
                        camposEx.adic(campo.nomeImpCampo(), campo.mensagem());
                    } else {
                        Class<?> fieldType = valor.getClass();
                        if (fieldType.isPrimitive()) {

                            if (NumberHelper.isNull(valor)) {
                                camposEx.adic(campo.nomeImpCampo(), campo.mensagem());
                            }

                        } else if (valor instanceof String) {

                            if (StringHelper.isBlank((String) valor)) {
                                camposEx.adic(campo.nomeImpCampo(), campo.mensagem());
                            }

                        } else if (valor instanceof Character) {
                            
                            if (Character.isWhitespace((Character) valor)) {
                                camposEx.adic(campo.nomeImpCampo(), campo.mensagem());
                            } else if (((Character)valor)=='\u0000') {
                                camposEx.adic(campo.nomeImpCampo(), campo.mensagem());
                            }
                        } else if (valor instanceof Number) {
                            if (NumberHelper.isNull(valor)) {
                                camposEx.adic(campo.nomeImpCampo(), campo.mensagem());
                            }
                        }
                    }
                } catch (Throwable ex) {
                    logSis.erro(ex.getMessage(), ex);
                }
            }
        }
        if(!CollectionsUtil.isEmpty(camposEx.getList())){
            throw camposEx;
        }
    }
//    public <K, V> void populatedMap(Map<K, V> origem, Map<K, V> destino) {
//        if (origem.isEmpty()) {
//            return;
//        }
//        Set<K> keys = origem.keySet();
//        for (K key : keys) {
//            destino.put(key, origem.get(key));
//        }
//    }
}
