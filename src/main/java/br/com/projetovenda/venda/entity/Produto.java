package br.com.projetovenda.venda.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.projetovenda.venda.exception.EstoqueInsuficienteException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "preco_unitario", precision = 10, scale = 2, nullable = false)
    private BigDecimal preco;

    @Column(nullable = false, unique = true)
    private Integer codigoProduto;

    @Column(nullable = false)
    private Integer quantidade;

    @OneToMany(mappedBy = "produto")
    private List<ItemPedido> itens = new ArrayList<>();

    public Produto() {
    }

    public Produto(Long id, String nome, BigDecimal preco, Integer codigoProduto, Integer quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Integer codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public void incrementarEstoque(int quantidade) {
        this.quantidade += quantidade;
    }

    public void decrementarEstoque(int quantidade) {
        if (this.quantidade >= quantidade) {
            this.quantidade -= quantidade;
        } else {
            throw new EstoqueInsuficienteException(
                    "A quantidade a ser retirada é maior que está disponível em estoque. Quantidade em estoque: "
                            + this.quantidade + " Quantidade solicitada: " + quantidade);
        }

    }

    public void addItemPedido(ItemPedido item) {
        itens.add(item);
        item.setProduto(this);
    }

    public void removeItemPedido(ItemPedido item) {
        itens.remove(item);
        item.setProduto(null);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produto other = (Produto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
