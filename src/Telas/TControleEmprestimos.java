/*
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rotulo1 = new padraotela.Rotulo();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEmprestimos = new javax.swing.JTable();
        bAtualizar = new padraotela.BConsultar();
        bRegistrarDevolucao = new padraotela.BExcluir();
        bSair1 = new padraotela.BSair();
        bGerenciarContrato = new javax.swing.JButton();
        bGerarNF = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Controle de Empréstimos");

        rotulo1.setText("Histórico de Empréstimos (Ativos e Finalizados)");

        jTableEmprestimos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Empr.", "Produto", "Cliente", "Data Empréstimo", "Data Devolução", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableEmprestimos);

        bAtualizar.setText("Atualizar Lista");
        bAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAtualizarActionPerformed(evt);
            }
        });

        bRegistrarDevolucao.setText("Registrar Devolução");
        bRegistrarDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRegistrarDevolucaoActionPerformed(evt);
            }
        });

        bSair1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSair1ActionPerformed(evt);
            }
        });

        bGerenciarContrato.setText("Gerir Contrato");
        bGerenciarContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGerenciarContratoActionPerformed(evt);
            }
        });

        bGerarNF.setText("Gerar Nota Fiscal");
        bGerarNF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGerarNFActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rotulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bRegistrarDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bGerenciarContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bGerarNF, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bSair1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotulo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bGerenciarContrato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bRegistrarDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bSair1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bGerarNF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAtualizarActionPerformed
        carregarEmprestimos();
    }//GEN-LAST:event_bAtualizarActionPerformed

    private void bRegistrarDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRegistrarDevolucaoActionPerformed
        registrarDevolucao();
    }//GEN-LAST:event_bRegistrarDevolucaoActionPerformed

    private void bSair1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSair1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_bSair1ActionPerformed

    private void bGerenciarContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGerenciarContratoActionPerformed

    }//GEN-LAST:event_bGerenciarContratoActionPerformed

    private void bGerarNFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGerarNFActionPerformed
        gerarNotaFiscal();
    }//GEN-LAST:event_bGerarNFActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    carregarEmprestimos();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private padraotela.BConsultar bAtualizar;
    private javax.swing.JButton bGerarNF;
    private javax.swing.JButton bGerenciarContrato;
    private padraotela.BExcluir bRegistrarDevolucao;
    private padraotela.BSair bSair1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEmprestimos;
    private padraotela.Rotulo rotulo1;
    // End of variables declaration//GEN-END:variables
*/
package Telas;

// Imports de Controles e Persistencia
import Controles.Emprestimo;
import Controles.NotaFiscal;
import Persistencia.dao;

// Imports do Java e Swing
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;

