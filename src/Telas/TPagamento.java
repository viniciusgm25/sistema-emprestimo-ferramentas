package Telas;

import Controles.Emprestimo;
import Controles.Pagamento;
import Persistencia.dao;
import Utils.GeradorContrato;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.NumberFormatter;

public class TPagamento extends javax.swing.JDialog {

    private Emprestimo emprestimo;
    private boolean pagamentoConfirmado = false;

    // Construtor para NOVO EMPRÉSTIMO
    public TPagamento(java.awt.Frame parent, boolean modal, Emprestimo emprestimo) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        this.emprestimo = emprestimo;

        rotuloTitulo.setText("Registro de Pagamento");
        preencherDados();
        configurarCamposFormatados();
        gerenciarCamposPagamento();
    }

    // Construtor para MULTAS
    public TPagamento(java.awt.Frame parent, boolean modal, Emprestimo emprestimo, BigDecimal valorMulta) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);

        this.emprestimo = emprestimo;

        rotuloTitulo.setText("Pagamento de Multa por Atraso");
        rotuloCliente.setText("Cliente: " + emprestimo.getPessoa().getNome());
        rotuloProduto.setText("Produto: " + emprestimo.getProduto().getNome());
        rotuloValorTotal.setText(String.format("R$ %.2f", valorMulta));

        this.emprestimo.setValorTotalCobrado(valorMulta);

        configurarCamposFormatados();
        gerenciarCamposPagamento();
    }

    public boolean isPagamentoConfirmado() {
        return this.pagamentoConfirmado;
    }

    private void preencherDados() {
        rotuloCliente.setText("Cliente: " + emprestimo.getPessoa().getNome());
        rotuloProduto.setText("Produto: " + emprestimo.getProduto().getNome());
        rotuloValorTotal.setText(String.format("R$ %.2f", emprestimo.getValorTotalCobrado()));
    }

    private void configurarCamposFormatados() {
        // Formatação para o campo de ENTRADA (Moeda)
        NumberFormat currencyFormat = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        currencyFormat.setMinimumFractionDigits(2);
        NumberFormatter currencyFormatter = new NumberFormatter(currencyFormat);
        currencyFormatter.setAllowsInvalid(false);
        currencyFormatter.setValueClass(Double.class);
        campoEntrada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(currencyFormatter));
        campoEntrada.setValue(0.00);

        // Formatação para o campo de PARCELAS (Número Inteiro)
        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        NumberFormatter integerFormatter = new NumberFormatter(integerFormat);
        integerFormatter.setAllowsInvalid(false);
        integerFormatter.setValueClass(Integer.class);
        integerFormatter.setMinimum(1);
        campoParcelas.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(integerFormatter));
        campoParcelas.setValue(1);
    }

    private void gerenciarCamposPagamento() {
        String forma = (String) jComboBoxFormaPagamento.getSelectedItem();
        boolean habilitarParcelamento = "Cartão de Crédito".equals(forma);

        rotuloEntrada.setEnabled(habilitarParcelamento);
        campoEntrada.setEnabled(habilitarParcelamento);
        rotuloParcelas.setEnabled(habilitarParcelamento);
        campoParcelas.setEnabled(habilitarParcelamento);
    }

    private void confirmarPagamento() {
        try {
            emprestimo = (Emprestimo) dao.salvar(emprestimo);

            Pagamento pag = new Pagamento();
            pag.setCodEmprestimo(emprestimo);
            pag.setValorTotal(emprestimo.getValorTotalCobrado());
            pag.setDataPagamento(new Date());
            pag.setStatus("Pago");

            String forma = (String) jComboBoxFormaPagamento.getSelectedItem();
            pag.setFormaPagamento(forma);

            if ("Cartão de Crédito".equals(forma)) {
                double entradaDouble = ((Number) campoEntrada.getValue()).doubleValue();
                BigDecimal entrada = new BigDecimal(entradaDouble).setScale(2, RoundingMode.HALF_UP);
                int parcelas = (Integer) campoParcelas.getValue();

                if (parcelas <= 0) {
                    parcelas = 1;
                }

                pag.setValorEntrada(entrada);
                pag.setNumeroParcelas(parcelas);

                BigDecimal valorAParcelar = pag.getValorTotal().subtract(entrada);
                if (valorAParcelar.compareTo(BigDecimal.ZERO) > 0 && parcelas > 0) {
                    BigDecimal valorParcela = valorAParcelar.divide(new BigDecimal(parcelas), 2, RoundingMode.HALF_UP);
                    pag.setValorParcela(valorParcela);
                }
            }

            dao.salvar(pag);

            JOptionPane.showMessageDialog(this, "Pagamento confirmado e empréstimo registrado!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            TContratoPreview telaPreview = new TContratoPreview(this, true, emprestimo);
            telaPreview.setVisible(true);

            this.pagamentoConfirmado = true;
            this.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao confirmar pagamento ou gerar contrato: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            if (emprestimo.getId() != null) {
                dao.excluir(emprestimo);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rotuloTitulo = new padraotela.Rotulo();
        jPanel1 = new javax.swing.JPanel();
        rotuloCliente = new padraotela.Rotulo();
        rotuloProduto = new padraotela.Rotulo();
        rotuloValorTotalLabel = new padraotela.Rotulo();
        rotuloValorTotal = new padraotela.Rotulo();
        rotuloFormaPag = new padraotela.Rotulo();
        jComboBoxFormaPagamento = new javax.swing.JComboBox<>();
        rotuloEntrada = new padraotela.Rotulo();
        rotuloParcelas = new padraotela.Rotulo();
        bConfirmar = new padraotela.BSalvar();
        bCancelar = new padraotela.BSair();
        campoEntrada = new javax.swing.JFormattedTextField();
        campoParcelas = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Finalizar Pagamento");

        rotuloTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        rotuloTitulo.setText("Registro de Pagamento");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Resumo do Empréstimo"));

        rotuloCliente.setText("Cliente:");

        rotuloProduto.setText("Produto:");

        rotuloValorTotalLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rotuloValorTotalLabel.setText("VALOR TOTAL:");

        rotuloValorTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rotuloValorTotal.setForeground(new java.awt.Color(0, 153, 0));
        rotuloValorTotal.setText("R$ 0,00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(rotuloCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rotuloProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(rotuloValorTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rotuloValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 16, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(rotuloCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rotuloProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rotuloValorTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rotuloValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(12, Short.MAX_VALUE))
        );

        rotuloFormaPag.setText("Forma de Pagamento:*");

        jComboBoxFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"PIX", "Dinheiro", "Cartão de Débito", "Cartão de Crédito"}));
        jComboBoxFormaPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFormaPagamentoActionPerformed(evt);
            }
        });

        rotuloEntrada.setText("Valor Entrada (R$):");

        rotuloParcelas.setText("Nº de Parcelas:");

        bConfirmar.setText("Confirmar Pagamento");
        bConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConfirmarActionPerformed(evt);
            }
        });

        bCancelar.setText("Cancelar");
        bCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(bConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(rotuloFormaPag, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(jComboBoxFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(rotuloEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(campoEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(rotuloParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(campoParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(134, 134, 134)
                                                .addComponent(rotuloTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(rotuloTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rotuloFormaPag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBoxFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rotuloEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(campoEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rotuloParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(campoParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(bConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(bCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxFormaPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFormaPagamentoActionPerformed
        gerenciarCamposPagamento();
    }//GEN-LAST:event_jComboBoxFormaPagamentoActionPerformed

    private void bConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConfirmarActionPerformed
        confirmarPagamento();
    }//GEN-LAST:event_bConfirmarActionPerformed

    private void bCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_bCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private padraotela.BSair bCancelar;
    private padraotela.BSalvar bConfirmar;
    private javax.swing.JFormattedTextField campoEntrada;
    private javax.swing.JFormattedTextField campoParcelas;
    private javax.swing.JComboBox<String> jComboBoxFormaPagamento;
    private javax.swing.JPanel jPanel1;
    private padraotela.Rotulo rotuloCliente;
    private padraotela.Rotulo rotuloEntrada;
    private padraotela.Rotulo rotuloFormaPag;
    private padraotela.Rotulo rotuloParcelas;
    private padraotela.Rotulo rotuloProduto;
    private padraotela.Rotulo rotuloTitulo;
    private padraotela.Rotulo rotuloValorTotal;
    private padraotela.Rotulo rotuloValorTotalLabel;
    // End of variables declaration//GEN-END:variables
}
