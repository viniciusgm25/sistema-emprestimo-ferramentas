/*
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    // Variables declaration - do not modify                     
    // End of variables declaration                   
*/
package Telas;

import Controles.Produto;
import Controles.UpperCaseDocumentFilter;
import Persistencia.dao;
import Utils.GerenciadorArquivos;
import java.awt.Image;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

public class TProduto extends javax.swing.JFrame {

    private String caminhoDaImagemSelecionada = null;

    public TProduto() {
        initComponents();
        setLocationRelativeTo(null);
        limparCampos();
        pesquisarProdutos();
        
         // Pega o "documento" interno do campo e aplica o nosso filtro
        ((AbstractDocument) campoValorDiaria.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoPesquisa.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoNome.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoMarca.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoDescricao.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoCodigoOriginal.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoCodigo.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
    }

    private void limparCampos() {
        campoCodigo.setText("");
        campoNome.setText("");
        campoDescricao.setText("");
        campoMarca.setText("");
        campoCodigoOriginal.setText("");
        campoValorDiaria.setText("");
        jLabelImagemPreview.setIcon(null);
        jLabelImagemPreview.setText("Sem Imagem");
        caminhoDaImagemSelecionada = null;
        campoNome.requestFocus();
        bExcluir1.setEnabled(false);
        jTabbedPane1.setSelectedIndex(0);
    }

    private void exibirImagem() {
        if (caminhoDaImagemSelecionada != null && !caminhoDaImagemSelecionada.isEmpty()) {
            ImageIcon icon = new ImageIcon(caminhoDaImagemSelecionada);
            Image imagem = icon.getImage().getScaledInstance(jLabelImagemPreview.getWidth(), jLabelImagemPreview.getHeight(), Image.SCALE_SMOOTH);
            jLabelImagemPreview.setIcon(new ImageIcon(imagem));
            jLabelImagemPreview.setText("");
        } else {
            jLabelImagemPreview.setIcon(null);
            jLabelImagemPreview.setText("Sem Imagem");
        }
    }

    private void preencherFormulario(Produto p) {
        if (p != null) {
            campoCodigo.setText(p.getCodigo().toString());
            campoNome.setText(p.getNome());
            campoDescricao.setText(p.getDescricao());
            campoMarca.setText(p.getMarca());
            campoCodigoOriginal.setText(p.getCodigoOriginal());
            campoValorDiaria.setText(p.getValorDiaria() != null ? p.getValorDiaria().toPlainString() : "0.00");
            caminhoDaImagemSelecionada = p.getCaminhoImagem();
            exibirImagem();
            bExcluir1.setEnabled(true);
        }
    }

    private void salvarProduto() {
        if (campoNome.getText().trim().isEmpty() || campoValorDiaria.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome e Valor Diária são obrigatórios.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Produto p;
            if (campoCodigo.getText().trim().isEmpty()) {
                p = new Produto();
            } else {
                p = (Produto) dao.consultar(Produto.class, Integer.parseInt(campoCodigo.getText()));
            }

            p.setNome(campoNome.getText());
            p.setDescricao(campoDescricao.getText());
            p.setMarca(campoMarca.getText());
            p.setCodigoOriginal(campoCodigoOriginal.getText());
            p.setValorDiaria(new BigDecimal(campoValorDiaria.getText().replace(",", ".")));
            p.setCaminhoImagem(caminhoDaImagemSelecionada);

            p = (Produto) dao.salvar(p);

            if (caminhoDaImagemSelecionada != null && caminhoDaImagemSelecionada.contains("_temp_")) {
                String novoCaminho = GerenciadorArquivos.renomear(caminhoDaImagemSelecionada, "produto", p.getCodigo());
                p.setCaminhoImagem(novoCaminho);
                dao.salvar(p);
            }

            JOptionPane.showMessageDialog(this, "Produto salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
            pesquisarProdutos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao salvar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void excluirProduto() {
        if (campoCodigo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Consulte um produto antes de excluir.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir este produto?", "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            try {
                int codigo = Integer.parseInt(campoCodigo.getText());
                Produto p = (Produto) dao.consultar(Produto.class, codigo);
                dao.excluir(p);
                JOptionPane.showMessageDialog(this, "Produto excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
                pesquisarProdutos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao excluir. Verifique se o produto não está em um empréstimo ativo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void pesquisarProdutos() {
        DefaultTableModel model = (DefaultTableModel) jTablePesquisa.getModel();
        model.setRowCount(0);
        String termoPesquisa = "%" + campoPesquisa.getText() + "%";
        List<Produto> lista = dao.listar("Produto.pesquisaNome", "nome", termoPesquisa);
        for (Produto p : lista) {
            model.addRow(new Object[]{
                p.getCodigo(),
                p.getNome(),
                p.getMarca(),
                p.getValorDiaria()
            });
        }
    }

    private void consultarProdutoPeloCodigo() {
        String codigoStr = JOptionPane.showInputDialog(this, "Digite o código do produto:");
        if (codigoStr != null && !codigoStr.trim().isEmpty()) {
            try {
                int codigo = Integer.parseInt(codigoStr);
                Produto p = (Produto) dao.consultar(Produto.class, codigo);
                if (p != null) {
                    preencherFormulario(p);
                    jTabbedPane1.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(this, "Produto não encontrado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Código inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        rotulo1 = new padraotela.Rotulo();
        campoCodigo = new padraotela.Campo();
        rotulo2 = new padraotela.Rotulo();
        campoNome = new padraotela.Campo();
        rotulo3 = new padraotela.Rotulo();
        rotulo4 = new padraotela.Rotulo();
        campoMarca = new padraotela.Campo();
        rotulo5 = new padraotela.Rotulo();
        campoCodigoOriginal = new padraotela.Campo();
        rotulo6 = new padraotela.Rotulo();
        campoValorDiaria = new padraotela.Campo();
        bSalvar1 = new padraotela.BSalvar();
        bLimpar1 = new padraotela.BLimpar();
        bExcluir1 = new padraotela.BExcluir();
        bSair1 = new padraotela.BSair();
        jScrollPane1 = new javax.swing.JScrollPane();
        campoDescricao = new javax.swing.JTextArea();
        jLabelImagemPreview = new javax.swing.JLabel();
        bSelecionarImagem = new javax.swing.JButton();
        bConsultar1 = new padraotela.BConsultar();
        jPanel2 = new javax.swing.JPanel();
        rotulo7 = new padraotela.Rotulo();
        campoPesquisa = new padraotela.Campo();
        bPesquisar = new padraotela.BConsultar();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePesquisa = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Produtos");

        rotulo1.setText("Código:");

        campoCodigo.setEditable(false);
        campoCodigo.setBackground(new java.awt.Color(220, 220, 220));

        rotulo2.setText("Nome:*");

        rotulo3.setText("Descrição:");

        rotulo4.setText("Marca:");

        rotulo5.setText("Cód. Original:");

        rotulo6.setText("Valor Diária (R$):*");

        bSalvar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalvar1ActionPerformed(evt);
            }
        });

        bLimpar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLimpar1ActionPerformed(evt);
            }
        });

        bExcluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExcluir1ActionPerformed(evt);
            }
        });

        bSair1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSair1ActionPerformed(evt);
            }
        });

        campoDescricao.setColumns(20);
        campoDescricao.setRows(5);
        jScrollPane1.setViewportView(campoDescricao);

        jLabelImagemPreview.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImagemPreview.setText("Sem Imagem");
        jLabelImagemPreview.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        bSelecionarImagem.setText("Selecionar Imagem...");
        bSelecionarImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSelecionarImagemActionPerformed(evt);
            }
        });

        bConsultar1.setText("Consultar");
        bConsultar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConsultar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bSalvar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bLimpar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                        .addComponent(bSair1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rotulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rotulo2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rotulo3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rotulo4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rotulo5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rotulo6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bConsultar1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCodigoOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoValorDiaria, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelImagemPreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bSelecionarImagem, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rotulo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bConsultar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rotulo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rotulo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rotulo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rotulo5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCodigoOriginal, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelImagemPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bSelecionarImagem)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotulo6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoValorDiaria, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSalvar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bLimpar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bSair1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jTabbedPane1.addTab("Cadastro", jPanel1);

        rotulo7.setText("Pesquisar por nome:");

        bPesquisar.setText("Pesquisar");
        bPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPesquisarActionPerformed(evt);
            }
        });

        jTablePesquisa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Marca", "Valor Diária"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePesquisaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTablePesquisa);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rotulo7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(bPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotulo7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Pesquisa", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSalvar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalvar1ActionPerformed
        salvarProduto();
    }//GEN-LAST:event_bSalvar1ActionPerformed

    private void bLimpar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLimpar1ActionPerformed
        limparCampos();
    }//GEN-LAST:event_bLimpar1ActionPerformed

    private void bExcluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExcluir1ActionPerformed
        excluirProduto();
    }//GEN-LAST:event_bExcluir1ActionPerformed

    private void bSair1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSair1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_bSair1ActionPerformed

    private void bSelecionarImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSelecionarImagemActionPerformed
        JFileChooser seletor = new JFileChooser();
        seletor.setFileFilter(new FileNameExtensionFilter("Imagens", "jpg", "png", "gif", "jpeg"));
        int retorno = seletor.showOpenDialog(this);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            File arquivoOriginal = seletor.getSelectedFile();
            caminhoDaImagemSelecionada = GerenciadorArquivos.copiar(arquivoOriginal, "produto");
            if (caminhoDaImagemSelecionada != null) {
                exibirImagem();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao copiar a imagem para o sistema.", "Erro de Arquivo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_bSelecionarImagemActionPerformed

    private void bConsultar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConsultar1ActionPerformed
        consultarProdutoPeloCodigo();
    }//GEN-LAST:event_bConsultar1ActionPerformed

    private void bPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPesquisarActionPerformed
        pesquisarProdutos();
    }//GEN-LAST:event_bPesquisarActionPerformed

    private void jTablePesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePesquisaMouseClicked
        if (evt.getClickCount() == 2) { // Duplo clique na linha
            int linha = jTablePesquisa.getSelectedRow();
            if (linha != -1) {
                int codigo = (int) jTablePesquisa.getValueAt(linha, 0);
                Produto p = (Produto) dao.consultar(Produto.class, codigo);
                if (p != null) {
                    preencherFormulario(p);
                    jTabbedPane1.setSelectedIndex(0); // Volta para a aba de cadastro
                }
            }
        }
    }//GEN-LAST:event_jTablePesquisaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private padraotela.BConsultar bConsultar1;
    private padraotela.BExcluir bExcluir1;
    private padraotela.BLimpar bLimpar1;
    private padraotela.BConsultar bPesquisar;
    private padraotela.BSair bSair1;
    private padraotela.BSalvar bSalvar1;
    private javax.swing.JButton bSelecionarImagem;
    private padraotela.Campo campoCodigo;
    private padraotela.Campo campoCodigoOriginal;
    private javax.swing.JTextArea campoDescricao;
    private padraotela.Campo campoMarca;
    private padraotela.Campo campoNome;
    private padraotela.Campo campoPesquisa;
    private padraotela.Campo campoValorDiaria;
    private javax.swing.JLabel jLabelImagemPreview;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTablePesquisa;
    private padraotela.Rotulo rotulo1;
    private padraotela.Rotulo rotulo2;
    private padraotela.Rotulo rotulo3;
    private padraotela.Rotulo rotulo4;
    private padraotela.Rotulo rotulo5;
    private padraotela.Rotulo rotulo6;
    private padraotela.Rotulo rotulo7;
    // End of variables declaration//GEN-END:variables
}
