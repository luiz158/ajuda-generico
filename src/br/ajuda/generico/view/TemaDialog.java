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

/*
 * TemaDialog.java
 *
 * Created on 05/12/2012, 11:11:22
 */
package br.ajuda.generico.view;

import br.ajuda.generico.beansbinding.Bindings;
import br.ajuda.generico.controller.TemaController;
import br.ajuda.generico.entities.Tema;
import br.ajuda.generico.util.AbstractDialog;
import br.ajuda.generico.util.JMessageUtil;
import br.ajuda.generico.util.StringHelper;
import br.com.swing.componentes.personalizados.ui.ListaComFiltro;

/**
 *
 * @author jacoboliveira
 */
public class TemaDialog extends AbstractDialog {

    private TemaController temaController;

    /** Creates new form TemaDialog */
    public TemaDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Bindings.adicLigacao(tituloTField, "text", null, String.class);
        Bindings.adicLigacao(descTArea, "text", null, String.class);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descTArea = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tituloTField = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        fecharButton = new javax.swing.JButton();
        salvarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Temas");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setText("Descrição:");
        jLabel1.setName("jLabel1"); // NOI18N
        jPanel1.add(jLabel1);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        descTArea.setColumns(20);
        descTArea.setLineWrap(true);
        descTArea.setRows(7);
        descTArea.setWrapStyleWord(true);
        descTArea.setName("descricao"); // NOI18N
        jScrollPane1.setViewportView(descTArea);

        jPanel1.add(jScrollPane1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 256;
        gridBagConstraints.ipady = 94;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 0);
        jPanel2.add(jPanel1, gridBagConstraints);

        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        jLabel2.setText("Titulo:");
        jLabel2.setName("jLabel2"); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(50, 14));
        jPanel4.add(jLabel2);

        tituloTField.setName("titulo"); // NOI18N
        jPanel4.add(tituloTField);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 292;
        jPanel2.add(jPanel4, gridBagConstraints);

        jSeparator1.setName("jSeparator1"); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        fecharButton.setText("Fechar");
        fecharButton.setName("fecharButton"); // NOI18N
        fecharButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecharButtonActionPerformed(evt);
            }
        });
        jPanel3.add(fecharButton);

        salvarButton.setText("Adicionar");
        salvarButton.setName("salvarButton"); // NOI18N
        salvarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarButtonActionPerformed(evt);
            }
        });
        jPanel3.add(salvarButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-456)/2, (screenSize.height-351)/2, 456, 351);
    }// </editor-fold>//GEN-END:initComponents

    private void salvarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarButtonActionPerformed

        try {

            Tema tema = new Tema();

            int operacao = (Integer) controladorDespacho.getParam(MainFrame.OPER_TEMA);

            //se a operacao for adicionar o tema
            if (operacao == MainFrame.OPER_ADIC_TEMA) {
                Bindings.analisarBean(tema);
                temaController.salvar(tema);
                //adiciono o tema na lista da janela principal
                ((ListaComFiltro<Tema>) controladorDespacho.getParam(MainFrame.COMP_ADIC_TEMA_JLIST)).adicionarItem(tema);
                JMessageUtil.showSucessMessage(this, "Tema:'"
                        + StringHelper.getFraseStringLimitado(tema.getTitulo(), 30) + "', inserido com sucesso!");
                Bindings.limpar(Tema.class);
            } else {//senao altera o tema
                tema = (Tema) controladorDespacho.getParam(MainFrame.OBJ_TEMA);
                Bindings.analisarBean(tema);
                temaController.alterar(tema);
                //altero o tema na lista da janela principal
                ListaComFiltro<Tema> adicTemaList = (ListaComFiltro) controladorDespacho.getParam(MainFrame.COMP_ADIC_TEMA_JLIST);

                adicTemaList.alterarItem(tema, adicTemaList.getList().getSelectedIndex());

                JMessageUtil.showSucessMessage(this, "Tema:'"
                        + StringHelper.getFraseStringLimitado(tema.getTitulo(), 30) + "', alterado com sucesso!");

                dispose();
            }

            tituloTField.setRequestFocusEnabled(true);
        } catch (Exception ex) {
            this.controladorDespacho.registraEexibe(ex);
        }

    }//GEN-LAST:event_salvarButtonActionPerformed

    private void fecharButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecharButtonActionPerformed

        dispose();

    }//GEN-LAST:event_fecharButtonActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        try {
            temaController = new TemaController();
        } catch (Exception ex) {
            controladorDespacho.registraEexibe(ex);
        }

        if (controladorDespacho.getParam(MainFrame.OPER_TEMA) != null
                && ((Integer) controladorDespacho.getParam(MainFrame.OPER_TEMA)) == MainFrame.OPER_ALT_TEMA) {
            try {

                Tema tema = temaController.consultaUnicoRetorno((Tema) controladorDespacho.getParam(MainFrame.OBJ_TEMA));
                if (tema == null) {
                    throw new NullPointerException("Objeto 'tema' esta nulo! Tela:'"+getTitle()+ "'");
                }
                tituloTField.setText(tema.getTitulo());
                descTArea.setText(tema.getDescricao());
                salvarButton.setText("Alterar");

            } catch (Exception ex) {
                controladorDespacho.registraEexibe(ex);
            }
        }
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

        //remocao dos parametros
        controladorDespacho.removerParam(MainFrame.OPER_TEMA);
        controladorDespacho.removerParam(MainFrame.OBJ_TEMA);
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                TemaDialog dialog = new TemaDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea descTArea;
    private javax.swing.JButton fecharButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton salvarButton;
    private javax.swing.JTextField tituloTField;
    // End of variables declaration//GEN-END:variables
}
