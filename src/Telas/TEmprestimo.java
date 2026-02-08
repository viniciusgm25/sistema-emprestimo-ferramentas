package Telas;

import Controles.Emprestimo;
import Controles.Pessoa;
import Controles.Produto;
import Controles.UpperCaseDocumentFilter;
import Persistencia.dao;
import java.awt.Image;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter; // Pode ser desnecessário se já importou acima

public class TEmprestimo extends javax.swing.JFrame {

    private Produto produtoSelecionado = null;
    private Pessoa pessoaSelecionada = null;

    public TEmprestimo() {
        initComponents();
        pack();
        setLocationRelativeTo(null);
        limparTudo();
        pesquisarProdutosDisponiveis();
        pesquisarPessoas();
        // Pega o "documento" interno do campo e aplica o nosso filtro
        ((AbstractDocument) campoPesquisaPessoa.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoPesquisaProduto.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoProdutoCodigo.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoProdutoNome.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoPessoaCodigo.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoPessoaNome.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        
    }

    private void limparTudo() {
        campoPesquisaProduto.setText("");
        campoPesquisaPessoa.setText("");
        campoProdutoCodigo.setText("");
        campoProdutoNome.setText("");
        campoPessoaCodigo.setText("");
        campoPessoaNome.setText("");
        jLabelImagemPreview.setIcon(null);
        jLabelImagemPreview.setText("Sem Imagem");
        jDateChooserDevolucao.setDate(null);

        produtoSelecionado = null;
        pessoaSelecionada = null;

        jTableProdutos.clearSelection();
        jTablePessoas.clearSelection();
    }

    private void pesquisarProdutosDisponiveis() {
        DefaultTableModel model = (DefaultTableModel) jTableProdutos.getModel();
        model.setRowCount(0);

        List<Produto> listaDisponiveis = dao.listar("Produto.findDisponiveis");
        String termoPesquisa = campoPesquisaProduto.getText().trim().toLowerCase();

        for (Produto p : listaDisponiveis) {
            if (termoPesquisa.isEmpty() || p.getNome().toLowerCase().contains(termoPesquisa)) {
                model.addRow(new Object[]{
                    p.getCodigo(),
                    p.getNome(),
                    p.getMarca(),
                    p.getValorDiaria()
                });
            }
        }
    }

    private void pesquisarPessoas() {
        DefaultTableModel model = (DefaultTableModel) jTablePessoas.getModel();
        model.setRowCount(0);
        String termoPesquisa = "%" + campoPesquisaPessoa.getText() + "%";
        List<Pessoa> lista = dao.listar("Pessoa.pesquisaNome", "nome", termoPesquisa);
        for (Pessoa p : lista) {
            model.addRow(new Object[]{
                p.getCodigo(),
                p.getNome(),
                p.getTipoPessoa() != null && p.getTipoPessoa() == 'J' ? p.getCnpj() : p.getCpf()
            });
        }
    }

    private void salvarEmprestimo() {
        if (produtoSelecionado == null || pessoaSelecionada == null || jDateChooserDevolucao.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Selecione um PRODUTO, uma PESSOA e a DATA de devolução.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Date dataInicio = new Date();
        Date dataFim = jDateChooserDevolucao.getDate();
        if (dataFim.before(dataInicio)) {
            JOptionPane.showMessageDialog(this, "A data de devolução não pode ser anterior à data de hoje.", "Data Inválida", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Emprestimo novoEmprestimo = new Emprestimo();
        novoEmprestimo.setProduto(produtoSelecionado);
        novoEmprestimo.setPessoa(pessoaSelecionada);
        novoEmprestimo.setDataEmprestimo(dataInicio);
        novoEmprestimo.setDataPrevistaDevolucao(dataFim);

        long diffEmMillis = Math.abs(dataFim.getTime() - dataInicio.getTime());
        long diffEmDias = TimeUnit.DAYS.convert(diffEmMillis, TimeUnit.MILLISECONDS);
        if (diffEmDias == 0) {
            diffEmDias = 1;
        }
        BigDecimal total = produtoSelecionado.getValorDiaria().multiply(new BigDecimal(diffEmDias));
        novoEmprestimo.setValorTotalCobrado(total);

        TPagamento telaPagamento = new TPagamento(this, true, novoEmprestimo);
        telaPagamento.setVisible(true);

        if (telaPagamento.isPagamentoConfirmado()) {
            limparTudo();
            pesquisarProdutosDisponiveis();
            pesquisarPessoas();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        campoPesquisaProduto = new padraotela.Campo();
        bPesquisarProduto = new padraotela.BConsultar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProdutos = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        campoPesquisaPessoa = new padraotela.Campo();
        bPesquisarPessoa = new padraotela.BConsultar();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePessoas = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabelImagemPreview = new javax.swing.JLabel();
        rotulo1 = new padraotela.Rotulo();
        campoProdutoCodigo = new padraotela.Campo();
        campoProdutoNome = new padraotela.Campo();
        rotulo2 = new padraotela.Rotulo();
        campoPessoaCodigo = new padraotela.Campo();
        campoPessoaNome = new padraotela.Campo();
        rotulo3 = new padraotela.Rotulo();
        jDateChooserDevolucao = new com.toedter.calendar.JDateChooser();
        bSalvar1 = new padraotela.BSalvar();
        bLimpar1 = new padraotela.BLimpar();
        bSair1 = new padraotela.BSair();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar Novo Empréstimo");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("1. Buscar Produto Disponível"));

        bPesquisarProduto.setText("Pesquisar");
        bPesquisarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPesquisarProdutoActionPerformed(evt);
            }
        });

        jTableProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cód", "Nome", "Marca", "Diária"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableProdutos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(campoPesquisaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bPesquisarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoPesquisaProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bPesquisarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("2. Buscar Cliente"));

        bPesquisarPessoa.setText("Pesquisar");
        bPesquisarPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPesquisarPessoaActionPerformed(evt);
            }
        });

