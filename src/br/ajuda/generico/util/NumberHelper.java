/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ajuda.generico.util;

import br.com.jacob.util.StringHelper;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import org.apache.commons.lang.math.NumberUtils;

/**
 *
 * @author Administrador
 */
public class NumberHelper extends NumberUtils {

    private static Locale locale = new Locale("pt", "BR");

    public static Double formatDouble(String source) {
        if (StringHelper.isBlank(source)) {
            return new Double(0.0d);
        }
        return new Double(toDouble(source, 0.0d));
    }

    public static Float formatFloat(String source) {
        if (StringHelper.isBlank(source)) {
            return new Float(0.0f);
        }
        return new Float(toFloat(source, 0.0f));
    }

    public static Long formatLong(String source) {
        if (StringHelper.isBlank(source)) {
            return new Long(0L);
        }
        return new Long(toLong(source, 0L));
    }

    public static DecimalFormat newDecimalFormatCustom() {
        return new DecimalFormat("#,##0.00", new DecimalFormatSymbols(locale));
    }

    public static DecimalFormat newDecimalFormatCustom2(String format) {
        return new DecimalFormat(format, new DecimalFormatSymbols(locale));
    }

    public static boolean isNull(Integer n) {
        return (n == null || n.intValue() == -1);
    }

    public static boolean isNull(Double d) {
        return (d == null || d.doubleValue() == -1d);
    }

    public static boolean isNotNull(Double d) {
        return !isNull(d);
    }

    public static boolean isNull(Object o) {
        if (o instanceof Integer) {
            return isNull((Integer) o);
        } else if (o instanceof Double) {
            return isNull((Double) o);
        } else if (o instanceof Short) {
            return isNull((Short) o);
        } else if (o instanceof Long) {
            return isNull((Long) o);
        }else if (o instanceof Byte) {
            return (((Byte)o).byteValue()==-1);
        }
        return (o == null);
    }

    public static boolean isNotNull(Object o) {
        return !isNull(o);
    }

    public static boolean isNull(double d) {
        return d == -1d;
    }

    public static boolean isNull(float f) {
        return f == -1f;
    }

    public static boolean isNull(short s) {
        return s == -1;
    }

    public static boolean isNull(int i) {
        return i == -1;
    }

    public static boolean isNull(byte b) {
        return b == -1;
    }

    public static boolean isNotNull(Integer n) {
        return !isNull(n);
    }

    public static boolean isNull(BigDecimal n) {
        return (n == null || n.doubleValue() == -1d);
    }

    public static boolean isNotNull(BigDecimal n) {
        return !isNull(n);
    }

//    public boolean isNumber(String src) {
//        char[] caracteres = src.toCharArray();
//        boolean isNumber = true;
//        for (char c : caracteres) {
//            if (!Character.isDigit(c)) {
//                isNumber = false;
//                return isNumber;
//            }
//        }
//        return isNumber;
//    }
//    public static void main(String[] args) {
//        String as = "0";
//        System.out.println("print: " + new Integer(as));
//    }
}
