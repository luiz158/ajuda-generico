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

package testes;

import br.ajuda.generico.util.NumberHelper;
import br.ajuda.generico.util.RegexUtil;
import java.text.ParseException;

/**
 *
 * @author jacoboliveira
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        //System.out.println(DateUtil.stringToDate(DateUtil.FORMAT_DATE_DDMMYYYY, "06/12/2012"));
//        GregorianCalendar calendar = new GregorianCalendar();
//
//        p(""+calendar.get(GregorianCalendar.DAY_OF_MONTH));
        //p(RegexUtil.matcher("12/12/2012", RegexUtil.PATTERN_DATE_DDMMYYYY)+"");
        p(String.valueOf(NumberHelper.newDecimalFormatCustom().parse("1.200,50")));
    }

    private static void p(String s) {
        System.out.println(s);
    }

}
