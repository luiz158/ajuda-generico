/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ajuda.generico.util;

import br.ajuda.generico.controladordespacho.ConstantsControl;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author jacoblisboa
 */
public class JMessageUtil {
        
    public static void showWarningMessage(Component comp, String message) {
        JTextArea area = new JTextArea(10, 30);
        area.setWrapStyleWord(true);
        area.setLineWrap(true);
        area.setEditable(false);
        area.append(message);
        JOptionPane.showMessageDialog(comp, new JScrollPane(area), "Atenção", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * 
     * @param comp
     * @param message
     * @param titulo
     * @return
     */
    public static String showInputMessage(Component comp, Object message) {
        return JOptionPane.showInputDialog(comp, message, "Pergunta:", JOptionPane.QUESTION_MESSAGE);
    }



    public static int showOptionDialog(Component comp, Object message,String titulo,Object valorDefault,Object[] opcoes){
        return JOptionPane.showOptionDialog(comp,
                message,
                titulo,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                ImageUtil.getImageIconResource(ConstantsControl.PATH_IMAGES + "localizar.gif"),
                opcoes,
                valorDefault);
    }

     /**
     *
     * @param message
     * @param titulo
     * @param textInputInitialize
     * @param comp
     * @param valoresSelecionados
     * @return
     */
    public static Object showInputMessage(Component comp,Object message,Object[] valoresSelecionados) {
        return JOptionPane.showInputDialog(comp,
                message,
                "Pergunta:",
                JOptionPane.QUESTION_MESSAGE,
                ImageUtil.getImageIconResource(ConstantsControl.PATH_IMAGES + "help.gif"),
                valoresSelecionados,null);
    }
    /**
     * 
     * @param message
     * @param titulo
     * @param textInputInitialize
     * @param comp
     * @param valoresSelecionados
     * @return
     */
    public static Object showInputMessage(Component comp,Object message,String titulo,Object[] valoresSelecionados) {
        return JOptionPane.showInputDialog(comp,
                message,
                titulo,
                JOptionPane.QUESTION_MESSAGE,
                ImageUtil.getImageIconResource(ConstantsControl.PATH_IMAGES + "help.gif"),
                valoresSelecionados,null);
    }

    public static void showInfoMessage(Component comp, String message) {
        JOptionPane.showMessageDialog(comp, message, "Informação", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showSucessMessage(Component comp, String message) {
        JOptionPane.showMessageDialog(comp, message, "Sucesso", JOptionPane.INFORMATION_MESSAGE, ImageUtil.getImageIconResource(ConstantsControl.PATH_IMAGES + "sucess_msg.png"));
    }

    public static void showErroMessage(Component comp, String message) {
        JOptionPane.showMessageDialog(comp, message, "Erro Ocorrido", JOptionPane.ERROR_MESSAGE);
    }

    public static int showConfirm(String message) {
        return JOptionPane.showConfirmDialog(new JFrame(), message, "Confirmação", JOptionPane.OK_CANCEL_OPTION);
    }

    public static void showMensagensSistema(Component comp, String message) {
        JTextArea area = new JTextArea(10, 30);
        area.setWrapStyleWord(true);
        //area.setLineWrap(true);
        area.setEditable(false);
        area.append(message);
        JOptionPane.showMessageDialog(comp, new JScrollPane(area), "Mensagen(s) do Sistema:", JOptionPane.PLAIN_MESSAGE);
    }

}
