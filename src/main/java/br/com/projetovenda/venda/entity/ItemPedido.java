package br.com.projetovenda.venda.entity;

import java.math.BigDecimal;

import br.com.projetovenda.venda.entity.id.ItemPedidoID;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @EmbeddedId
    private ItemPedidoID id = new ItemPedidoID();

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal precoUnitario;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal subtotal;

    @Max(999)
    @Min(1)
    @Column(nullable = false)
    private Integer quantidade;

    @ManyToOne
    @MapsId("pedidoID")
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @MapsId("produtoID")
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public ItemPedido() {
    }

    public ItemPedido(BigDecimal precoUnitario, Integer quantidade, Pedido pedido, Produto produto) {
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.subtotal = precoUnitario.multiply(BigDecimal.valueOf(quantidade));
        this.pedido = pedido;
        this.produto = produto;
    }

    public ItemPedidoID getId() {
        return id;
    }

    public void setId(ItemPedidoID id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
        if (!pedido.getItemPedidos().contains(this)) {
            pedido.getItemPedidos().add(this);
        }
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        if (!produto.getItens().contains(this)) {
            produto.getItens().add(this);
        }
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
        ItemPedido other = (ItemPedido) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
