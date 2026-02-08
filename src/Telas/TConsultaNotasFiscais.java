/*
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
*/
package Telas;

import Controles.NotaFiscal;
import Persistencia.dao;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TConsultaNotasFiscais extends javax.swing.JFrame {

    public TConsultaNotasFiscais() {
        initComponents();
        pack();
        setLocationRelativeTo(null);
        carregarNotasFiscais();
    }

    private void carregarNotasFiscais() {
        DefaultTableModel model = (DefaultTableModel) jTableNotas.getModel();
        model.setRowCount(0);

        // Usa a NamedQuery "NotaFiscal.findAll" que já criámos na entidade
        List<NotaFiscal> lista = dao.listar("NotaFiscal.findAll");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        for (NotaFiscal nf : lista) {
            model.addRow(new Object[]{
                nf.getNumeroNf(),
                sdf.format(nf.getDataEmissao()),
                nf.getCodEmprestimo().getPessoa().getNome(),
                String.format("R$ %.2f", nf.getValorServico()),
                nf.getCodEmprestimo().getId() // Adiciona o ID do empréstimo para consulta
            });
        }
    }

    private void visualizarNotaSelecionada() {
        int linhaSelecionada = jTableNotas.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma nota fiscal na lista para visualizar.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Assume-se que o ID do Empréstimo está na última coluna (oculta ou não)
        int emprestimoId = (int) jTableNotas.getValueAt(linhaSelecionada, 4);

        // Busca a nota fiscal associada a esse empréstimo
        List<NotaFiscal> notas = dao.listar("NotaFiscal.findByEmprestimo", "emprestimoId", emprestimoId);

        if (!notas.isEmpty()) {
            TNotaFiscalPreview telaPreview = new TNotaFiscalPreview(this, true, notas.get(0));
            telaPreview.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Não foi possível encontrar os detalhes da nota fiscal selecionada.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rotulo1 = new padraotela.Rotulo();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableNotas = new javax.swing.JTable();
        bSair = new padraotela.BSair();
        bAtualizar = new padraotela.BConsultar();
        bVisualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta de Notas Fiscais Emitidas");

        rotulo1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rotulo1.setText("Histórico de Notas Fiscais Emitidas");

        jTableNotas.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Nº da Nota", "Data de Emissão", "Cliente", "Valor (R$)", "ID Empréstimo"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTableNotas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableNotasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableNotas);

        bSair.setText("Fechar");
        bSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSairActionPerformed(evt);
            }
        });

        bAtualizar.setText("Atualizar");
        bAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAtualizarActionPerformed(evt);
            }
        });

        bVisualizar.setText("Visualizar Nota Selecionada");
        bVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bVisualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(rotulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(bAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(bVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(bSair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(rotulo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(bVisualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(bSair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(bAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_bSairActionPerformed

    private void bAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAtualizarActionPerformed
        carregarNotasFiscais();
    }//GEN-LAST:event_bAtualizarActionPerformed

    private void bVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bVisualizarActionPerformed
        visualizarNotaSelecionada();
    }//GEN-LAST:event_bVisualizarActionPerformed

    private void jTableNotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableNotasMouseClicked
        if (evt.getClickCount() == 2) {
            visualizarNotaSelecionada();
        }
    }//GEN-LAST:event_jTableNotasMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private padraotela.BConsultar bAtualizar;
    private padraotela.BSair bSair;
    private javax.swing.JButton bVisualizar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableNotas;
    private padraotela.Rotulo rotulo1;
    // End of variables declaration//GEN-END:variables
}
