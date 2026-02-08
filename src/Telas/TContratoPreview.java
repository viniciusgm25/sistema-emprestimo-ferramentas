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
}*/
package Telas;

import Controles.Emprestimo;
import Utils.GeradorContrato;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class TContratoPreview extends javax.swing.JDialog {

    private Emprestimo emprestimo;

    /**
     * Creates new form TContratoPreview
     */
    public TContratoPreview(java.awt.Dialog parent, boolean modal, Emprestimo emprestimo) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.emprestimo = emprestimo;

        // Carrega o HTML no componente de visualização
        jEditorPaneContrato.setContentType("text/html");
        jEditorPaneContrato.setText(GeradorContrato.gerarHtml(emprestimo));
        jEditorPaneContrato.setCaretPosition(0); // Garante que a view inicie no topo
    }

    private void salvarComoPdf() {
        JFileChooser seletor = new JFileChooser();
        seletor.setDialogTitle("Salvar Contrato PDF");
        // Sugere um nome de arquivo padrão
        seletor.setSelectedFile(new File("Contrato_" + emprestimo.getPessoa().getNome().replace(" ", "_") + ".pdf"));

        if (seletor.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File arquivo = seletor.getSelectedFile();
            try {
                GeradorContrato.gerar(emprestimo, arquivo.getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Contrato salvo com sucesso em: " + arquivo.getAbsolutePath());
                // Tenta abrir o PDF após salvar
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().open(arquivo);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Não foi possível abrir o arquivo PDF automaticamente.\nPor favor, abra-o manualmente no local salvo.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar o PDF: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void imprimirContrato() {
        try {
            // O método .print() do JEditorPane abre a caixa de diálogo de impressão do sistema
            boolean impressaoCompleta = jEditorPaneContrato.print();
            if (impressaoCompleta) {
                JOptionPane.showMessageDialog(this, "O contrato foi enviado para a impressora.", "Impressão", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "A impressão foi cancelada.", "Impressão", JOptionPane.WARNING_MESSAGE);
            }
        } catch (java.awt.print.PrinterException e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao tentar imprimir: " + e.getMessage(), "Erro de Impressão", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPaneContrato = new javax.swing.JEditorPane();
        bFechar = new padraotela.BSair();
        bImprimir = new javax.swing.JButton();
        bSalvarPDF = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Visualização de Contrato");
        setModal(true);

        jEditorPaneContrato.setEditable(false);
        jScrollPane1.setViewportView(jEditorPaneContrato);

        bFechar.setText("Fechar");
        bFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFecharActionPerformed(evt);
            }
        });

        bImprimir.setText("Imprimir...");
        bImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bImprimirActionPerformed(evt);
            }
        });

        bSalvarPDF.setText("Salvar como PDF...");
        bSalvarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalvarPDFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(bImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(bSalvarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 310, Short.MAX_VALUE)
                                                .addComponent(bFechar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bFechar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bSalvarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_bFecharActionPerformed

    private void bImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bImprimirActionPerformed
        imprimirContrato();
    }//GEN-LAST:event_bImprimirActionPerformed

    private void bSalvarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalvarPDFActionPerformed
        salvarComoPdf();
    }//GEN-LAST:event_bSalvarPDFActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private padraotela.BSair bFechar;
    private javax.swing.JButton bImprimir;
    private javax.swing.JButton bSalvarPDF;
    private javax.swing.JEditorPane jEditorPaneContrato;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
