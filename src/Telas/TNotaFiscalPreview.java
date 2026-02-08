/*
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
import Utils.GeradorNotaFiscal;
import java.awt.Desktop;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class TNotaFiscalPreview extends javax.swing.JDialog {

    private NotaFiscal notaFiscal;

    public TNotaFiscalPreview(java.awt.Frame parent, boolean modal, NotaFiscal notaFiscal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.notaFiscal = notaFiscal;

        // Para simplificar a pré-visualização, mostraremos os dados em texto.
        // A geração do layout complexo será feita diretamente no PDF.
        carregarPreview();
    }

    private void carregarPreview() {
        StringBuilder preview = new StringBuilder();
        preview.append("NOTA FISCAL DE SERVIÇO (SIMULAÇÃO)\n");
        preview.append("=========================================\n\n");
        preview.append("NÚMERO DA NOTA: ").append(notaFiscal.getNumeroNf()).append("\n");
        preview.append("DATA DE EMISSÃO: ").append(notaFiscal.getDataEmissao().toString()).append("\n\n");
        preview.append("--- DADOS DO CLIENTE ---\n");
        preview.append("Nome: ").append(notaFiscal.getCodEmprestimo().getPessoa().getNome()).append("\n");
        String doc = notaFiscal.getCodEmprestimo().getPessoa().getTipoPessoa() == 'F' ? "CPF: " : "CNPJ: ";
        doc += notaFiscal.getCodEmprestimo().getPessoa().getTipoPessoa() == 'F' ? notaFiscal.getCodEmprestimo().getPessoa().getCpf() : notaFiscal.getCodEmprestimo().getPessoa().getCnpj();
        preview.append(doc).append("\n\n");
        preview.append("--- DESCRIÇÃO DO SERVIÇO ---\n");
        preview.append(notaFiscal.getDescricaoServico()).append("\n\n");
        preview.append("-----------------------------------------\n");
        preview.append(String.format("VALOR TOTAL: R$ %.2f", notaFiscal.getValorServico()));

        jTextAreaPreview.setText(preview.toString());
    }

    private void salvarComoPdf() {
        JFileChooser seletor = new JFileChooser();
        seletor.setDialogTitle("Salvar Nota Fiscal em PDF");
        seletor.setSelectedFile(new File("NF_" + notaFiscal.getNumeroNf() + ".pdf"));

        if (seletor.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File arquivo = seletor.getSelectedFile();
            try {
                GeradorNotaFiscal.gerarPdf(notaFiscal, arquivo.getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Nota Fiscal salva com sucesso em: " + arquivo.getAbsolutePath());
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(arquivo);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar o PDF: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaPreview = new javax.swing.JTextArea();
        bSalvarPDF = new javax.swing.JButton();
        bFechar = new padraotela.BSair();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Visualização de Nota Fiscal");

        jTextAreaPreview.setEditable(false);
        jTextAreaPreview.setColumns(20);
        jTextAreaPreview.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jTextAreaPreview.setRows(5);
        jScrollPane1.setViewportView(jTextAreaPreview);

        bSalvarPDF.setText("Salvar como PDF...");
        bSalvarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalvarPDFActionPerformed(evt);
            }
        });

        bFechar.setText("Fechar");
        bFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFecharActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Pré-visualização da Nota Fiscal (Simulação)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(bSalvarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 318, Short.MAX_VALUE)
                                                .addComponent(bFechar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bFechar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bSalvarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSalvarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalvarPDFActionPerformed
        salvarComoPdf();
    }//GEN-LAST:event_bSalvarPDFActionPerformed

    private void bFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFecharActionPerformed
        this.dispose();
    }//GEN-LAST:event_bFecharActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private padraotela.BSair bFechar;
    private javax.swing.JButton bSalvarPDF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaPreview;
    // End of variables declaration//GEN-END:variables
}
