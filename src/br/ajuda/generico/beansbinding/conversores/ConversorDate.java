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
import br.ajuda.generico.beansbinding.exceptions.FormatoDataException;
import br.ajuda.generico.util.DateUtil;
import br.ajuda.generico.util.RegexUtil;
import br.ajuda.generico.util.StringHelper;
import java.awt.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author jacoboliveira
 */
public class ConversorDate implements ConversorComponente {

    @Override
    public Object converterParaObjeto(Component componente, Object valor) throws Exception {
        if (valor == null) {
            return valor;
        }
        String source = String.valueOf(valor);
        boolean isAnaliseFormatoData = false;

        String[] formatosValidos = {
            DateUtil.FORMAT_DATE_DDMMYYYY,            
            DateUtil.FORMAT_DATETIME_DDMMYYYY_HHMM,            
        };

        String[] testRegexValidos = {
            RegexUtil.PATTERN_DATE_DDMMYYYY,
            RegexUtil.PATTERN_DATE_DDMMYYYY_HHMM
        };
        
        String regexEncontrado = "";
        int formato=0;
        //faço a analise do formato da data.
        for (String regex : testRegexValidos) {
            ++formato;
            if (RegexUtil.matcher(source, regex)) {
                isAnaliseFormatoData = true;
                regexEncontrado = regex;                
                break;
            } else {
                isAnaliseFormatoData = false;
            }
        }

        if (isAnaliseFormatoData == false) {
            throw new FormatoDataException("Formato da data inválido:'"
                    + source
                    + "'! Forneça um dos formatos a seguir:" + StringHelper.entreVirgula(formatosValidos));
        }
        //faço a analise do dia e mes se forma informados corretamente.
        Pattern checarData = Pattern.compile(regexEncontrado);

        Matcher m = checarData.matcher(source);
        m.matches();
        int dia = Integer.parseInt(m.group(1));
        int mes = Integer.parseInt(m.group(2));
        
        if (dia > 31 && mes > 12) {
            throw new FormatoDataException("Dia e Mês com formato incorreto,respectivamente: " + dia + "; " + mes + "!");
        }

        if (dia > 31) {
            throw new FormatoDataException("Dia: " + dia + " incorreto!");
        }

        if (mes > 12) {
            throw new FormatoDataException("Mês: " + mes + " incorreto!");
        }

        if(formato==2){
            int hora = Integer.parseInt(m.group(4));
            int minutos = Integer.parseInt(m.group(5));
            if(hora>23){
                throw new FormatoDataException("Hora incorreta: maior que 23!");
            }
            if(minutos>59){
                throw new FormatoDataException("Minutos incorreto: maior que 59!");
            }
        }

        return DateUtil.stringToDate(
                ((formato==1)?DateUtil.FORMAT_DATE_DDMMYYYY:DateUtil.FORMAT_DATETIME_DDMMYYYY_HHMM),
                source);
    }

    @Override
    public Object converterParaComponente(Component componente, Object bean) throws Exception {
        return null;
    }
}
