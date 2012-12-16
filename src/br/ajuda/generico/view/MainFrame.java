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
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author jacob
 */
public class MainFrame extends AbstractFrame implements DocumentListener {

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
    private Bindings bindings;
    private boolean isHouveAlteracaoEdicaoTexto = false;

    /** Creates new form MainFrame */
    public MainFrame() {
        initComponents();
        bindings = new Bindings();
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

        adicTemaList.getList().addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!adicTemaList.getList().isSelectionEmpty()) {
                    popularSubTemaLista();
                    adicSubTemaButton.setEnabled(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!adicTemaList.getList().isSelectionEmpty()) {
                    popularSubTemaLista();
                    adicSubTemaButton.setEnabled(true);
                }
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
        adicSubTemaList.getList().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                //TODO implementar uma maneira de avisar ao usuario quando ele mundo de selecao,
                //para quando estiver no mode de edicao o formulario de texto avisar que
                //os dados irao se perder

                if (!adicSubTemaList.getList().isSelectionEmpty()) {
//                    int opcao = confirmaAlteracao();
//                    if (opcao == 0) {
//                        JMessageUtil.showInputMessage(MainFrame.this, "change");
//                    }
                }
            }
        });
        adicSubTemaList.getList().addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!adicSubTemaList.getList().isSelectionEmpty()) {
                    popularDadosPainelEdicao();
                    estadoExcSubTema();
                    visualisarControleEdicaoTexto(true);
                    modoLeituraControleEdicaoTexto(true);
                    estadoEdicaoTButton.setVisible(true);
                    estadoEdicaoTButton.setSelected(false);
                    salvarSubTemaButton.setEnabled(false);
                    ativarListenerEdicaoTexto(false);
                    isHouveAlteracaoEdicaoTexto = false;
                }
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
                    popularDadosPainelEdicao();
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
        bindings.adicLigacao(tituloSubTemaTField, "text", null, String.class);
        bindings.adicLigacao(editorPanel, "text", null, String.class);
        bindings.adicLigacao(adicTemaList.getList(), "selectedValue", null, Tema.class);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Ajuda v.1.0");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
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

        jMenuItem1.setText("Sair");
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

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
        try {

            if (isHouveAlteracaoEdicaoTexto) {
                int opcao = confirmaAlteracao();
                if (opcao == 0) {
                    rollbackEdicaodeTexto();
                    modoLeituraControleEdicaoTexto(true);
                    salvarSubTemaButton.setEnabled(false);
                    cancelSubTemaButton.setVisible(false);
                    isHouveAlteracaoEdicaoTexto = false;
                    ativarListenerEdicaoTexto(false);
                    limparCamposControleEdicaoTexto();
                } else {
                    estadoEdicaoTButton.setSelected(true);
                    return;
                }
            }

            visualisarControleEdicaoTexto(false);
            limparCamposControleEdicaoTexto();
            adicTemaList.getList().clearSelection();
            adicSubTemaList.removeAllItens();
            estadoAdicTema();
            estadoAdicSubTema();
            isHouveAlteracaoEdicaoTexto = false;
        } catch (Exception ex) {
            controladorDespacho.registraEexibe(ex);

        }

    }//GEN-LAST:event_cancelTemaButtonActionPerformed

    private void adicSubTemaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicSubTemaButtonActionPerformed

        if (adicTemaList.getListModel().isEmpty()) {
            controladorDespacho.alertaMsg("adicione um tema!");
            controladorDespacho.exibirMsgs();
            return;
        }
        if (isHouveAlteracaoEdicaoTexto) {
            int opcao = JMessageUtil.showOptionDialog(this, "Seus dados do formulario de edição de texto irão se perder, você deseja realmente continuar com esta operação?", "Confirmação de cancelamento de inserção", "Não", new Object[]{"Sim", "Não"}, JMessageUtil.TIPO_IMG_PERGUNTA);
            if (opcao == 1) {
                return;
            }
        }

        OPER_SUBTEMA = OPER_ADIC_SUBTEMA;
        limparCamposControleEdicaoTexto();
        visualisarControleEdicaoTexto(true);
        modoLeituraControleEdicaoTexto(false);
        salvarSubTemaButton.setEnabled(false);
        estadoPadraoSubTema();
        estadoEdicaoTButton.setVisible(false);
        adicSubTemaList.getList().clearSelection();
        tituloSubTemaTField.setRequestFocusEnabled(true);
        ativarListenerEdicaoTexto(true);

