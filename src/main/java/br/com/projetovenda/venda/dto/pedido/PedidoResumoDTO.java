package br.com.projetovenda.venda.dto.pedido;

import java.math.BigDecimal;

public class PedidoResumoDTO {

    private Long id;

    private BigDecimal valorTotal;

    public PedidoResumoDTO(){
    }

    public PedidoResumoDTO(Long id, BigDecimal valorTotal) {
        this.id = id;
        this.valorTotal = valorTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

}
