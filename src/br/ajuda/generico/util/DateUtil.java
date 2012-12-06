/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ajuda.generico.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.time.DateUtils;

/**
 *
 * @author jacob_lisboa
 */
public class DateUtil extends DateUtils {

    public static final String FORMAT_DATETIME_YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATETIME_DDMMYYYY_HHMMSS = "dd/MM/yyyy HH:mm:ss";
    public static final String FORMAT_DATETIME_MMDDYYYY_HHMMSS = "MM/dd/yyyy HH:mm:ss";
    public static final String FORMAT_DATETIME_DDMMYYYY_HHMM = "dd/MM/yyyy HH:mm";
    public static final String FORMAT_DATETIME_MMDDYYYY_HHMM = "MM/dd/yyyy HH:mm";
    public static final String FORMAT_DATETIME_YYYYMMDD_HHMM = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_DATE_DDMMYYYY = "dd/MM/yyyy";
    public static final String FORMAT_DATE_MMDDYYYY = "dd/MM/yyyy";
    public static final String FORMAT_DATE_YYYYMMDD = "yyyy-MM-dd";

    public DateUtil() {
    }

    public static String getDataHoraHoje() {
        return new SimpleDateFormat(FORMAT_DATE_DDMMYYYY).format(getDataHoje());
    }

    public static Date getDataHoje() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_DDMMYYYY);
        try {
            return sdf.parse(sdf.format(new Date()));
        } catch (ParseException ex) {
            Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Date getDataHoje(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(sdf.format(new Date()));
        } catch (ParseException ex) {
            Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static String getDataHoje2(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }

    public static Date getDataHoje2() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATETIME_DDMMYYYY_HHMMSS);
        try {
            return sdf.parse(sdf.format(new Date()));
        } catch (ParseException ex) {
            Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static String dateToString(String pattern, Date date) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static Date stringToDate(String pattern, String source) throws ParseException {
        return new SimpleDateFormat(pattern).parse(source);
    }

    public static Date parse(String src, String patternIn, String patternOut) throws ParseException {
        if (StringHelper.isBlank(src)) {
            return null;
        }
        SimpleDateFormat in = new SimpleDateFormat(patternIn);
        SimpleDateFormat out = new SimpleDateFormat(patternOut);
        return out.parse(out.format(in.parse(src)));
    }
}
