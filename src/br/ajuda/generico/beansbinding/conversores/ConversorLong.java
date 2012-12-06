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

package br.ajuda.generico.beansbinding.conversores;

import br.ajuda.generico.beansbinding.ConversorComponente;
import br.ajuda.generico.util.NumberHelper;
import java.awt.Component;

/**
 *
 * @author jacoboliveira
 */
public class ConversorLong implements ConversorComponente{

    @Override
    public Object converterParaObjeto(Component componente, Object valor) throws Exception {
        if(valor==null){
            return valor;
        }
        String source = String.valueOf(valor);
        if (!NumberHelper.isNumber(source)) {
            throw new NumberFormatException("Formato do numero inválido, "
                    + "por favor forneça um conversor ou formate o componente para obter o valor correto. "
                    + "\nValor fornercido:[" + valor + "]"
                    + "\nNome componente:[" + componente.getName() + "]");
        }
        return NumberHelper.formatLong(source);
    }

    @Override
    public Object converterParaComponente(Component componente, Object bean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
