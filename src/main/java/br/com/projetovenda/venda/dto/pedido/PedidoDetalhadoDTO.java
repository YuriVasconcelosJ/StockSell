package br.com.projetovenda.venda.dto.pedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.projetovenda.venda.dto.itemPedido.ItemPedidoDTO;

public class PedidoDetalhadoDTO {

    private String nomeFuncionario;

    private BigDecimal valorTotal;

    private List<ItemPedidoDTO> itemPedidos = new ArrayList<>();

    public PedidoDetalhadoDTO() {
    }

    public PedidoDetalhadoDTO(String nomeFuncionario, BigDecimal valorTotal) {
        this.nomeFuncionario = nomeFuncionario;
        this.valorTotal = valorTotal;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemPedidoDTO> getItemPedidos() {
        return itemPedidos;
    }

    public void setItemPedidos(List<ItemPedidoDTO> itemPedidos) {
        this.itemPedidos = itemPedidos;
    }

}
