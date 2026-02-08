package Telas;

import Controles.Emprestimo;
import Persistencia.dao;
import Utils.GerenciadorArquivos;
import java.awt.Desktop;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TGerenciarContrato extends javax.swing.JDialog {

    private Emprestimo emprestimo;
    private String caminhoContratoAnexado = null;

    public TGerenciarContrato(java.awt.Frame parent, boolean modal, Emprestimo emprestimo) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.emprestimo = emprestimo;
        preencherDados();
    }

    private void preencherDados() {
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Empréstimo ID: " + emprestimo.getId()));
        rotuloCliente.setText("Cliente: " + emprestimo.getPessoa().getNome());
        rotuloProduto.setText("Produto: " + emprestimo.getProduto().getNome());

        // Verifica se há nulo antes de definir o texto.
        String localFisico = emprestimo.getLocalizacaoFisica();
        campoLocalizacao.setText(localFisico != null ? localFisico : ""); // Se for nulo, define uma string vazia.

        caminhoContratoAnexado = emprestimo.getCaminhoContratoAssinado();
        atualizarLabelAnexo();
    }

    private void atualizarLabelAnexo() {
        if (caminhoContratoAnexado != null && !caminhoContratoAnexado.isEmpty()) {
            File f = new File(caminhoContratoAnexado);
            labelNomeAnexo.setText(f.getName());
            bVisualizarContrato.setEnabled(true);
        } else {
            labelNomeAnexo.setText("Nenhum contrato anexado.");
            bVisualizarContrato.setEnabled(false);
        }
    }

    private void salvarAlteracoes() {
        try {
            // Atualiza o objeto emprestimo com os dados da tela
            emprestimo.setLocalizacaoFisica(campoLocalizacao.getText());
            emprestimo.setCaminhoContratoAssinado(caminhoContratoAnexado);

            // Renomeia o ficheiro se for um anexo novo e temporário
            if (caminhoContratoAnexado != null && caminhoContratoAnexado.contains("_temp_")) {
                String novoCaminho = GerenciadorArquivos.renomear(caminhoContratoAnexado, "contrato_assinado", emprestimo.getId());
                emprestimo.setCaminhoContratoAssinado(novoCaminho);
            }

            // Salva o objeto emprestimo atualizado no banco
            dao.salvar(emprestimo);

            JOptionPane.showMessageDialog(this, "Informações do contrato salvas com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            this.dispose(); // Fecha a janela

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao salvar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        rotuloCliente = new padraotela.Rotulo();
        rotuloProduto = new padraotela.Rotulo();
        rotulo1 = new padraotela.Rotulo();
        campoLocalizacao = new padraotela.Campo();
        rotulo2 = new padraotela.Rotulo();
        bAnexarContrato = new javax.swing.JButton();
        labelNomeAnexo = new javax.swing.JLabel();
        bVisualizarContrato = new javax.swing.JButton();
        bSalvar = new padraotela.BSalvar();
        bSair = new padraotela.BSair();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerenciar Contrato do Empréstimo");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Empréstimo"));

        rotuloCliente.setText("Cliente:");

        rotuloProduto.setText("Produto:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(rotuloCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rotuloProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(rotuloCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rotuloProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rotulo1.setText("Localização Física:");

        rotulo2.setText("Contrato Digitalizado:");

        bAnexarContrato.setText("Anexar Arquivo...");
        bAnexarContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAnexarContratoActionPerformed(evt);
            }
        });

        labelNomeAnexo.setText("Nenhum contrato anexado.");

        bVisualizarContrato.setText("Visualizar");
        bVisualizarContrato.setEnabled(false);
        bVisualizarContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bVisualizarContratoActionPerformed(evt);
            }
        });

        bSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalvarActionPerformed(evt);
            }
        });

        bSair.setText("Fechar");
        bSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(bSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(bSair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(rotulo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(campoLocalizacao, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(rotulo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(bAnexarContrato)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(labelNomeAnexo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(bVisualizarContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rotulo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(campoLocalizacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rotulo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bAnexarContrato)
                                        .addComponent(labelNomeAnexo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bVisualizarContrato)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bSair, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bAnexarContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAnexarContratoActionPerformed
        JFileChooser seletor = new JFileChooser();
        seletor.setFileFilter(new FileNameExtensionFilter("Documentos (PDF, JPG, PNG)", "pdf", "jpg", "png", "jpeg"));
        int retorno = seletor.showOpenDialog(this);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            File arquivoOriginal = seletor.getSelectedFile();
            caminhoContratoAnexado = GerenciadorArquivos.copiar(arquivoOriginal, "contrato_assinado");
            if (caminhoContratoAnexado != null) {
                atualizarLabelAnexo();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao anexar o contrato.", "Erro de Arquivo", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_bAnexarContratoActionPerformed

    private void bVisualizarContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bVisualizarContratoActionPerformed
        if (caminhoContratoAnexado != null && !caminhoContratoAnexado.isEmpty()) {
            try {
                Desktop.getDesktop().open(new File(caminhoContratoAnexado));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Não foi possível abrir o arquivo.\nVerifique se há um programa instalado para abrir este tipo de arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_bVisualizarContratoActionPerformed

    private void bSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalvarActionPerformed
        salvarAlteracoes();
    }//GEN-LAST:event_bSalvarActionPerformed

    private void bSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_bSairActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAnexarContrato;
    private padraotela.BSair bSair;
    private padraotela.BSalvar bSalvar;
    private javax.swing.JButton bVisualizarContrato;
    private padraotela.Campo campoLocalizacao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelNomeAnexo;
    private padraotela.Rotulo rotulo1;
    private padraotela.Rotulo rotulo2;
    private padraotela.Rotulo rotuloCliente;
    private padraotela.Rotulo rotuloProduto;
    // End of variables declaration//GEN-END:variables
}
