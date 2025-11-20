package br.com.projetovenda.venda.dto.produto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProdutoCreateDTO {

    @NotBlank
    private String nome;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
    private BigDecimal preco;

    @NotNull
    private Integer codigoProduto;

    @NotNull
    @Min(value = 0, message = "Quantidade não pode ser negativa")
    @Max(value = 999, message = "Quantidade máxima é 999")
    private Integer quantidade;

    public ProdutoCreateDTO() {
    }

    public ProdutoCreateDTO(String nome, BigDecimal preco, Integer codigoProduto, Integer quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
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

}
