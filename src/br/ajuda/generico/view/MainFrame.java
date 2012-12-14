/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainFrame.java
 *
 * Created on 15/11/2012, 08:18:14
 */
package br.ajuda.generico.view;

import br.ajuda.generico.beansbinding.Bindings;
import br.ajuda.generico.controller.SubTemaController;
import br.ajuda.generico.controller.TemaController;
import br.ajuda.generico.entities.SubTema;
import br.ajuda.generico.entities.Tema;
import br.ajuda.generico.util.AbstractFrame;
import br.ajuda.generico.util.JMessageUtil;
import br.ajuda.generico.util.ListSupport;
import br.ajuda.generico.util.StringHelper;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author jacob
 */
public class MainFrame extends AbstractFrame {

    public static final String OBJ_TEMA = "tema";
    public static final String OPER_TEMA = "operacao_tema";
    public static final int OPER_ADIC_TEMA = 1;
    public static final int OPER_ALT_TEMA = 2;
    public static final String COMP_ADIC_TEMA_JLIST = "adicTemaList";
    public static final String OBJ_SUBTEMA = "subtema";
    public static int OPER_SUBTEMA;
    public static final int OPER_ADIC_SUBTEMA = 1;
    public static final int OPER_ALT_SUBTEMA = 2;
    public static final String COMP_ADIC_SUBTEMA_JLIST = "adicSubTemaList";
    private TemaController temaController;
    private SubTemaController subTemaController;
    private ListSupport adicTemaLSupport;

