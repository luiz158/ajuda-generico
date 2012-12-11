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

import java.io.Serializable;

/**
 *
 * @author jacoboliveira
 */
public class BeanComposicaoBinding<C> implements Serializable{
    private static final long serialVersionUID = -2752280998602738906L;
    //o objeto do componente
    private C componente;
    //o nome do metodo do componente a ser setado
    private String nomeMetodo;
    //e o tipo do valor que é passado pelo componente, exemplo: Intenger.class,String.class,BigDecimal.class, e etc.
    private Class<?> tipoClasseComp;
    //conversor de entrada/saida 
    private ConversorComponente conversorComponente;
    //valor default original do componente
    private Object valorDefault;

    public BeanComposicaoBinding() {
    }



    public BeanComposicaoBinding(C componente, String nomeMetodo, Class<?> tipoClasseComp,Object valorDefault, ConversorComponente conversorComponente) {
        this.componente = componente;
        this.nomeMetodo = nomeMetodo;
        this.tipoClasseComp = tipoClasseComp;
        this.conversorComponente = conversorComponente;
        this.valorDefault = valorDefault;
    }

    /**
     * @return the componente
     */
    public C getComponente() {
        return componente;
    }

    /**
     * @param componente the componente to set
     */
    public void setComponente(C componente) {
        this.componente = componente;
    }

    /**
     * @return the nomeMetodo
     */
    public String getNomeMetodo() {
        return nomeMetodo;
    }

    /**
     * @param nomeMetodo the nomeMetodo to set
     */
    public void setNomeMetodo(String nomeMetodo) {
        this.nomeMetodo = nomeMetodo;
    }

    /**
     * @return the tipoClasseComp
     */
    public Class<?> getTipoClasseComp() {
        return tipoClasseComp;
    }

    /**
     * @param tipoClasseComp the tipoClasseComp to set
     */
    public void setTipoClasseComp(Class<?> tipoClasseComp) {
        this.tipoClasseComp = tipoClasseComp;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BeanComposicaoBinding other = (BeanComposicaoBinding) obj;
        if (this.componente != other.componente && (this.componente == null || !this.componente.equals(other.componente))) {
            return false;
        }
        if ((this.nomeMetodo == null) ? (other.nomeMetodo != null) : !this.nomeMetodo.equals(other.nomeMetodo)) {
            return false;
        }
        if (this.tipoClasseComp != other.tipoClasseComp && (this.tipoClasseComp == null || !this.tipoClasseComp.equals(other.tipoClasseComp))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.componente != null ? this.componente.hashCode() : 0);
        hash = 53 * hash + (this.nomeMetodo != null ? this.nomeMetodo.hashCode() : 0);
        hash = 53 * hash + (this.tipoClasseComp != null ? this.tipoClasseComp.hashCode() : 0);
        return hash;
    }

    /**
     * @return the conversorComponente
     */
    public ConversorComponente getConversorComponente() {
        return conversorComponente;
    }

    /**
     * @param conversorComponente the conversorComponente to set
     */
    public void setConversorComponente(ConversorComponente conversorComponente) {
        this.conversorComponente = conversorComponente;
    }

    /**
     * @return the valorDefault
     */
    public Object getValorDefault() {
        return valorDefault;
    }

    /**
     * @param valorDefault the valorDefault to set
     */
    public void setValorDefault(Object valorDefault) {
        this.valorDefault = valorDefault;
    }

}
