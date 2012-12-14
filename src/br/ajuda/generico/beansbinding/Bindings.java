/*
 *  Copyright (C) 2012 jacoboliveira
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.ajuda.generico.beansbinding;

import br.ajuda.generico.beansbinding.conversores.ConversorBigDecimal;
import br.ajuda.generico.beansbinding.conversores.ConversorBoolean;
import br.ajuda.generico.beansbinding.conversores.ConversorByte;
import br.ajuda.generico.beansbinding.conversores.ConversorDate;
import br.ajuda.generico.beansbinding.conversores.ConversorDouble;
import br.ajuda.generico.beansbinding.conversores.ConversorInteger;
import br.ajuda.generico.beansbinding.conversores.ConversorLong;
import br.ajuda.generico.beansbinding.conversores.ConversorObject;
import br.ajuda.generico.beansbinding.conversores.ConversorShort;
import br.ajuda.generico.beansbinding.conversores.ConversorString;
import br.ajuda.generico.util.BeanHelper;
import br.ajuda.generico.util.StringHelper;
import java.awt.Component;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jacoboliveira
 */
public class Bindings {

    private static final Logger log = Logger.getLogger(Bindings.class.getName());
    static List<BeanComposicaoBinding> beansBindings;

    static {
        beansBindings = Collections.synchronizedList(new ArrayList<BeanComposicaoBinding>());
    }

    /**
     * armazena a ligação do componente, com o método que vai ser chamado deste,
     * e o tipo do objeto requerido.
     * @param componente componente a ser feito a ligação.
     * @param nomeMetodo nome do metodo a ser chamado do componente fornecido
     * @param valorDefault valor default original do componente
     * @param tipoClasseComp tipo da classe requerida para conversão do valor do componente.
     */
    public static <C> void adicLigacao(C componente, String nomeMetodo, Object valorDefault, Class<?> tipoClasseComp) {
        aL(componente, nomeMetodo, valorDefault, tipoClasseComp, null);
    }

    /**
     * armazena a ligação do componente, com o método que vai ser chamado deste,
     * podendo tambem fornecer uma implementação do conversor e o tipo do objeto requerido.
     * @param componente componente a ser feito a ligação.
     * @param nomeMetodo nome do metodo a ser chamado do componente fornecido
     * @param valorDefault valor default original do componente
     * @param tipoClasseComp tipo da classe requerida para conversão do valor do componente.
     * @param conversorComponente personalisar o conversor
     */
    public static <C> void adicLigacao(C componente, String nomeMetodo, Object valorDefault, Class<?> tipoClasseComp, ConversorComponente conversorComponente) {
        aL(componente, nomeMetodo, valorDefault, tipoClasseComp, conversorComponente);
    }

    private static <C> void aL(C componente, String nomeMetodo, Object valorDefault, Class<?> tipoClasseComp, ConversorComponente conversorComponente) {
        beansBindings.add(new BeanComposicaoBinding(componente, nomeMetodo, tipoClasseComp, valorDefault, conversorComponente));
    }

