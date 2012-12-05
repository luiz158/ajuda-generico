/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ajuda.generico.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author jacoboliveira
 */
public class LogSis {

    private static final Logger logger = Logger.getLogger("LogSistema");
    private Throwable ex;

    public LogSis() {
        //TODO fazer uma rotina criar uma pasta 'logs' e transferir o arquivo sis.log para dentro da mesma
        try {

            String appPath = FileHelper.getLocationAppPath()
                    + SO.getSepArqSO()
                    + "build"
                    + SO.getSepArqSO()
                    + "classes";
            //String appPath =FileHelper.getLocationAppPath();
            System.out.println(appPath);
            File arq = new File(appPath + SO.getSepArqSO() + "sis.log");
            if (!arq.exists()) {
                arq.createNewFile();
            }
            FileHandler fh = new FileHandler(arq.getPath(), true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (IOException ex) {
            Logger.getLogger(LogSis.class.getName()).log(Level.SEVERE, "ocorreu um erro de comunicação ao tentar criar o arquivo, verificar permissões do mesmo ou se o caminho esta correto!", ex);
        } catch (SecurityException ex) {
            Logger.getLogger(LogSis.class.getName()).log(Level.SEVERE, "gerenciamento de segurança do log inexistente", ex);
        }
    }

    public void alerta(String msg) {
        logger.log(Level.WARNING, msg);
    }

    public void info(String msg) {
        logger.log(Level.INFO, msg);
    }

    public void erro(String msg) {
        logger.log(Level.SEVERE, msg);
    }

    public void info(String msg, Throwable ex) {
        logger.log(Level.INFO, msg, ex);
        this.ex = ex;
    }

    public void alerta(String msg, Throwable ex) {
        logger.log(Level.WARNING, msg, ex);
        this.ex = ex;
    }

    public void erro(String msg, Throwable ex) {
        logger.log(Level.SEVERE, msg, ex);
        this.ex = ex;
    }

    public void log(Level level, String msg, Throwable ex) {
        logger.log(level, msg, ex);
        this.ex = ex;
    }

    public void log(Level level, String msg) {
        logger.log(level, msg);
    }

    public String getPrintStackTrace(String classeExceptionOrigem) {
        String r = "";
        StackTraceElement[] ste = ex.getStackTrace();
        for (StackTraceElement stack : ste) {
            r += classeExceptionOrigem+"\n\tde "+stack.getClassName() + "." + stack.getMethodName()+"("+stack.getFileName()+ ":"+stack.getLineNumber()+")";
        }
        return r;
    }
}
