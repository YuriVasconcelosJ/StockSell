package br.com.projetovenda.venda.dto.itemPedido;

import java.math.BigDecimal;

public class ItemPedidoDTO {

    private Long produtoId;
    private String nomeProduto;
    private Integer codigoProduto;
    private BigDecimal precoUnitario;
    private Integer quantidade;

    public ItemPedidoDTO() {
    }

    public ItemPedidoDTO(Long produtoId, String nomeProduto, Integer codigoProduto, BigDecimal precoUnitario,
            Integer quantidade) {
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.codigoProduto = codigoProduto;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
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

    public Integer getCodgioProduto() {
        return codigoProduto;
    }

    public void setCodioProduto(Integer codioProduto) {
        this.codigoProduto = codioProduto;
    }

}