    /**
     * realisa o processo de população do bean, atraves da ligação do componente adicionada previamente
     * @param <T> objeto generico bean
     * @param <C> objeto generico componente
     * @param bean objeto bean a ser analisado
     * @throws Exception se houver algum erro durante a analise do bean,exemplo: erro de conversao,erro de formato campo,se o bean estiver nulo etc.
     */
    public static <T, C> void analisarBean(T bean) throws Exception {
        //TODO realizar testes no demais tipos, foram testados:string,date.Testar tambem os conversores
        //TODO criar um exeception que agrupe todas os erros ocorridos nos campos para exibir na tela de uma vez so
        //TODO BUG : preenchimento em branco do componente voltar nulo pro bean(corrigido)
        //TODO criar um arquivo properties contendo as mensagens customizadas dos conversores
        //verifico se bean esta nulo
        if (bean == null) {
            throw new NullPointerException("o bean passado para realisar o binding esta nulo!");
        }
        //realiso uma varredura na lista das ligações feitas previamente
        for (BeanComposicaoBinding beanBinding : beansBindings) {
            C objGenericoComp = (C) beanBinding.getComponente();
            Component comp = (Component) objGenericoComp;
            String nomeMetodoAbuscar = beanBinding.getNomeMetodo();
            Class<?> tipoClasseComp = beanBinding.getTipoClasseComp();
            //obtenho o valor do componente, atraves do metodo fornecido
            Object valor = BeanHelper.getPropriedade(objGenericoComp, nomeMetodoAbuscar);
            //se o atributo 'name' do componente estiver nulo continuo a varredura
            if (StringHelper.isBlank(comp.getName())) {
                log.log(Level.WARNING, "Valor do nome do componente:[{0}],Forne\u00e7a um nome de acordo com bean passado, para o componente: {1}", new Object[]{comp.getName(), objGenericoComp.toString()});
                continue;
            }
            if (!BeanHelper.isAtributoContemBean(bean, comp.getName())) {
                log.log(Level.WARNING, "O nome:{0} fornecido não foi encontrado no bean!", new Object[]{comp.getName()});
                continue;
            }
            //aqui faço a verificação do tipo requirido para conversao ou cast de cada componente
            //que será setado em cada atributo do bean
            if (tipoClasseComp == BigDecimal.class) {
                //se o valor que vier já for do tipo requerido, realiso somente um cast,
                //caso contrário forneco um conversor.
                if (valor instanceof BigDecimal) {
                    BeanHelper.setPropriedade(bean, (BigDecimal) valor, comp.getName());
                } else {
                    //verifico se foi fornecido um conversor, caso não, assumo o conversor 'default',
                    //caso contrário assumo o conversor implementado
                    ConversorComponente conversor = (beanBinding.getConversorComponente() == null)
                            ? new ConversorBigDecimal() : beanBinding.getConversorComponente();

                    BeanHelper.setPropriedade(bean,
                            (BigDecimal) conversor.converterParaObjeto(comp, valor),
                            comp.getName());
                }
            } else if (tipoClasseComp == Date.class) {
                //se o valor que vier já for do tipo requerido, realiso somente um cast,
                //caso contrário forneco um conversor.
                if (valor instanceof Date) {
                    BeanHelper.setPropriedade(bean, (Date) valor, comp.getName());
                } else {
                    //verifico se foi fornecido um conversor, caso não, assumo o conversor 'default',
                    //caso contrário assumo o conversor implementado
                    ConversorComponente conversor = (beanBinding.getConversorComponente() == null)
                            ? new ConversorDate() : beanBinding.getConversorComponente();

                    BeanHelper.setPropriedade(bean,
                            (Date) conversor.converterParaObjeto(comp, valor),
                            comp.getName());
                }
            } else if (tipoClasseComp == Double.class) {
                //se o valor que vier já for do tipo requerido, realiso somente um cast,
                //caso contrário forneco um conversor.
                if (valor instanceof Double) {
                    BeanHelper.setPropriedade(bean, (Double) valor, comp.getName());
                } else {
                    //verifico se foi fornecido um conversor, caso não, assumo o conversor 'default',
                    //caso contrário assumo o conversor implementado
                    ConversorComponente conversor = (beanBinding.getConversorComponente() == null)
                            ? new ConversorDouble() : beanBinding.getConversorComponente();

                    BeanHelper.setPropriedade(bean,
                            (Double) conversor.converterParaObjeto(comp, valor),
                            comp.getName());
                }
            } else if (tipoClasseComp == Integer.class) {
                //se o valor que vier já for do tipo requerido, realiso somente um cast,
                //caso contrário forneco um conversor.
                if (valor instanceof Integer) {
                    BeanHelper.setPropriedade(bean, (Integer) valor, comp.getName());
                } else {
                    //verifico se foi fornecido um conversor, caso não, assumo o conversor 'default',
                    //caso contrário assumo o conversor implementado
                    ConversorComponente conversor = (beanBinding.getConversorComponente() == null)
                            ? new ConversorInteger() : beanBinding.getConversorComponente();

                    BeanHelper.setPropriedade(bean,
                            (Integer) conversor.converterParaObjeto(comp, valor),
                            comp.getName());
                }
            } else if (tipoClasseComp == String.class) {
                if (valor instanceof String) {
                    BeanHelper.setPropriedade(bean, (String) valor, comp.getName());
                } else {
                    //verifico se foi fornecido um conversor, caso não, assumo o conversor 'default',
                    //caso contrário assumo o conversor implementado
                    ConversorComponente conversor = (beanBinding.getConversorComponente() == null)
                            ? new ConversorString() : beanBinding.getConversorComponente();

                    BeanHelper.setPropriedade(bean,
                            (String) conversor.converterParaObjeto(comp, valor),
                            comp.getName());
                }
            } else if (tipoClasseComp == Long.class) {
                //se o valor que vier já for do tipo requerido, realiso somente um cast,
                //caso contrário forneco um conversor.
                if (valor instanceof Long) {
                    BeanHelper.setPropriedade(bean, (Long) valor, comp.getName());
                } else {
                    //verifico se foi fornecido um conversor, caso não, assumo o conversor 'default',
                    //caso contrário assumo o conversor implementado
                    ConversorComponente conversor = (beanBinding.getConversorComponente() == null)
                            ? new ConversorLong() : beanBinding.getConversorComponente();

                    BeanHelper.setPropriedade(bean,
                            (Long) conversor.converterParaObjeto(comp, valor),
                            comp.getName());
                }
            } else if (tipoClasseComp == Short.class) {
                //se o valor que vier já for do tipo requerido, realiso somente um cast,
                //caso contrário forneco um conversor.
                if (valor instanceof Short) {
                    BeanHelper.setPropriedade(bean, (Short) valor, comp.getName());
                } else {
                    //verifico se foi fornecido um conversor, caso não, assumo o conversor 'default',
                    //caso contrário assumo o conversor implementado
                    ConversorComponente conversor = (beanBinding.getConversorComponente() == null)
                            ? new ConversorShort() : beanBinding.getConversorComponente();

                    BeanHelper.setPropriedade(bean,
                            (Short) conversor.converterParaObjeto(comp, valor),
                            comp.getName());
                }
            } else if (tipoClasseComp == Boolean.class) {
                //se o valor que vier já for do tipo requerido, realiso somente um cast,
                //caso contrário forneco um conversor.
                if (valor instanceof Boolean) {
                    BeanHelper.setPropriedade(bean, (Boolean) valor, comp.getName());
                } else {
                    //verifico se foi fornecido um conversor, caso não, assumo o conversor 'default',
                    //caso contrário assumo o conversor implementado
                    ConversorComponente conversor = (beanBinding.getConversorComponente() == null)
                            ? new ConversorBoolean() : beanBinding.getConversorComponente();

                    BeanHelper.setPropriedade(bean,
                            (Boolean) conversor.converterParaObjeto(comp, valor),
                            comp.getName());
                }
            } else if (tipoClasseComp == Byte.class) {
                //se o valor que vier já for do tipo requerido, realiso somente um cast,
                //caso contrário forneco um conversor.
                if (valor instanceof Byte) {
                    BeanHelper.setPropriedade(bean, (Byte) valor, comp.getName());
                } else {
                    //verifico se foi fornecido um conversor, caso não, assumo o conversor 'default',
                    //caso contrário assumo o conversor implementado
                    ConversorComponente conversor = (beanBinding.getConversorComponente() == null)
                            ? new ConversorByte() : beanBinding.getConversorComponente();

                    BeanHelper.setPropriedade(bean,
                            (Byte) conversor.converterParaObjeto(comp, valor),
                            comp.getName());

                }
            } else {
                 ConversorComponente conversor = (beanBinding.getConversorComponente() == null)
                            ? new ConversorObject() : beanBinding.getConversorComponente();
                BeanHelper.setPropriedade(bean, conversor.converterParaObjeto(comp, valor), comp.getName());
            }
        }
    }

    /**
     * limpa todos os componentes ligados a cada nome do atributo do bean.
     * @param <C> tipo generico do componente
     * @param classe
     */
    public static <C> void limpar(Class classe) {
        Field[] campos = BeanHelper.getAllFields(classe);;
        for (Field campo : campos) {
            campo.setAccessible(true);

            for (BeanComposicaoBinding beanBinding : beansBindings) {
                C objGenericoComp = (C) beanBinding.getComponente();
                Component comp = (Component) beanBinding.getComponente();
                if (campo.getName().equals(comp.getName())) {
                    BeanHelper.setPropriedade(objGenericoComp, beanBinding.getValorDefault(), beanBinding.getNomeMetodo());
                    break;
                }

            }
        }
    }
}
