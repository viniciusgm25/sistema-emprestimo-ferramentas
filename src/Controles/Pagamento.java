package Controles;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "pagamento")
@NamedQueries({
    @NamedQuery(name = "Pagamento.sumTotalByDateRange", query = "SELECT SUM(p.valorTotal) FROM Pagamento p WHERE p.dataPagamento BETWEEN :dataInicio AND :dataFim"),
    @NamedQuery(name = "Pagamento.countByDateRange", query = "SELECT COUNT(p) FROM Pagamento p WHERE p.dataPagamento BETWEEN :dataInicio AND :dataFim"),
    @NamedQuery(name = "Pagamento.findByDateRange", query = "SELECT p FROM Pagamento p WHERE p.dataPagamento BETWEEN :dataInicio AND :dataFim ORDER BY p.dataPagamento DESC")
})

public class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "cod_emprestimo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Emprestimo codEmprestimo;

    public Emprestimo getCodEmprestimo() {
        return codEmprestimo;
    }

    public void setCodEmprestimo(Emprestimo codEmprestimo) {
        this.codEmprestimo = codEmprestimo;
    }

    @Basic(optional = false)
    @Column(name = "forma_pagamento")
    private String formaPagamento;

    @Basic(optional = false)
    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Column(name = "valor_entrada")
    private BigDecimal valorEntrada;

    @Column(name = "numero_parcelas")
    private Integer numeroParcelas;

    @Column(name = "valor_parcela")
    private BigDecimal valorParcela;

    @Basic(optional = false)
    @Column(name = "data_pagamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPagamento;

    @Basic(optional = false)
    @Column(name = "status")
    private String status;

    public Pagamento() {
    }

    // GETTERS E SETTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorEntrada() {
        return valorEntrada;
    }

    public void setValorEntrada(BigDecimal valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public BigDecimal getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(BigDecimal valorParcela) {
        this.valorParcela = valorParcela;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Pagamento)) {
            return false;
        }
        Pagamento other = (Pagamento) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Controles.Pagamento[ id=" + id + " ]";
    }
}
