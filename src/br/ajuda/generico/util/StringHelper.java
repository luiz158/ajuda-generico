/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ajuda.generico.util;

import java.util.StringTokenizer;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Administrador
 */
public class StringHelper extends StringUtils {

    public static String valueOf(Object object) {
        if (isNull(object)) {
            return null;
        }
        return String.valueOf(object);
    }

    public static boolean isNull(Object campo) {
        return (campo == null);
    }

    public static boolean isNull(char[] campo) {
        return (campo == null || campo.length == 0);
    }

    public static boolean isNotNull(char[] campo) {
        return !isNull(campo);
    }

    public static boolean isNotNull(Object campo) {
        return !isNull(campo);
    }

    public static boolean isNullString(String campo) {
        return campo == null || campo.trim().length() == 0 ? true : false;
    }

    public static String getPath() {
        return StringHelper.class.getResource(StringHelper.class.getSimpleName() + ".class").getPath();
    }

    public static String extrairPonto(String source) {
        if (isBlank(source)) {
            return "";
        }
        char[] caracteres = source.replace('.', ' ').toCharArray();
        StringBuilder s = new StringBuilder();
        for (char c : caracteres) {
            if (!Character.isWhitespace(c)) {
                s.append(c);
            }
        }
        return s.toString();
    }

    public static String entreVirgula(String... source) {
        StringBuilder str = new StringBuilder();
        for (String s : source) {
            str.append(s).append(",");
        }
        return str.deleteCharAt(str.lastIndexOf(",")).toString();
    }

    public static String extrairNumeros(String source) {
        if (isBlank(source)) {
            return "";
        }
        char caracteres[] = source.toCharArray();
        StringBuilder str = new StringBuilder();

        for (char caracter : caracteres) {
            if (Character.isDigit(caracter)) {
                str.append(caracter);
            }
        }
        return str.toString();
    }

    public static String replaceText(String text, String search, String replace) {

        StringBuilder newText = new StringBuilder();

        StringTokenizer token = new StringTokenizer(text);

        while (token.hasMoreTokens()) {
            String element = token.nextToken();
            if (element.compareToIgnoreCase(search) == 0) {
                newText.append(replace).append(" ");
                continue;
            } else if (element.indexOf(search) != -1) {
                String t1 = StringHelper.replace(element, search, replace);
                newText.append(t1).append(" ");
                continue;
            } else {
                newText.append(element).append(" ");
                continue;
            }
        }
        return newText.toString();
    }

}
