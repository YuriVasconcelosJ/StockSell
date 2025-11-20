package br.com.projetovenda.venda.dto.produto;

import java.math.BigDecimal;

public class ProdutoResponseDTO {

    private Long id;

    private String nome;

    private BigDecimal preco;

    private Integer codigoProduto;

	private Integer quantidade;

    public ProdutoResponseDTO() {
    }

	public ProdutoResponseDTO(Long id, String nome, BigDecimal preco, Integer codigoProduto, Integer quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
