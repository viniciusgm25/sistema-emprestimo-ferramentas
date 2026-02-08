package Controles;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.*;

@Entity
@Table(name = "produto")
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p"),
    @NamedQuery(name = "Produto.findByCodigo", query = "SELECT p FROM Produto p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Produto.findDisponiveis", query = "SELECT p FROM Produto p WHERE p.codigo NOT IN (SELECT e.produto.codigo FROM Emprestimo e WHERE e.dataDevolucaoReal IS NULL)"),
    @NamedQuery(name = "Produto.pesquisaNome", query = "SELECT p FROM Produto p WHERE p.nome LIKE :nome")
})

public class Produto implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "produto")
    private Collection<Emprestimo> emprestimoCollection;

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;

    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor_diaria")
    private BigDecimal valorDiaria;

    @Column(name = "marca")
    private String marca;

    @Column(name = "codigo_original")
    private String codigoOriginal;

    @Column(name = "caminho_imagem")
    private String caminhoImagem;

    public Produto() {
    }

    // GETTERS E SETTERS
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(BigDecimal valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodigoOriginal() {
        return codigoOriginal;
    }

    public void setCodigoOriginal(String codigoOriginal) {
        this.codigoOriginal = codigoOriginal;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    // Métodos hashCode, equals e toString
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        return !((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo)));
    }

    @Override
    public String toString() {
        return "Controles.Produto[ codigo=" + codigo + " ]";
    }

    public Collection<Emprestimo> getEmprestimoCollection() {
        return emprestimoCollection;
    }

    public void setEmprestimoCollection(Collection<Emprestimo> emprestimoCollection) {
        this.emprestimoCollection = emprestimoCollection;
    }
}
