package br.com.projetovenda.venda.dto.produto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class ProdutoUpdateDTO {

    @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
    private BigDecimal preco;

    @Min(value = 0, message = "Quantidade não pode ser negativa")
    @Max(value = 999, message = "Quantidade máxima é 999")
    private Integer quantidade;

    public ProdutoUpdateDTO() {
    }

    public ProdutoUpdateDTO(BigDecimal preco, Integer quantidade) {
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}
