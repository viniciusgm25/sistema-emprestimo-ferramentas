package Controles;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "emprestimo")
@NamedQueries({
    @NamedQuery(name = "Emprestimo.findAll", query = "SELECT e FROM Emprestimo e"),
    @NamedQuery(name = "Emprestimo.findAllAtivos", query = "SELECT e FROM Emprestimo e WHERE e.dataDevolucaoReal IS NULL ORDER BY e.dataPrevistaDevolucao ASC"),
    @NamedQuery(name = "Emprestimo.findAtivoByProduto", query = "SELECT e FROM Emprestimo e WHERE e.produto = :produto AND e.dataDevolucaoReal IS NULL"),
    @NamedQuery(name = "Emprestimo.findByDateRange", query = "SELECT e FROM Emprestimo e WHERE e.dataEmprestimo BETWEEN :dataInicio AND :dataFim ORDER BY e.dataEmprestimo DESC"),
    @NamedQuery(name = "Emprestimo.findByPessoa", query = "SELECT e FROM Emprestimo e WHERE e.pessoa = :pessoa ORDER BY e.dataEmprestimo DESC"),
    
    // --- Consultas para "EM ABERTO" (dataDevolucaoReal é NULA) ---
    @NamedQuery(name = "Emprestimo.findAllAbertos", query = "SELECT e FROM Emprestimo e WHERE e.dataDevolucaoReal IS NULL"),
    @NamedQuery(name = "Emprestimo.findByDateRangeAbertos", query = "SELECT e FROM Emprestimo e WHERE e.dataDevolucaoReal IS NULL AND e.dataEmprestimo BETWEEN :dataInicio AND :dataFim"),

    // --- Consultas para "ENCERRADOS" (dataDevolucaoReal NÃO é NULA) ---
    @NamedQuery(name = "Emprestimo.findAllEncerrados", query = "SELECT e FROM Emprestimo e WHERE e.dataDevolucaoReal IS NOT NULL"),
    @NamedQuery(name = "Emprestimo.findByDateRangeEncerrados", query = "SELECT e FROM Emprestimo e WHERE e.dataDevolucaoReal IS NOT NULL AND e.dataEmprestimo BETWEEN :dataInicio AND :dataFim")
})
public class Emprestimo implements Serializable {

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "codEmprestimo")
    private NotaFiscal notaFiscal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codEmprestimo")
    private Collection<Pagamento> pagamentoCollection;

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "cod_produto", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Produto produto;

    @JoinColumn(name = "cod_pessoa", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Pessoa pessoa;

    @Basic(optional = false)
    @Column(name = "data_emprestimo")
    @Temporal(TemporalType.DATE)
    private Date dataEmprestimo;

    @Basic(optional = false)
    @Column(name = "data_prevista_devolucao")
    @Temporal(TemporalType.DATE)
    private Date dataPrevistaDevolucao;

    @Column(name = "data_devolucao_real")
    @Temporal(TemporalType.DATE)
    private Date dataDevolucaoReal;

    @Column(name = "valor_total_cobrado")
    private BigDecimal valorTotalCobrado;

    // --- NOVOS CAMPOS ---
    @Column(name = "caminho_contrato_assinado")
    private String caminhoContratoAssinado;

    @Column(name = "localizacao_fisica")
    private String localizacaoFisica;

    public Emprestimo() {
    }

    // GETTERS E SETTERS (EXISTENTES E NOVOS)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(Date dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public Date getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setDataDevolucaoReal(Date dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }

    public BigDecimal getValorTotalCobrado() {
        return valorTotalCobrado;
    }

    public void setValorTotalCobrado(BigDecimal valorTotalCobrado) {
        this.valorTotalCobrado = valorTotalCobrado;
    }

    public String getCaminhoContratoAssinado() {
        return caminhoContratoAssinado;
    }

    public void setCaminhoContratoAssinado(String caminhoContratoAssinado) {
        this.caminhoContratoAssinado = caminhoContratoAssinado;
    }

    public String getLocalizacaoFisica() {
        return localizacaoFisica;
    }

    public void setLocalizacaoFisica(String localizacaoFisica) {
        this.localizacaoFisica = localizacaoFisica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Emprestimo)) {
            return false;
        }
        Emprestimo other = (Emprestimo) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Controles.Emprestimo[ id=" + id + " ]";
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public Collection<Pagamento> getPagamentoCollection() {
        return pagamentoCollection;
    }

    public void setPagamentoCollection(Collection<Pagamento> pagamentoCollection) {
        this.pagamentoCollection = pagamentoCollection;
    }
}