        jTablePessoas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cód", "Nome", "CPF/CNPJ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePessoas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePessoasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTablePessoas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(campoPesquisaPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bPesquisarPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoPesquisaPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bPesquisarPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("3. Detalhes do Empréstimo"));

        jLabelImagemPreview.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImagemPreview.setText("Sem Imagem");
        jLabelImagemPreview.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        rotulo1.setText("Produto:");

        campoProdutoCodigo.setEditable(false);

        campoProdutoNome.setEditable(false);

        rotulo2.setText("Cliente:");

        campoPessoaCodigo.setEditable(false);

        campoPessoaNome.setEditable(false);

        rotulo3.setText("Data Prevista Devolução:*");

        bSalvar1.setText("Confirmar Empréstimo");
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

        bSair1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSair1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelImagemPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rotulo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rotulo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoPessoaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoProdutoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(campoProdutoNome, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                    .addComponent(campoPessoaNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(bLimpar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bSair1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(rotulo3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(bSalvar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rotulo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoProdutoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoProdutoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rotulo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoPessoaCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoPessoaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabelImagemPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rotulo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(bSalvar1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bLimpar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bSair1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bPesquisarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPesquisarProdutoActionPerformed
        pesquisarProdutosDisponiveis();
    }//GEN-LAST:event_bPesquisarProdutoActionPerformed

    private void bPesquisarPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPesquisarPessoaActionPerformed
        pesquisarPessoas();
    }//GEN-LAST:event_bPesquisarPessoaActionPerformed

    private void jTableProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProdutosMouseClicked
        int linha = jTableProdutos.getSelectedRow();
        if (linha != -1) {
            int codigo = (int) jTableProdutos.getValueAt(linha, 0);
            produtoSelecionado = (Produto) dao.consultar(Produto.class, codigo);
            if (produtoSelecionado != null) {
                campoProdutoCodigo.setText(produtoSelecionado.getCodigo().toString());
                campoProdutoNome.setText(produtoSelecionado.getNome());
                String caminho = produtoSelecionado.getCaminhoImagem();
                if (caminho != null && !caminho.isEmpty()) {
                    ImageIcon icon = new ImageIcon(caminho);
                    Image image = icon.getImage().getScaledInstance(jLabelImagemPreview.getWidth(), jLabelImagemPreview.getHeight(), Image.SCALE_SMOOTH);
                    jLabelImagemPreview.setIcon(new ImageIcon(image));
                } else {
                    jLabelImagemPreview.setIcon(null);
                    jLabelImagemPreview.setText("Sem Imagem");
                }
            }
        }
    }//GEN-LAST:event_jTableProdutosMouseClicked

    private void jTablePessoasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePessoasMouseClicked
        int linha = jTablePessoas.getSelectedRow();
        if (linha != -1) {
            int codigo = (int) jTablePessoas.getValueAt(linha, 0);
            pessoaSelecionada = (Pessoa) dao.consultar(Pessoa.class, codigo);
            if (pessoaSelecionada != null) {
                campoPessoaCodigo.setText(pessoaSelecionada.getCodigo().toString());
                campoPessoaNome.setText(pessoaSelecionada.getNome());
            }
        }
    }//GEN-LAST:event_jTablePessoasMouseClicked

    private void bSalvar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalvar1ActionPerformed
        salvarEmprestimo();
    }//GEN-LAST:event_bSalvar1ActionPerformed

    private void bLimpar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLimpar1ActionPerformed
        limparTudo();
    }//GEN-LAST:event_bLimpar1ActionPerformed

    private void bSair1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSair1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_bSair1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private padraotela.BLimpar bLimpar1;
    private padraotela.BConsultar bPesquisarPessoa;
    private padraotela.BConsultar bPesquisarProduto;
    private padraotela.BSair bSair1;
    private padraotela.BSalvar bSalvar1;
    private padraotela.Campo campoPesquisaPessoa;
    private padraotela.Campo campoPesquisaProduto;
    private padraotela.Campo campoPessoaCodigo;
    private padraotela.Campo campoPessoaNome;
    private padraotela.Campo campoProdutoCodigo;
    private padraotela.Campo campoProdutoNome;
    private com.toedter.calendar.JDateChooser jDateChooserDevolucao;
    private javax.swing.JLabel jLabelImagemPreview;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTablePessoas;
    private javax.swing.JTable jTableProdutos;
    private padraotela.Rotulo rotulo1;
    private padraotela.Rotulo rotulo2;
    private padraotela.Rotulo rotulo3;
    // End of variables declaration//GEN-END:variables
}

