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
 * TestFormBinding.java
 *
 * Created on 06/12/2012, 11:35:21
 */

package testes;

import br.ajuda.generico.beansbinding.Bindings;
import br.ajuda.generico.beansbinding.ConversorComponente;
import br.ajuda.generico.util.NumberHelper;
import br.ajuda.generico.util.StringHelper;
import java.awt.Component;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jacoboliveira
 */
public class TestFormBinding extends javax.swing.JFrame {

    /** Creates new form TestFormBinding */
    public TestFormBinding() {
        initComponents();
//        Bindings.adicLigacao(nomeTField, "text", String.class);
//        Bindings.adicLigacao(dataAniverTField, "text", Date.class);
//        Bindings.adicLigacao(salarioTField, "text", Double.class);
//        Bindings.adicLigacao(nArquivosTField, "text", Long.class);
//        Bindings.adicLigacao(idadeTField, "text", Integer.class);
//        Bindings.adicLigacao(shortTField, "text", Short.class);
//        Bindings.adicLigacao(fmCBox, "selected", Boolean.class);
//        Bindings.adicLigacao(totalTField, "text", BigDecimal.class,new ConversorComponente() {
//
//            @Override
//            public Object converterParaObjeto(Component componente, Object valor) throws Exception {
//                if(valor ==null){
//                    return valor;
//                }
//                String source = StringHelper.valueOf(valor);
//                if(StringHelper.isBlank(source)){
//                    return null;
//                }
//                String tmp = source.replaceAll(",", "").replaceAll("\\.", "");
//                if(!NumberHelper.isNumber(tmp)){
//                   throw new NumberFormatException("O valor informado:'"+source
//                           + "', esta em um formato inválido! "
//                           + "Usar este formato,exemplo: 1.200,00 ou 50,00");
//                }
//                return new BigDecimal(String.valueOf(NumberHelper.newDecimalFormatCustom().parse(source)));
//            }
//
//            @Override
//            public Object converterParaComponente(Component componente, Object bean) throws Exception {
//                throw new UnsupportedOperationException("Not supported yet.");
//            }
//        });
        
    }

	
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        nomeTField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        dataAniverTField = new javax.swing.JTextField();
        salarioTField = new javax.swing.JTextField();
        nArquivosTField = new javax.swing.JTextField();
        idadeTField = new javax.swing.JTextField();
        shortTField = new javax.swing.JTextField();
        fmCBox = new javax.swing.JCheckBox();
        totalTField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nomeTField.setText("nome");
        nomeTField.setName("nome"); // NOI18N

        jButton1.setText("jButton1");
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        dataAniverTField.setText("dd/mm/yyyy");
        dataAniverTField.setName("dataAniver"); // NOI18N

        salarioTField.setText("salario");
        salarioTField.setName("salario"); // NOI18N

        nArquivosTField.setText("nArquivos");
        nArquivosTField.setName("nArquivos"); // NOI18N

        idadeTField.setText("idade");
        idadeTField.setName("idade"); // NOI18N

        shortTField.setText("short");
        shortTField.setName("b"); // NOI18N

        fmCBox.setText("F/M");
        fmCBox.setName("sexo"); // NOI18N

        totalTField.setText("total");
        totalTField.setName("total"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.setName("jList1"); // NOI18N
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dataAniverTField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nomeTField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(idadeTField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(salarioTField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(nArquivosTField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                .addComponent(shortTField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                .addComponent(fmCBox, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(totalTField, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(259, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(nomeTField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(dataAniverTField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salarioTField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nArquivosTField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idadeTField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(shortTField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fmCBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalTField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//	TestPessoa p = new TestPessoa();
//        try {
//            Bindings.analisarBean(p);
//            System.out.println(p);
//        } catch (Exception ex) {
//            Logger.getLogger(TestFormBinding.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jList1ValueChanged

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestFormBinding().setVisible(true);
            }
        });
    }
		

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField dataAniverTField;
    private javax.swing.JCheckBox fmCBox;
    private javax.swing.JTextField idadeTField;
    private javax.swing.JButton jButton1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nArquivosTField;
    private javax.swing.JTextField nomeTField;
    private javax.swing.JTextField salarioTField;
    private javax.swing.JTextField shortTField;
    private javax.swing.JTextField totalTField;
    // End of variables declaration//GEN-END:variables

}
