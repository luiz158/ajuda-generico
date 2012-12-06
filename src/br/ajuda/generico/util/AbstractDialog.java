/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ajuda.generico.util;

import br.ajuda.generico.control.ControladorDespacho;
import br.ajuda.generico.control.Visao;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jacoboliveira
 */
public class AbstractDialog extends javax.swing.JDialog implements Visao{
    private static final long serialVersionUID = 4436597960584755195L;

    protected LogSis logSis;
    protected ControladorDespacho controladorDespacho;
    private final static Logger log = Logger.getLogger("LogConsole");

    public AbstractDialog() {
        super();
        logSis = new LogSis();
    }

    public AbstractDialog(Window owner, String title, ModalityType modalityType, GraphicsConfiguration gc) {
        super(owner, title, modalityType, gc);
    }

    public AbstractDialog(Window owner, String title, ModalityType modalityType) {
        super(owner, title, modalityType);
    }

    public AbstractDialog(Window owner, String title) {
        super(owner, title);
    }

    public AbstractDialog(Window owner, ModalityType modalityType) {
        super(owner, modalityType);
    }

    public AbstractDialog(Window owner) {
        super(owner);
    }

    public AbstractDialog(Dialog owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
    }

    public AbstractDialog(Dialog owner, String title, boolean modal) {
        super(owner, title, modal);
    }

    public AbstractDialog(Dialog owner, String title) {
        super(owner, title);
    }

    public AbstractDialog(Dialog owner, boolean modal) {
        super(owner, modal);
    }

    public AbstractDialog(Dialog owner) {
        super(owner);
    }

    public AbstractDialog(Frame owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
    }

    public AbstractDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
    }

    public AbstractDialog(Frame owner, String title) {
        super(owner, title);
    }

    public AbstractDialog(Frame owner, boolean modal) {
        super(owner, modal);
        logSis = new LogSis();
    }

    public AbstractDialog(Frame owner) {
        super(owner);
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
