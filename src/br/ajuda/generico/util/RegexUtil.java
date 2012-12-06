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

package br.ajuda.generico.util;

import java.util.regex.Pattern;

/**
 *
 * @author jacoboliveira
 */
public class RegexUtil {

    public static final String PATTERN_DATE_DDMMYYYY="([0-9]{2})/([0-9]{2})/([0-9]{4})";
    public static final String PATTERN_DATE_DDMMYYYY_HHMM="([0-9]{2})/([0-9]{2})/([0-9]{4}) ([0-9]{2}):([0-9]{2})";

    public static boolean matcher(String p,String regex){
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(p).matches();
    }


}
