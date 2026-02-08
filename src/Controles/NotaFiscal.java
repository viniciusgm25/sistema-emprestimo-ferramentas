/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controles;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Vinicius
 */
@Entity
@Table(name = "nota_fiscal")
@NamedQueries({
    @NamedQuery(name = "NotaFiscal.findAll", query = "SELECT n FROM NotaFiscal n"),
    @NamedQuery(name = "NotaFiscal.findById", query = "SELECT n FROM NotaFiscal n WHERE n.id = :id"),
    @NamedQuery(name = "NotaFiscal.findByNumeroNf", query = "SELECT n FROM NotaFiscal n WHERE n.numeroNf = :numeroNf"),
    @NamedQuery(name = "NotaFiscal.findByDataEmissao", query = "SELECT n FROM NotaFiscal n WHERE n.dataEmissao = :dataEmissao"),
    @NamedQuery(name = "NotaFiscal.findByValorServico", query = "SELECT n FROM NotaFiscal n WHERE n.valorServico = :valorServico"),
    @NamedQuery(name = "NotaFiscal.findMaxNumero", query = "SELECT MAX(n.numeroNf) FROM NotaFiscal n"),
    @NamedQuery(name = "NotaFiscal.findByEmprestimo", query = "SELECT n FROM NotaFiscal n WHERE n.codEmprestimo.id = :emprestimoId"),
    @NamedQuery(name = "NotaFiscal.findByDescricaoServico", query = "SELECT n FROM NotaFiscal n WHERE n.descricaoServico = :descricaoServico")

})
public class NotaFiscal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "numero_nf")
    private long numeroNf;
    @Basic(optional = false)
    @Column(name = "data_emissao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEmissao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "valor_servico")
    private BigDecimal valorServico;
    @Column(name = "descricao_servico")
    private String descricaoServico;
    @JoinColumn(name = "cod_emprestimo", referencedColumnName = "id") // <-- CORRIGIDO!
    @OneToOne(optional = false)
    private Emprestimo codEmprestimo;

    public NotaFiscal() {
    }

    public NotaFiscal(Integer id) {
        this.id = id;
    }

    public NotaFiscal(Integer id, long numeroNf, Date dataEmissao, BigDecimal valorServico) {
        this.id = id;
        this.numeroNf = numeroNf;
        this.dataEmissao = dataEmissao;
        this.valorServico = valorServico;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getNumeroNf() {
        return numeroNf;
    }

    public void setNumeroNf(long numeroNf) {
        this.numeroNf = numeroNf;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public BigDecimal getValorServico() {
        return valorServico;
    }

    public void setValorServico(BigDecimal valorServico) {
        this.valorServico = valorServico;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public Emprestimo getCodEmprestimo() {
        return codEmprestimo;
    }

    public void setCodEmprestimo(Emprestimo codEmprestimo) {
        this.codEmprestimo = codEmprestimo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotaFiscal)) {
            return false;
        }
        NotaFiscal other = (NotaFiscal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Controles.NotaFiscal[ id=" + id + " ]";
    }

}