    /** Creates new form MainFrame */
    public MainFrame() {
        initComponents();
        adicTemaList.getList().setName("tema");
        //LISTENERS
        adicTemaList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (adicTemaList.getList().isSelectionEmpty()) {
                    estadoAdicTema();
                } else {
                    estadoAltExclTema();
                }
            }
        });
        adicSubTemaList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (adicSubTemaList.getList().isSelectionEmpty()) {
                    //estadoAdicSubTema();
                    estadoPadraoSubTema();
                    estadoControleEdicaoTexto(false);
                    excSubTemaButton.setEnabled(false);
                    estadoEdicaoTButton.setVisible(false);
                } else {
                    //estadoControleEdicaoTexto(true);
                    excSubTemaButton.setEnabled(true);
                    estadoEdicaoTButton.setVisible(true);
                    cancelSubTemaButton.setVisible(false);
                    //estadoAltExclSubTema();
                }
            }
        });

        adicTemaList.getList().addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                popularSubTemaLista();
                adicSubTemaButton.setEnabled(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        adicTemaList.getList().addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    popularSubTemaLista();
                    adicSubTemaButton.setEnabled(true);
                }
            }
        });
        adicSubTemaList.getList().addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                visualisarEditor();
                visualisarControleEdicaoTexto(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        adicSubTemaList.getList().addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    visualisarEditor();
                }
            }
        });


        // CELLRENDERS
        adicTemaList.setCellRenderer(new ListCellRenderer() {

            DefaultListCellRenderer renderer = new DefaultListCellRenderer();

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Tema) {
                    Tema t = (Tema) value;
                    return renderer.getListCellRendererComponent(list, t.getTitulo(), index, isSelected, cellHasFocus);
                } else {
                    return renderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                }
            }
        });
        adicSubTemaList.setCellRenderer(new ListCellRenderer() {

            DefaultListCellRenderer renderer = new DefaultListCellRenderer();

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof SubTema) {
                    SubTema t = (SubTema) value;
                    return renderer.getListCellRendererComponent(list, t.getTitulo(), index, isSelected, cellHasFocus);
                } else {
                    return renderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                }
            }
        });

        // CONTROLE DE VISIBILIDADE DOS COMPONENTES
        visualisarControleEdicaoTexto(false);
        estadoEdicaoTButton.setVisible(false);
        //BINDINGS
        Bindings.adicLigacao(tituloSubTemaTField, "text", null, String.class);
        Bindings.adicLigacao(editorPanel, "text", null, String.class);
        Bindings.adicLigacao(adicTemaList.getList(), "selectedValue", null, Tema.class);

    }

    private void visualisarControleEdicaoTexto(boolean b) {
        editorSPane.setVisible(b);
        controleEdicaoTextoPanel.setVisible(b);
    }

    private void visualisarEditor() {
        editorPanel.setText(adicSubTemaList.getItemSelecionado().getDescricao());
    }

    private void popularSubTemaLista() {
        try {
            List<SubTema> subTemaList = subTemaController.consultaLista(new SubTema(adicTemaList.getItemSelecionado().getId()));
            adicSubTemaList.removeAllItens();
            adicSubTemaList.adicionarItens(subTemaList);
        } catch (Exception ex) {
            controladorDespacho.registraEexibe(ex);
        }
    }

    //controle de estado do tema
    public void estadoAdicTema() {
        adicTemaButton.setEnabled(true);
        altTemaButton.setEnabled(false);
        excTemaButton.setEnabled(false);
        cancelTemaButton.setEnabled(false);
    }

    public void estadoAltExclTema() {
        adicTemaButton.setEnabled(false);
        altTemaButton.setEnabled(true);
        excTemaButton.setEnabled(true);
        cancelTemaButton.setEnabled(true);
    }

    public void estadoExclTema() {
        adicTemaButton.setEnabled(false);
        altTemaButton.setEnabled(false);
        excTemaButton.setEnabled(true);
        cancelTemaButton.setEnabled(true);
    }

    //controle de estado do subtema
    public void estadoPadraoSubTema() {
        adicSubTemaButton.setEnabled(true);
        altSubTemaButton.setEnabled(false);
        excSubTemaButton.setEnabled(false);
        cancelSubTemaButton.setEnabled(false);
    }

    public void estadoAdicSubTema() {
        adicSubTemaButton.setEnabled(false);
        altSubTemaButton.setEnabled(false);
        excSubTemaButton.setEnabled(false);
        cancelSubTemaButton.setEnabled(true);
    }

    public void estadoAltSubTema() {
        adicSubTemaButton.setEnabled(false);
        altSubTemaButton.setEnabled(true);
        //excSubTemaButton.setEnabled(true);
        cancelSubTemaButton.setEnabled(true);
    }

    public void estadoExclSubTema() {
        adicSubTemaButton.setEnabled(false);
        altSubTemaButton.setEnabled(false);
        excSubTemaButton.setEnabled(true);
        cancelSubTemaButton.setEnabled(true);
    }

    public void estadoControleEdicaoTexto(boolean b) {
        tituloSubTemaTField.setEditable(b);
        salvarSubTemaButton.setEnabled(b);
        editorPanel.setEditable(b);
        tituloSubTemaTField.setRequestFocusEnabled(b);
        //estadoEdicaoTButton.setSelected(b);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        altSubTemaButton = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        adicTemaList = new br.com.swing.componentes.personalizados.ui.ListaComFiltro<Tema>();
        adicSubTemaList = new br.com.swing.componentes.personalizados.ui.ListaComFiltro<SubTema>();
        jPanel4 = new javax.swing.JPanel();
        adicTemaButton = new javax.swing.JButton();
        altTemaButton = new javax.swing.JButton();
        excTemaButton = new javax.swing.JButton();
        cancelTemaButton = new javax.swing.JButton();
        jXTitledSeparator1 = new org.jdesktop.swingx.JXTitledSeparator();
        jXTitledSeparator2 = new org.jdesktop.swingx.JXTitledSeparator();
        jPanel5 = new javax.swing.JPanel();
        adicSubTemaButton = new javax.swing.JButton();
        excSubTemaButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        editorSPane = new javax.swing.JScrollPane();
        editorPanel = new javax.swing.JEditorPane();
        controleEdicaoTextoPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tituloSubTemaTField = new javax.swing.JTextField();
        salvarSubTemaButton = new javax.swing.JButton();
        cancelSubTemaButton = new javax.swing.JButton();
        estadoEdicaoTButton = new javax.swing.JToggleButton();
        jXStatusBar1 = new org.jdesktop.swingx.JXStatusBar();
        jLabel1 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        altSubTemaButton.setText("ALT");
        altSubTemaButton.setEnabled(false);
        altSubTemaButton.setName("altSubTemaButton"); // NOI18N
        altSubTemaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                altSubTemaButtonActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ajuda v.1.0");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jSplitPane1.setDividerLocation(250);
        jSplitPane1.setName("jSplitPane1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        adicTemaList.setLabelText("Filtrar: ");
        adicTemaList.setNomeCampo("tema");

        adicSubTemaList.setName("adicSubTemaList"); // NOI18N

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setName("jPanel4"); // NOI18N

        adicTemaButton.setText("AD");
        adicTemaButton.setName("adicTemaButton"); // NOI18N
        adicTemaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicTemaButtonActionPerformed(evt);
            }
        });
        jPanel4.add(adicTemaButton);

        altTemaButton.setText("ALT");
        altTemaButton.setEnabled(false);
        altTemaButton.setName("altTemaButton"); // NOI18N
        altTemaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                altTemaButtonActionPerformed(evt);
            }
        });
        jPanel4.add(altTemaButton);

        excTemaButton.setText("EXC");
        excTemaButton.setEnabled(false);
        excTemaButton.setName("excTemaButton"); // NOI18N
        excTemaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excTemaButtonActionPerformed(evt);
            }
        });
        jPanel4.add(excTemaButton);

        cancelTemaButton.setText("CAN");
        cancelTemaButton.setEnabled(false);
        cancelTemaButton.setName("cancelTemaButton"); // NOI18N
        cancelTemaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelTemaButtonActionPerformed(evt);
            }
        });
        jPanel4.add(cancelTemaButton);

        jXTitledSeparator1.setTitle("Subtema");
        jXTitledSeparator1.setName("jXTitledSeparator1"); // NOI18N

        jXTitledSeparator2.setTitle("Tema(Assunto Geral)");
        jXTitledSeparator2.setName("jXTitledSeparator2"); // NOI18N

        jPanel5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setName("jPanel5"); // NOI18N

        adicSubTemaButton.setText("AD");
        adicSubTemaButton.setEnabled(false);
        adicSubTemaButton.setName("adicSubTemaButton"); // NOI18N
        adicSubTemaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicSubTemaButtonActionPerformed(evt);
            }
        });
        jPanel5.add(adicSubTemaButton);

        excSubTemaButton.setText("EXC");
        excSubTemaButton.setEnabled(false);
        excSubTemaButton.setName("excSubTemaButton"); // NOI18N
        excSubTemaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excSubTemaButtonActionPerformed(evt);
            }
        });
        jPanel5.add(excSubTemaButton);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jXTitledSeparator2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                            .addComponent(adicTemaList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jXTitledSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                            .addComponent(adicSubTemaList, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jXTitledSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(adicTemaList, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jXTitledSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adicSubTemaList, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jPanel1);

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setLayout(new java.awt.BorderLayout());

        editorSPane.setName("editorSPane"); // NOI18N

        editorPanel.setEditable(false);
        editorPanel.setName("descricao"); // NOI18N
        editorSPane.setViewportView(editorPanel);

        jPanel2.add(editorSPane, java.awt.BorderLayout.CENTER);

        controleEdicaoTextoPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        controleEdicaoTextoPanel.setName("controleEdicaoTextoPanel"); // NOI18N
        controleEdicaoTextoPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jLabel2.setText("Titulo:");
        jLabel2.setName("jLabel2"); // NOI18N
        controleEdicaoTextoPanel.add(jLabel2);

        tituloSubTemaTField.setEditable(false);
        tituloSubTemaTField.setName("titulo"); // NOI18N
        tituloSubTemaTField.setPreferredSize(new java.awt.Dimension(350, 20));
        controleEdicaoTextoPanel.add(tituloSubTemaTField);

        salvarSubTemaButton.setText("salvar");
        salvarSubTemaButton.setEnabled(false);
        salvarSubTemaButton.setName("salvarSubTemaButton"); // NOI18N
        salvarSubTemaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarSubTemaButtonActionPerformed(evt);
            }
        });
        controleEdicaoTextoPanel.add(salvarSubTemaButton);

        cancelSubTemaButton.setText("cancelar");
        cancelSubTemaButton.setName("cancelSubTemaButton"); // NOI18N
        cancelSubTemaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelSubTemaButtonActionPerformed(evt);
            }
        });
        controleEdicaoTextoPanel.add(cancelSubTemaButton);

        estadoEdicaoTButton.setText("Editar");
        estadoEdicaoTButton.setName("estadoEdicaoTButton"); // NOI18N
        estadoEdicaoTButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                estadoEdicaoTButtonItemStateChanged(evt);
            }
        });
        estadoEdicaoTButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadoEdicaoTButtonActionPerformed(evt);
            }
        });
        controleEdicaoTextoPanel.add(estadoEdicaoTButton);

        jPanel2.add(controleEdicaoTextoPanel, java.awt.BorderLayout.PAGE_START);

        jSplitPane1.setRightComponent(jPanel2);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        jXStatusBar1.setName("jXStatusBar1"); // NOI18N

        jLabel1.setText("Status:");
        jLabel1.setName("jLabel1"); // NOI18N
        jXStatusBar1.add(jLabel1);

        getContentPane().add(jXStatusBar1, java.awt.BorderLayout.SOUTH);

        menuBar.setName("menuBar"); // NOI18N

        jMenu1.setText("Sistema");
        jMenu1.setName("jMenu1"); // NOI18N
        menuBar.add(jMenu1);

        jMenu2.setName("jMenu2"); // NOI18N
        menuBar.add(jMenu2);

        setJMenuBar(menuBar);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-912)/2, (screenSize.height-559)/2, 912, 559);
    }// </editor-fold>//GEN-END:initComponents

    private void adicTemaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicTemaButtonActionPerformed

        controladorDespacho.setParam(OPER_TEMA, OPER_ADIC_TEMA);//se for para adicionar um tema
        controladorDespacho.despachar(new TemaDialog(this, true));

    }//GEN-LAST:event_adicTemaButtonActionPerformed

    private void altTemaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altTemaButtonActionPerformed

        controladorDespacho.setParam(OBJ_TEMA, adicTemaList.getList().getSelectedValue());
        controladorDespacho.setParam(OPER_TEMA, OPER_ALT_TEMA);//se for alteracao
        controladorDespacho.despachar(new TemaDialog(this, true));

    }//GEN-LAST:event_altTemaButtonActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        try {
            temaController = new TemaController();
            subTemaController = new SubTemaController();

            adicTemaList.adicionarItens(temaController.consultaLista(new Tema()));
            controladorDespacho.setParam(COMP_ADIC_TEMA_JLIST, adicTemaList);
            controladorDespacho.setParam(COMP_ADIC_SUBTEMA_JLIST, adicSubTemaList);
        } catch (Exception ex) {
            controladorDespacho.registraEexibe(ex);
        }

    }//GEN-LAST:event_formWindowOpened

    private void excTemaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excTemaButtonActionPerformed

        try {
            if (JMessageUtil.showConfirm("Você tem certeza que deseja deletar este tema:'"
                    + StringHelper.getFraseStringLimitado(adicTemaList.getItemSelecionado().getTitulo(), 30) + "'?") == JOptionPane.OK_OPTION) {
                temaController.excluir(adicTemaList.getItemSelecionado());
                adicTemaList.removerItem(adicTemaList.getItemSelecionado());
                adicSubTemaList.removeAllItens();
                estadoAdicSubTema();
            }
        } catch (Exception ex) {
            controladorDespacho.registraEexibe(ex);
        }

    }//GEN-LAST:event_excTemaButtonActionPerformed

    private void cancelTemaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelTemaButtonActionPerformed

        adicTemaList.getList().clearSelection();
        adicSubTemaList.removeAllItens();
        estadoAdicTema();
        estadoAdicSubTema();

    }//GEN-LAST:event_cancelTemaButtonActionPerformed

    private void adicSubTemaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicSubTemaButtonActionPerformed

        if (adicTemaList.getListModel().isEmpty()) {
            controladorDespacho.alertaMsg("adicione um tema!");
            controladorDespacho.exibirMsgs();
            return;
        }
        OPER_SUBTEMA = OPER_ADIC_SUBTEMA;
        visualisarControleEdicaoTexto(true);
        estadoControleEdicaoTexto(true);
        estadoAdicSubTema();
        cancelSubTemaButton.setVisible(true);
        adicSubTemaList.getList().clearSelection();
        