// Imports para a "Tunada" Visual (Linhas Coloridas)
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel; // Import para os novos JLabels
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TControleEmprestimos extends javax.swing.JFrame {

    private dao dao;

    public TControleEmprestimos() {
        initComponents();
        dao = new dao();

        // 1. Configura o JComboBox de DATAS (jComboBox1)
        String[] filtrosData = {"Todos", "Hoje", "Este Mês", "Mês Passado", "Este Ano", "Ano Passado"};
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(filtrosData));

        // 2. Configura o NOVO JComboBox de STATUS (jComboBoxStatus)
        // Certifique-se de que o nome da variável no "initComponents" é jComboBoxStatus
        String[] filtrosStatus = {"Todos", "Em Aberto", "Encerrados"};
        jComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(filtrosStatus));

        // 3. Carrega os dados e aplica o visual
        carregarEmprestimos();
        aplicarRenderizadorDeStatus();
    }

    // --- MÉTODOS AUXILIARES DE DATA ---
    private Date getInicioDoDia(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private Date getFimDoDia(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    // --- MÉTODO PRINCIPAL DE CARREGAMENTO (COM FILTRO DUPLO) ---
    private void carregarEmprestimos() {
        // 1. Pega os valores dos DOIS filtros
        String filtroData = (String) jComboBox1.getSelectedItem();
        String filtroStatus = (String) jComboBoxStatus.getSelectedItem();

        if (filtroData == null) {
            filtroData = "Todos";
        }
        if (filtroStatus == null) {
            filtroStatus = "Todos";
        }

        List lista;
        Calendar cal = Calendar.getInstance();
        Date dataInicio = null;
        Date dataFim = null;

        String queryName = "";
        boolean usaData = false;

        // --- 2. Prepara as datas (se necessário) ---
        switch (filtroData) {
            case "Hoje":
                dataInicio = getInicioDoDia(cal.getTime());
                dataFim = getFimDoDia(cal.getTime());
                usaData = true;
                break;
            case "Este Mês":
                cal.set(Calendar.DAY_OF_MONTH, 1);
                dataInicio = getInicioDoDia(cal.getTime());
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                dataFim = getFimDoDia(cal.getTime());
                usaData = true;
                break;
            case "Mês Passado":
                cal.add(Calendar.MONTH, -1);
                cal.set(Calendar.DAY_OF_MONTH, 1);
                dataInicio = getInicioDoDia(cal.getTime());
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                dataFim = getFimDoDia(cal.getTime());
                usaData = true;
                break;
            case "Este Ano":
                cal.set(Calendar.DAY_OF_YEAR, 1);
                dataInicio = getInicioDoDia(cal.getTime());
                cal.set(Calendar.DAY_OF_YEAR, cal.getActualMaximum(Calendar.DAY_OF_YEAR));
                dataFim = getFimDoDia(cal.getTime());
                usaData = true;
                break;
            case "Ano Passado":
                cal.add(Calendar.YEAR, -1);
                cal.set(Calendar.DAY_OF_YEAR, 1);
                dataInicio = getInicioDoDia(cal.getTime());
                cal.set(Calendar.DAY_OF_YEAR, cal.getActualMaximum(Calendar.DAY_OF_YEAR));
                dataFim = getFimDoDia(cal.getTime());
                usaData = true;
                break;
            case "Todos":
            default:
                usaData = false;
                break;
        }

        // --- 3. Escolhe a NamedQuery CORRETA baseada nos filtros ---
        // (Você DEVE adicionar estas queries na sua entidade Emprestimo.java)
        switch (filtroStatus) {
            case "Em Aberto":
                queryName = usaData ? "Emprestimo.findByDateRangeAbertos" : "Emprestimo.findAllAbertos";
                break;
            case "Encerrados":
                queryName = usaData ? "Emprestimo.findByDateRangeEncerrados" : "Emprestimo.findAllEncerrados";
                break;
            case "Todos":
            default:
                queryName = usaData ? "Emprestimo.findByDateRange" : "Emprestimo.findAll";
                break;
        }

        // --- 4. Executa a consulta no DAO ---
        try {
            if (usaData) {
                // Usa o método: listar(String, String, Date, String, Date)
                lista = dao.listar(queryName, "dataInicio", dataInicio, "dataFim", dataFim);
            } else {
                // Usa o método: listar(String)
                lista = dao.listar(queryName);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar lista. Verifique se as NamedQueries estão corretas em Emprestimo.java.\nQuery: " + queryName, "Erro de Consulta", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return; // Sai do método se der erro
        }

        // --- 5. Lógica para atualizar a tabela (JTable) ---
        DefaultTableModel model = (DefaultTableModel) jTableEmprestimos.getModel();
        model.setRowCount(0);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (lista == null) {
            return; // Segurança caso a lista volte nula
        }
        for (Object obj : lista) {
            Emprestimo e = (Emprestimo) obj; // Cast

            String status;
            if (e.getDataDevolucaoReal() != null) {
                status = "Devolvido";
            } else {
                long diff = e.getDataPrevistaDevolucao().getTime() - new Date().getTime();
                long diasRestantes = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                status = (diasRestantes < 0) ? "ATRASADO (" + Math.abs(diasRestantes) + " dias)" : "Ativo";
            }

            model.addRow(new Object[]{
                e.getId(),
                e.getProduto().getNome(),
                e.getPessoa().getNome(),
                sdf.format(e.getDataEmprestimo()),
                sdf.format(e.getDataPrevistaDevolucao()),
                status
            });
        }
    }

    // --- MÉTODOS DE AÇÃO (Devolução, NF, etc.) ---
    private void registrarDevolucao() {
        int linhaSelecionada = jTableEmprestimos.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um empréstimo na lista para devolver.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int emprestimoId = (int) jTableEmprestimos.getValueAt(linhaSelecionada, 0);
        Emprestimo emprestimo = (Emprestimo) dao.consultar(Emprestimo.class, emprestimoId);

        if (emprestimo == null) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar empréstimo.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (emprestimo.getDataDevolucaoReal() != null) {
            JOptionPane.showMessageDialog(this, "Este item já foi devolvido.", "Operação Inválida", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Date hoje = new Date();

        if (hoje.after(emprestimo.getDataPrevistaDevolucao())) {
            long diff = hoje.getTime() - emprestimo.getDataPrevistaDevolucao().getTime();
            long diasAtraso = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            if (diasAtraso == 0) {
                diasAtraso = 1; // Pelo menos 1 dia de atraso se for no mesmo dia
            }

            BigDecimal valorDiaria = emprestimo.getProduto().getValorDiaria();
            BigDecimal multaPorDias = valorDiaria.multiply(new BigDecimal(diasAtraso));
            BigDecimal taxaContrato = emprestimo.getValorTotalCobrado().multiply(new BigDecimal("0.20"));
            BigDecimal totalMulta = multaPorDias.add(taxaContrato);

            String mensagem = String.format("Devolução com %d dia(s) de atraso!\n\nMulta por dias: R$ %.2f\nTaxa de contrato: R$ %.2f\n--------------------\nTOTAL A PAGAR: R$ %.2f\n\nDeseja prosseguir para o pagamento da multa?",
                    diasAtraso, multaPorDias, taxaContrato, totalMulta);

            int resposta = JOptionPane.showConfirmDialog(this, mensagem, "Atraso na Devolução", JOptionPane.YES_NO_OPTION);

            if (resposta == JOptionPane.YES_OPTION) {
                // Supondo que você tenha a tela TPagamento
                TPagamento telaPagamentoMulta = new TPagamento(this, true, emprestimo, totalMulta);
                telaPagamentoMulta.setVisible(true);

                if (telaPagamentoMulta.isPagamentoConfirmado()) {
                    finalizarDevolucao(emprestimo);
                }
            }
        } else {
            int resposta = JOptionPane.showConfirmDialog(this, "Confirma a devolução deste item (no prazo)?", "Devolução", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                finalizarDevolucao(emprestimo);
            }
        }
    }

    private void finalizarDevolucao(Emprestimo emprestimo) {
        emprestimo.setDataDevolucaoReal(new Date());
        dao.salvar(emprestimo);
        JOptionPane.showMessageDialog(this, "Devolução registrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        carregarEmprestimos();
    }

    private void gerarNotaFiscal() {
        int linhaSelecionada = jTableEmprestimos.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um empréstimo na lista para gerar a NF.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int emprestimoId = (int) jTableEmprestimos.getValueAt(linhaSelecionada, 0);
        Emprestimo emprestimo = (Emprestimo) dao.consultar(Emprestimo.class, emprestimoId);

        if (emprestimo == null) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar empréstimo.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (emprestimo.getDataDevolucaoReal() == null) {
            JOptionPane.showMessageDialog(this, "Só é possível gerar NF para empréstimos já finalizados (devolvidos).", "Operação Inválida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<NotaFiscal> notasExistentes = dao.listar("NotaFiscal.findByEmprestimo", "emprestimoId", emprestimo.getId());
        if (notasExistentes != null && !notasExistentes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Já existe uma Nota Fiscal emitida para este empréstimo.", "Operação Inválida", JOptionPane.INFORMATION_MESSAGE);
            // Supondo que você tenha a tela TNotaFiscalPreview
            TNotaFiscalPreview telaExistente = new TNotaFiscalPreview(this, true, notasExistentes.get(0));
            telaExistente.setVisible(true);
            return;
        }

        try {
            Object resultado = dao.consultarAgregadoSimples("NotaFiscal.findMaxNumero");
            Long ultimoNumero = (resultado != null) ? (Long) resultado : 0L;
            Long proximoNumero = ultimoNumero + 1;

            NotaFiscal novaNf = new NotaFiscal();
            novaNf.setCodEmprestimo(emprestimo);
            novaNf.setNumeroNf(proximoNumero);
            novaNf.setDataEmissao(new Date());
            novaNf.setDescricaoServico("Serviço de locação do equipamento: " + emprestimo.getProduto().getNome());
            novaNf.setValorServico(emprestimo.getValorTotalCobrado());

            dao.salvar(novaNf);

            JOptionPane.showMessageDialog(this, "Nota Fiscal Nº " + proximoNumero + " gerada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            TNotaFiscalPreview telaPreview = new TNotaFiscalPreview(this, true, novaNf);
            telaPreview.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao gerar a Nota Fiscal: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // --- "TUNADA" VISUAL (RENDERIZADOR DE CORES) ---
    private void aplicarRenderizadorDeStatus() {
        StatusColumnCellRenderer renderer = new StatusColumnCellRenderer();
        for (int i = 0; i < jTableEmprestimos.getColumnCount(); i++) {
            jTableEmprestimos.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    public class StatusColumnCellRenderer extends DefaultTableCellRenderer {

        private static final int COLUNA_STATUS = 5;

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (table.getRowCount() <= row) {
                return c;
            }

            String status = (String) table.getValueAt(row, COLUNA_STATUS);

            if (status == null) {
                c.setBackground(Color.WHITE);
                c.setForeground(Color.BLACK);
                return c;
            }

            if (status.startsWith("ATRASADO")) {
                Color background = isSelected ? new Color(220, 50, 50) : new Color(255, 200, 200);
                c.setBackground(background);
                c.setForeground(Color.BLACK);
            } else if (status.equals("Devolvido")) {
                Color background = isSelected ? new Color(50, 180, 50) : new Color(200, 255, 200);
                c.setBackground(background);
                c.setForeground(Color.BLACK);
            } else { // "Ativo"
                Color background = isSelected ? table.getSelectionBackground() : Color.WHITE;
                Color foreground = isSelected ? table.getSelectionForeground() : Color.BLACK;
                c.setBackground(background);
                c.setForeground(foreground);
            }

            return c;
        }
    }

    // --- CÓDIGO GERADO PELO NETBEANS ---
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        rotulo1 = new padraotela.Rotulo();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEmprestimos = new javax.swing.JTable();
        bAtualizar = new padraotela.BConsultar();
        bRegistrarDevolucao = new padraotela.BExcluir();
        bSair1 = new padraotela.BSair();
        bGerenciarContrato = new javax.swing.JButton();
        bGerarNF = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBoxStatus = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Controle de Empréstimos");

        rotulo1.setText("Histórico de Empréstimos (Ativos e Finalizados)");

        jTableEmprestimos.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID Empr.", "Produto", "Cliente", "Data Empréstimo", "Data Devolução", "Status"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableEmprestimos);

        bAtualizar.setText("Atualizar Lista");
        bAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAtualizarActionPerformed(evt);
            }
        });

        bRegistrarDevolucao.setText("Registrar Devolução");
        bRegistrarDevolucao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRegistrarDevolucaoActionPerformed(evt);
            }
        });

        bSair1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSair1ActionPerformed(evt);
            }
        });

        bGerenciarContrato.setText("Gerir Contrato");
        bGerenciarContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGerenciarContratoActionPerformed(evt);
            }
        });

        bGerarNF.setText("Gerar Nota Fiscal");
        bGerarNF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGerarNFActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        jComboBoxStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxStatusActionPerformed(evt);
            }
        });

        jLabel1.setText("Filtrar Data:");

        jLabel2.setText("Filtrar Status:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(rotulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(bAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(bRegistrarDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(bGerenciarContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(bGerarNF, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(bSair1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rotulo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(bGerenciarContrato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(bAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(bRegistrarDevolucao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(bSair1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(bGerarNF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    // --- MÉTODOS DE EVENTOS (Actions) ---
    private void bAtualizarActionPerformed(java.awt.event.ActionEvent evt) {
        carregarEmprestimos();
    }

    private void bRegistrarDevolucaoActionPerformed(java.awt.event.ActionEvent evt) {
        registrarDevolucao();
    }

    private void bSair1ActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    private void bGerenciarContratoActionPerformed(java.awt.event.ActionEvent evt) {
        int linhaSelecionada = jTableEmprestimos.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um empréstimo na lista para gerir o contrato.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int emprestimoId = (int) jTableEmprestimos.getValueAt(linhaSelecionada, 0);
        Emprestimo emprestimo = (Emprestimo) dao.consultar(Emprestimo.class, emprestimoId);

        if (emprestimo != null) {
            // Supondo que você tenha a tela TGerenciarContrato
            TGerenciarContrato tela = new TGerenciarContrato(this, true, emprestimo);
            tela.setVisible(true);
            carregarEmprestimos();
        }
    }

    private void bGerarNFActionPerformed(java.awt.event.ActionEvent evt) {
        gerarNotaFiscal();
    }

    // Evento do filtro de DATA
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        carregarEmprestimos();
    }

    // Evento do filtro de STATUS
    private void jComboBoxStatusActionPerformed(java.awt.event.ActionEvent evt) {
        carregarEmprestimos();
    }

    // Variables declaration - do not modify                     
    private padraotela.BConsultar bAtualizar;
    private javax.swing.JButton bGerarNF;
    private javax.swing.JButton bGerenciarContrato;
    private padraotela.BExcluir bRegistrarDevolucao;
    private padraotela.BSair bSair1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBoxStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEmprestimos;
    private padraotela.Rotulo rotulo1;
    // End of variables declaration                   
}
