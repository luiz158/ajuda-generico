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

import java.awt.Component;

/**
 *
 * @author jacoboliveira
 */
public interface ConversorComponente {

    /**
     * -Este metodo ira possibilitar customizacao do componente, para
     * obter o valor correto do componente.
     * -o sentido da converção é da tela para o bean.
     * -aqui devera retornar o valor do atributo totalmente qualificado.
     * @param componente objeto do componente passado
     * @param valor valor passado por reflexao
     * @return retorna o valor para o bean modificado pela implementacao do metodo converter()
     * @throws Exception caso haja algum erro durante a conversao do objeto na implementacao
     */
    public Object converterParaObjeto(Component componente, Object valor) throws Exception;

    /**
     * - o sentido da converção é do bean para os componentes da tela
     * - aqui o retorno tera que ser o proprio componente
     * @param componente
     * @param valor valor passado por reflexao
     * @return retorna o objeto componente modificado pela implementacao do metodo converter()
     * @throws Exception caso haja algum erro durante a conversao do objeto na implementacao
     */
    public Object converterParaComponente(Component componente, Object bean) throws Exception;
}