//        controladorDespacho.setParam(OPER_SUBTEMA, OPER_ADIC_SUBTEMA);//se for para adicionar um subtema
//        controladorDespacho.despachar(new SubTemaDialog(this, true));

    }//GEN-LAST:event_adicSubTemaButtonActionPerformed

    private void altSubTemaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_altSubTemaButtonActionPerformed
//        controladorDespacho.setParam(OBJ_SUBTEMA, adicSubTemaList.getList().getSelectedValue());
//        controladorDespacho.setParam(OPER_SUBTEMA, OPER_ALT_SUBTEMA);//se for alteracao
//        controladorDespacho.despachar(new SubTemaDialog(this, true));
    }//GEN-LAST:event_altSubTemaButtonActionPerformed

    private void excSubTemaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excSubTemaButtonActionPerformed

        try {
            if (JMessageUtil.showConfirm("Você tem certeza que deseja deletar este subtema:'"
                    + StringHelper.getFraseStringLimitado(adicSubTemaList.getItemSelecionado().getTitulo(), 30) + "'?") == JOptionPane.OK_OPTION) {
                subTemaController.excluir(adicSubTemaList.getItemSelecionado());
                adicSubTemaList.removerItem(adicSubTemaList.getItemSelecionado());
            }
        } catch (Exception ex) {
            controladorDespacho.registraEexibe(ex);
        }

    }//GEN-LAST:event_excSubTemaButtonActionPerformed

    private void cancelSubTemaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelSubTemaButtonActionPerformed

       // if (OPER_SUBTEMA == OPER_ADIC_SUBTEMA) {
            tituloSubTemaTField.setText(null);
            editorPanel.setText(null);
            estadoControleEdicaoTexto(false);
            visualisarControleEdicaoTexto(false);
            estadoPadraoSubTema();
      //  }


    }//GEN-LAST:event_cancelSubTemaButtonActionPerformed

    private void salvarSubTemaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarSubTemaButtonActionPerformed

        try {

            SubTema subTema = new SubTema();

            //se a operacao for adicionar o tema
            if (OPER_SUBTEMA == OPER_ADIC_SUBTEMA) {
                Bindings.analisarBean(subTema);
                subTema = subTemaController.salvar(subTema);
                //adiciono o tema na lista da janela principal
                adicSubTemaList.adicionarItem(subTema);
                adicSubTemaList.getList().setSelectedValue(subTema, true);
                //estadoControleEdicaoTexto(false);
                estadoPadraoSubTema();
                JMessageUtil.showSucessMessage(this, "SubTema:'"
                        + StringHelper.getFraseStringLimitado(subTema.getTitulo(), 30) + "', inserido com sucesso!");


            } else {//senao altera o tema
//                subTema = (SubTema) controladorDespacho.getParam(MainFrame.OBJ_SUBTEMA);
//                Bindings.analisarBean(subTema);
//                subTemaController.alterar(subTema);
//                //altero o tema na lista da janela principal
//                ListaComFiltro<SubTema> adicTemaList = (ListaComFiltro) controladorDespacho.getParam(MainFrame.COMP_ADIC_SUBTEMA_JLIST);
//
//                adicTemaList.alterarItem(subTema, adicTemaList.getList().getSelectedIndex());
//
//                JMessageUtil.showSucessMessage(this, "SubTema:'"
//                        + StringHelper.getFraseStringLimitado(subTema.getTitulo(), 30) + "', alterado com sucesso!");
//
//                dispose();
            }

            editorPanel.setRequestFocusEnabled(true);
        } catch (Exception ex) {
            this.controladorDespacho.registraEexibe(ex);
        }

    }//GEN-LAST:event_salvarSubTemaButtonActionPerformed

    private void estadoEdicaoTButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadoEdicaoTButtonActionPerformed

        

    }//GEN-LAST:event_estadoEdicaoTButtonActionPerformed

    private void estadoEdicaoTButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_estadoEdicaoTButtonItemStateChanged

        if (estadoEdicaoTButton.isSelected()) {
            estadoControleEdicaoTexto(true);
        }else{
            estadoControleEdicaoTexto(false);
        }

    }//GEN-LAST:event_estadoEdicaoTButtonItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicSubTemaButton;
    private br.com.swing.componentes.personalizados.ui.ListaComFiltro<SubTema> adicSubTemaList;
    private javax.swing.JButton adicTemaButton;
    private br.com.swing.componentes.personalizados.ui.ListaComFiltro<Tema> adicTemaList;
    private javax.swing.JButton altSubTemaButton;
    private javax.swing.JButton altTemaButton;
    private javax.swing.JButton cancelSubTemaButton;
    private javax.swing.JButton cancelTemaButton;
    private javax.swing.JPanel controleEdicaoTextoPanel;
    private javax.swing.JEditorPane editorPanel;
    private javax.swing.JScrollPane editorSPane;
    private javax.swing.JToggleButton estadoEdicaoTButton;
    private javax.swing.JButton excSubTemaButton;
    private javax.swing.JButton excTemaButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSplitPane jSplitPane1;
    private org.jdesktop.swingx.JXStatusBar jXStatusBar1;
    private org.jdesktop.swingx.JXTitledSeparator jXTitledSeparator1;
    private org.jdesktop.swingx.JXTitledSeparator jXTitledSeparator2;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton salvarSubTemaButton;
    private javax.swing.JTextField tituloSubTemaTField;
    // End of variables declaration//GEN-END:variables
}