//        controladorDespacho.setParam(OPER_SUBTEMA, OPER_ADIC_SUBTEMA);//se for para adicionar um subtema
//        controladorDespacho.despachar(new SubTemaDialog(this, true));

    }//GEN-LAST:event_adicSubTemaButtonActionPerformed

    private void excSubTemaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excSubTemaButtonActionPerformed

        try {
            if (JMessageUtil.showConfirm("Você tem certeza que deseja deletar este subtema:'"
                    + StringHelper.getFraseStringLimitado(adicSubTemaList.getItemSelecionado().getTitulo(), 30) + "'?") == JOptionPane.OK_OPTION) {
                subTemaController.excluir(adicSubTemaList.getItemSelecionado());
                adicSubTemaList.removerItem(adicSubTemaList.getItemSelecionado());
                estadoPadraoSubTema();
                modoLeituraControleEdicaoTexto(true);
                limparCamposControleEdicaoTexto();
                visualisarControleEdicaoTexto(false);
                estadoEdicaoTButton.setSelected(false);
                salvarSubTemaButton.setEnabled(false);
                ativarListenerEdicaoTexto(false);
                isHouveAlteracaoEdicaoTexto = false;
            }
        } catch (Exception ex) {
            controladorDespacho.registraEexibe(ex);
        }

    }//GEN-LAST:event_excSubTemaButtonActionPerformed

    private void cancelSubTemaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelSubTemaButtonActionPerformed

        limparCamposControleEdicaoTexto();
        modoLeituraControleEdicaoTexto(true);
        visualisarControleEdicaoTexto(false);
        estadoPadraoSubTema();
        estadoEdicaoTButton.setVisible(false);
        ativarListenerEdicaoTexto(false);
        isHouveAlteracaoEdicaoTexto = false;

    }//GEN-LAST:event_cancelSubTemaButtonActionPerformed

    private void salvarSubTemaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarSubTemaButtonActionPerformed

        try {

            SubTema subTema = new SubTema();

            //se a operacao for adicionar o tema
            if (OPER_SUBTEMA == OPER_ADIC_SUBTEMA) {
                bindings.analisarBean(subTema);
                subTema = subTemaController.salvar(subTema);
                //A2.2 - operacao salvar
                //1 - adicionar item na lista de subtemas
                adicSubTemaList.adicionarItem(subTema);
                //2 - selecionar o valor adicionado na lista
                adicSubTemaList.getList().setSelectedValue(subTema, true);
                visualisarControleEdicaoTexto(true);
                modoLeituraControleEdicaoTexto(true);
                estadoExcSubTema();

                estadoEdicaoTButton.setVisible(true);
                JMessageUtil.showSucessMessage(this, "SubTema:'"
                        + StringHelper.getFraseStringLimitado(subTema.getTitulo(), 30) + "', inserido com sucesso!");


            } else {//senao altera o tema
                subTema = adicSubTemaList.getItemSelecionado();
                bindings.analisarBean(subTema);
                subTemaController.alterar(subTema);

                adicSubTemaList.alterarItem(subTema, adicSubTemaList.getList().getSelectedIndex());
                adicSubTemaList.getList().setSelectedValue(subTema, true);
                modoLeituraControleEdicaoTexto(true);
                JMessageUtil.showSucessMessage(this, "SubTema:'"
                        + StringHelper.getFraseStringLimitado(subTema.getTitulo(), 30) + "', alterado com sucesso!");
            }
            isHouveAlteracaoEdicaoTexto = false;
            ativarListenerEdicaoTexto(false);
            estadoEdicaoTButton.setSelected(false);
            salvarSubTemaButton.setEnabled(false);
            editorPanel.setRequestFocusEnabled(true);
        } catch (Exception ex) {
            this.controladorDespacho.registraEexibe(ex);
        }

    }//GEN-LAST:event_salvarSubTemaButtonActionPerformed

    private void estadoEdicaoTButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadoEdicaoTButtonActionPerformed
        try {
            OPER_SUBTEMA = OPER_ALT_SUBTEMA;
            if (!estadoEdicaoTButton.isSelected() && isHouveAlteracaoEdicaoTexto) {
                int opcao = confirmaAlteracao();
                if (opcao == 0) {
                    rollbackEdicaodeTexto();
                    modoLeituraControleEdicaoTexto(true);
                    salvarSubTemaButton.setEnabled(false);
                    cancelSubTemaButton.setVisible(false);
                    isHouveAlteracaoEdicaoTexto = false;
                    ativarListenerEdicaoTexto(false);
                    return;
                } else {
                    estadoEdicaoTButton.setSelected(true);
                    return;
                }
            }
            modoLeituraControleEdicaoTexto(!estadoEdicaoTButton.isSelected());
            cancelSubTemaButton.setVisible(false);
            salvarSubTemaButton.setEnabled(estadoEdicaoTButton.isSelected() && isHouveAlteracaoEdicaoTexto);
            ativarListenerEdicaoTexto(estadoEdicaoTButton.isSelected());
        } catch (Exception ex) {
            controladorDespacho.registraEexibe(ex);
        }

    }//GEN-LAST:event_estadoEdicaoTButtonActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        //TODO quando o usuario tentar sair avisar se caso ele alterou algum dado no formulario de edicao - 2
        System.exit(0);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        //TODO quando o usuario tentar sair avisar se caso ele alterou algum dado no formulario de edicao - 1
        if (JMessageUtil.showConfirm("Deseja realmente sair?") == JOptionPane.OK_OPTION) {
            System.exit(0);
        }

    }//GEN-LAST:event_formWindowClosing

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
    private javax.swing.JMenuItem jMenuItem1;
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

    private void visualisarControleEdicaoTexto(boolean b) {
        editorSPane.setVisible(b);
        controleEdicaoTextoPanel.setVisible(b);
    }

    private void popularDadosPainelEdicao() {
        SubTema s = adicSubTemaList.getItemSelecionado();
        if (s == null) {
            return;
        }
        tituloSubTemaTField.setText(s.getTitulo());
        editorPanel.setText(s.getDescricao());
    }

    private void popularSubTemaLista() {
        try {
            if (adicTemaList.getList().isSelectionEmpty()) {
                return;
            }
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
        excSubTemaButton.setEnabled(false);
    }

    public void estadoAdicSubTema() {
        adicSubTemaButton.setEnabled(false);
        excSubTemaButton.setEnabled(false);
    }

    public void estadoExcSubTema() {
        adicSubTemaButton.setEnabled(true);
        excSubTemaButton.setEnabled(true);
    }

    public void modoLeituraControleEdicaoTexto(boolean b) {
        tituloSubTemaTField.setEditable(!b);
        //salvarSubTemaButton.setEnabled(!b);
        editorPanel.setEditable(!b);
        cancelSubTemaButton.setVisible(!b);
        editorPanel.setRequestFocusEnabled(!b);
    }

    private void limparCamposControleEdicaoTexto() {
        tituloSubTemaTField.setText(null);
        editorPanel.setText(null);
    }

    private void rollbackEdicaodeTexto() throws Exception {
        SubTema subTema = subTemaController.consultaUnicoRetorno(adicSubTemaList.getItemSelecionado());
        tituloSubTemaTField.setText(subTema.getTitulo());
        editorPanel.setText(subTema.getDescricao());
    }

    //listener dos componentes: editorPane e tituloSubTemaTField.
    private void ativarListenerEdicaoTexto(boolean b) {
        if (b) {
            editorPanel.getDocument().addDocumentListener(this);
            tituloSubTemaTField.getDocument().addDocumentListener(this);
        } else {
            editorPanel.getDocument().removeDocumentListener(this);
            tituloSubTemaTField.getDocument().removeDocumentListener(this);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        isHouveAlteracaoEdicaoTexto = true;
        salvarSubTemaButton.setEnabled(true);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        isHouveAlteracaoEdicaoTexto = true;
        salvarSubTemaButton.setEnabled(true);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        isHouveAlteracaoEdicaoTexto = true;
        salvarSubTemaButton.setEnabled(true);
    }

    private int confirmaAlteracao() {
        return JMessageUtil.showOptionDialog(this, "Houve alteração no formulario de edição,"
                + "você deseja realmente cancelar?", "Confirmação de Cancelamento alteração", "Não", new Object[]{"Sim", "Não"}, JMessageUtil.TIPO_IMG_PERGUNTA);
    }
}
