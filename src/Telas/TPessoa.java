package Telas;

import Controles.Pessoa;
import Controles.UpperCaseDocumentFilter;
import Persistencia.dao;
import Utils.Validador;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter; // Pode ser desnecessário se já importou acima

public class TPessoa extends javax.swing.JFrame {

    public TPessoa() {
        initComponents();
        pack(); // Garante que a janela tenha o tamanho correto
        setLocationRelativeTo(null); // Centraliza a janela na tela
        limparCampos();
        pesquisarPessoas();
        
        // Pega o "documento" interno do campo e aplica o nosso filtro
        ((AbstractDocument) campoUF.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoRG.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoPesquisa.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoNumero.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoNome.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoIE.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoEndereco.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoEmail.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoCodigo.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoCidade.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoCPF.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoCNPJ.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoCEP.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoBairro.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
        ((AbstractDocument) campoApelido.getDocument()).setDocumentFilter(new UpperCaseDocumentFilter());
    }

    private void gerenciarCamposTipoPessoa() {
        boolean isPessoaFisica = jrFisica.isSelected();

        campoCPF.setEnabled(isPessoaFisica);
        campoRG.setEnabled(isPessoaFisica);

        campoCNPJ.setEnabled(!isPessoaFisica);
        campoIE.setEnabled(!isPessoaFisica);

        if (isPessoaFisica) {
            campoCNPJ.setText("");
            campoIE.setText("");
        } else {
            campoCPF.setText("");
            campoRG.setText("");
        }
    }

    private void limparCampos() {
        campoCodigo.setText("");
        campoNome.setText("");
        campoApelido.setText("");
        campoEmail.setText("");
        campoCPF.setText("");
        campoRG.setText("");
        campoCNPJ.setText("");
        campoIE.setText("");
        campoEndereco.setText("");
        campoNumero.setText("");
        campoBairro.setText("");
        campoCidade.setText("");
        campoUF.setText("");
        campoCEP.setText("");

        jrFisica.setSelected(true);
        gerenciarCamposTipoPessoa();

        bExcluir1.setEnabled(false);
        jTabbedPane1.setSelectedIndex(0);
        campoNome.requestFocus();
    }

    private void preencherFormulario(Pessoa p) {
        if (p != null) {
            campoCodigo.setText(p.getCodigo().toString());
            campoNome.setText(p.getNome());
            campoApelido.setText(p.getApelido());
            campoEmail.setText(p.getEmail());

            if (p.getTipoPessoa() != null && p.getTipoPessoa() == 'J') {
                jrJuridica.setSelected(true);
            } else {
                jrFisica.setSelected(true);
            }
            gerenciarCamposTipoPessoa();

            campoCPF.setText(p.getCpf());
            campoRG.setText(p.getRg());
            campoCNPJ.setText(p.getCnpj());
            campoIE.setText(p.getInscricaoEstadual());
            campoEndereco.setText(p.getEndereco());
            campoNumero.setText(p.getNumero());
            campoBairro.setText(p.getBairro());
            campoCidade.setText(p.getCidade());
            campoUF.setText(p.getUf());
            campoCEP.setText(p.getCep());

            bExcluir1.setEnabled(true);
        }
    }

    private void salvarPessoa() {
        if (campoNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O campo Nome é obrigatório.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (jrFisica.isSelected()) {
            if (!campoCPF.getText().trim().isEmpty() && !Validador.isCPFValido(campoCPF.getText())) {
                JOptionPane.showMessageDialog(this, "O CPF informado é inválido!", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            if (!campoCNPJ.getText().trim().isEmpty() && !Validador.isCNPJValido(campoCNPJ.getText())) {
                JOptionPane.showMessageDialog(this, "O CNPJ informado é inválido!", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        try {
            Pessoa p;
            if (campoCodigo.getText().trim().isEmpty()) {
                p = new Pessoa();
            } else {
                p = (Pessoa) dao.consultar(Pessoa.class, Integer.parseInt(campoCodigo.getText()));
            }

            p.setNome(campoNome.getText());
            p.setApelido(campoApelido.getText());
            p.setEmail(campoEmail.getText());

            if (jrFisica.isSelected()) {
                p.setTipoPessoa('F');
                p.setCpf(campoCPF.getText());
                p.setRg(campoRG.getText());
                p.setCnpj(null);
                p.setInscricaoEstadual(null);
            } else {
                p.setTipoPessoa('J');
                p.setCnpj(campoCNPJ.getText());
                p.setInscricaoEstadual(campoIE.getText());
                p.setCpf(null);
                p.setRg(null);
            }

            p.setEndereco(campoEndereco.getText());
            p.setNumero(campoNumero.getText());
            p.setBairro(campoBairro.getText());
            p.setCidade(campoCidade.getText());
            p.setUf(campoUF.getText());
            p.setCep(campoCEP.getText());

            dao.salvar(p);
            JOptionPane.showMessageDialog(this, "Pessoa salva com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
            pesquisarPessoas();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao salvar: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluirPessoa() {
        if (campoCodigo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Consulte uma pessoa antes de excluir.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int resposta = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir esta pessoa?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            try {
                int codigo = Integer.parseInt(campoCodigo.getText());
                Pessoa p = (Pessoa) dao.consultar(Pessoa.class, codigo);
                dao.excluir(p);
                JOptionPane.showMessageDialog(this, "Pessoa excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
                pesquisarPessoas();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao excluir. Verifique se a pessoa não possui empréstimos ativos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void pesquisarPessoas() {
        DefaultTableModel model = (DefaultTableModel) jTablePesquisa.getModel();
        model.setRowCount(0);
        String termoPesquisa = "%" + campoPesquisa.getText() + "%";
        List<Pessoa> lista = dao.listar("Pessoa.pesquisaNome", "nome", termoPesquisa);
        for (Pessoa p : lista) {
            String tipo = p.getTipoPessoa() != null && p.getTipoPessoa() == 'J' ? "Jurídica" : "Física";
            String identificador = p.getTipoPessoa() != null && p.getTipoPessoa() == 'J' ? p.getCnpj() : p.getCpf();
            model.addRow(new Object[]{
                p.getCodigo(),
                p.getNome(),
                tipo,
                identificador,
                p.getCidade()
            });
        }
    }
    private void consultarPessoaPeloCodigo() {
        String codigoStr = JOptionPane.showInputDialog(this, "Digite o código da Pessoa:");
        if (codigoStr != null && !codigoStr.trim().isEmpty()) {
            try {
                int codigo = Integer.parseInt(codigoStr);
                Pessoa p = (Pessoa) dao.consultar(Pessoa.class, codigo);
                if (p != null) {
                    preencherFormulario(p);
                    jTabbedPane1.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(this, "Pessoa não encontrado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Código inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        rotuloCodigo = new padraotela.Rotulo();
        campoCodigo = new padraotela.Campo();
        rotuloNome = new padraotela.Rotulo();
        campoNome = new padraotela.Campo();
        rotuloApelido = new padraotela.Rotulo();
        campoApelido = new padraotela.Campo();
        jPanel3 = new javax.swing.JPanel();
        jrFisica = new javax.swing.JRadioButton();
        jrJuridica = new javax.swing.JRadioButton();
        rotuloEmail = new padraotela.Rotulo();
        campoEmail = new padraotela.Campo();
        rotuloCPF = new padraotela.Rotulo();
        campoCPF = new padraotela.Campo();
        rotuloRG = new padraotela.Rotulo();
        campoRG = new padraotela.Campo();
        rotuloCNPJ = new padraotela.Rotulo();
        campoCNPJ = new padraotela.Campo();
        rotuloIE = new padraotela.Rotulo();
        campoIE = new padraotela.Campo();
        jSeparator1 = new javax.swing.JSeparator();
        rotuloEndereco = new padraotela.Rotulo();
        campoEndereco = new padraotela.Campo();
        rotuloNumero = new padraotela.Rotulo();
        campoNumero = new padraotela.Campo();
        rotuloBairro = new padraotela.Rotulo();
        campoBairro = new padraotela.Campo();
        rotuloCidade = new padraotela.Rotulo();
        campoCidade = new padraotela.Campo();
        rotuloUF = new padraotela.Rotulo();
        campoUF = new padraotela.Campo();
        rotuloCEP = new padraotela.Rotulo();
        campoCEP = new padraotela.Campo();
        bSalvar1 = new padraotela.BSalvar();
        bLimpar1 = new padraotela.BLimpar();
        bExcluir1 = new padraotela.BExcluir();
        bSair1 = new padraotela.BSair();
        bConsultar1 = new padraotela.BConsultar();
        jPanel4 = new javax.swing.JPanel();
        rotuloPesquisa = new padraotela.Rotulo();
        campoPesquisa = new padraotela.Campo();
        bPesquisar = new padraotela.BConsultar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePesquisa = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Pessoas");

        rotuloCodigo.setText("Código:");

        campoCodigo.setEditable(false);
        campoCodigo.setBackground(new java.awt.Color(220, 220, 220));

        rotuloNome.setText("Nome/Razão Social:");

        rotuloApelido.setText("Fantasia:");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de Pessoa"));

        buttonGroup1.add(jrFisica);
        jrFisica.setSelected(true);
        jrFisica.setText("Pessoa Física");
        jrFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrFisicaActionPerformed(evt);
            }
        });

        buttonGroup1.add(jrJuridica);
        jrJuridica.setText("Pessoa Jurídica");
        jrJuridica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrJuridicaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jrFisica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jrJuridica)
                .addGap(25, 25, 25))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrFisica)
                    .addComponent(jrJuridica))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        rotuloEmail.setText("E-mail:");

        rotuloCPF.setText("CPF:");

        rotuloRG.setText("RG:");

        rotuloCNPJ.setText("CNPJ:");

        rotuloIE.setText("Inscrição Estadual:");

        rotuloEndereco.setText("Endereço:");

        rotuloNumero.setText("Número:");

        rotuloBairro.setText("Bairro:");

        rotuloCidade.setText("Cidade:");

        rotuloUF.setText("UF:");

        rotuloCEP.setText("CEP:");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 287, Short.MAX_VALUE)
                        .addComponent(bSair1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(rotuloEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rotuloBairro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rotuloCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(campoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rotuloNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(campoBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rotuloCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rotuloUF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoUF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(campoCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(rotuloCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                                        .addComponent(rotuloEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(rotuloNome, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(bConsultar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rotuloApelido, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campoApelido, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(rotuloCPF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rotuloCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoCPF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(campoCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(rotuloRG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rotuloIE, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campoRG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(campoIE, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rotuloCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bConsultar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotuloNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rotuloApelido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoApelido, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotuloEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotuloCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rotuloRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoRG, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotuloCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rotuloIE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoIE, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotuloEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rotuloNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotuloBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rotuloCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rotuloUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoUF, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotuloCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSalvar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bLimpar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bSair1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("Cadastro", jPanel1);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisa"));

        rotuloPesquisa.setText("Pesquisar por nome:");

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
                "Código", "Nome", "Tipo", "CPF/CNPJ", "Cidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
        jScrollPane1.setViewportView(jTablePesquisa);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(rotuloPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(bPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotuloPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Pesquisa", jPanel4);

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

    private void jrFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrFisicaActionPerformed
        gerenciarCamposTipoPessoa();
    }//GEN-LAST:event_jrFisicaActionPerformed

    private void jrJuridicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrJuridicaActionPerformed
        gerenciarCamposTipoPessoa();
    }//GEN-LAST:event_jrJuridicaActionPerformed

    private void bSalvar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalvar1ActionPerformed
        salvarPessoa();
    }//GEN-LAST:event_bSalvar1ActionPerformed

    private void bLimpar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLimpar1ActionPerformed
        limparCampos();
    }//GEN-LAST:event_bLimpar1ActionPerformed

    private void bExcluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExcluir1ActionPerformed
        excluirPessoa();
    }//GEN-LAST:event_bExcluir1ActionPerformed

    private void bSair1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSair1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_bSair1ActionPerformed

    private void bPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPesquisarActionPerformed
        pesquisarPessoas();
    }//GEN-LAST:event_bPesquisarActionPerformed

    private void jTablePesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePesquisaMouseClicked
        if (evt.getClickCount() == 2) {
            int linha = jTablePesquisa.getSelectedRow();
            if (linha != -1) {
                int codigo = (int) jTablePesquisa.getValueAt(linha, 0);
                Pessoa p = (Pessoa) dao.consultar(Pessoa.class, codigo);
                if (p != null) {
                    preencherFormulario(p);
                    jTabbedPane1.setSelectedIndex(0);
                }
            }
        }
    }//GEN-LAST:event_jTablePesquisaMouseClicked

    private void bConsultar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConsultar1ActionPerformed
        // TODO add your handling code here:
        consultarPessoaPeloCodigo();
    }//GEN-LAST:event_bConsultar1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private padraotela.BConsultar bConsultar1;
    private padraotela.BExcluir bExcluir1;
    private padraotela.BLimpar bLimpar1;
    private padraotela.BConsultar bPesquisar;
    private padraotela.BSair bSair1;
    private padraotela.BSalvar bSalvar1;
    private javax.swing.ButtonGroup buttonGroup1;
    private padraotela.Campo campoApelido;
    private padraotela.Campo campoBairro;
    private padraotela.Campo campoCEP;
    private padraotela.Campo campoCNPJ;
    private padraotela.Campo campoCPF;
    private padraotela.Campo campoCidade;
    private padraotela.Campo campoCodigo;
    private padraotela.Campo campoEmail;
    private padraotela.Campo campoEndereco;
    private padraotela.Campo campoIE;
    private padraotela.Campo campoNome;
    private padraotela.Campo campoNumero;
    private padraotela.Campo campoPesquisa;
    private padraotela.Campo campoRG;
    private padraotela.Campo campoUF;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTablePesquisa;
    private javax.swing.JRadioButton jrFisica;
    private javax.swing.JRadioButton jrJuridica;
    private padraotela.Rotulo rotuloApelido;
    private padraotela.Rotulo rotuloBairro;
    private padraotela.Rotulo rotuloCEP;
    private padraotela.Rotulo rotuloCNPJ;
    private padraotela.Rotulo rotuloCPF;
    private padraotela.Rotulo rotuloCidade;
    private padraotela.Rotulo rotuloCodigo;
    private padraotela.Rotulo rotuloEmail;
    private padraotela.Rotulo rotuloEndereco;
    private padraotela.Rotulo rotuloIE;
    private padraotela.Rotulo rotuloNome;
    private padraotela.Rotulo rotuloNumero;
    private padraotela.Rotulo rotuloPesquisa;
    private padraotela.Rotulo rotuloRG;
    private padraotela.Rotulo rotuloUF;
    // End of variables declaration//GEN-END:variables
}
