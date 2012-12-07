/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ajuda.generico.util;

import br.ajuda.generico.controladordespacho.ControladorDespacho;
import br.ajuda.generico.controladordespacho.Visao;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jacoboliveira
 */
public class AbstractFrame extends javax.swing.JFrame implements Visao{

    protected LogSis logSis;
    protected ControladorDespacho controladorDespacho;
    private final static Logger log = Logger.getLogger("LogConsole");

    public AbstractFrame(String title, GraphicsConfiguration gc) {
        super(title, gc);
    }

    public AbstractFrame(String title) throws HeadlessException {
        super(title);
    }

    public AbstractFrame(GraphicsConfiguration gc) {
        super(gc);
    }

    public AbstractFrame() throws HeadlessException {
        super();
        logSis = new LogSis();
    }

    /**
     * metodo para mostrar informacoes no console
     * @param msg
     */
    protected void info(String msg) {
        log.log(Level.INFO, msg);
    }

    /**
     * metodo para mostrar informacoes no console
     * @param msg
     */
    protected void warn(String msg) {
        log.log(Level.WARNING, msg);
    }

    /**
     * metodo para mostrar informacoes no console
     * @param msg
     */
    protected void error(String msg) {
        log.log(Level.SEVERE, msg);
    }

    /**
     * metodo para mostrar informacoes no console
     *
     * @param msg
     * @param ex
     */
    protected void info(String msg, Throwable ex) {
        log.log(Level.INFO, msg, ex);
    }

    /**
     * metodo para mostrar informacoes no console
     * @param msg
     * @param ex
     */
    protected void warn(String msg, Throwable ex) {
        log.log(Level.WARNING, msg, ex);
    }

    /**
     * metodo para mostrar informacoes no console
     * 
     * @param msg
     * @param ex
     */
    protected void error(String msg, Throwable ex) {
        log.log(Level.SEVERE, msg, ex);
    }

    @Override
    public void setControladorDespacho(ControladorDespacho controladorDespacho) {
        this.controladorDespacho=controladorDespacho;
    }
}
