package br.com.projetovenda.venda.entity.id;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ItemPedidoID implements Serializable {

    @Column(name = "pedido_id")
    private Long pedidoID;

    @Column(name = "produto_id")
    private Long produtoID;

    public ItemPedidoID() {
    }

    public ItemPedidoID(Long pedidoID, Long produtoID) {
        this.pedidoID = pedidoID;
        this.produtoID = produtoID;
    }

    public Long getPedidoID() {
        return pedidoID;
    }

    public void setPedidoID(Long pedidoID) {
        this.pedidoID = pedidoID;
    }

    public Long getProdutoID() {
        return produtoID;
    }

    public void setProdutoID(Long produtoID) {
        this.produtoID = produtoID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pedidoID == null) ? 0 : pedidoID.hashCode());
        result = prime * result + ((produtoID == null) ? 0 : produtoID.hashCode());
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
        ItemPedidoID other = (ItemPedidoID) obj;
        if (pedidoID == null) {
            if (other.pedidoID != null)
                return false;
        } else if (!pedidoID.equals(other.pedidoID))
            return false;
        if (produtoID == null) {
            if (other.produtoID != null)
                return false;
        } else if (!produtoID.equals(other.produtoID))
            return false;
        return true;
    }
}
