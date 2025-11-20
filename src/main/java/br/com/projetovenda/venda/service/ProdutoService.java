package br.com.projetovenda.venda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetovenda.venda.dto.produto.ProdutoCreateDTO;
import br.com.projetovenda.venda.dto.produto.ProdutoResponseDTO;
import br.com.projetovenda.venda.dto.produto.ProdutoUpdateDTO;
import br.com.projetovenda.venda.entity.Produto;
import br.com.projetovenda.venda.exception.ResourceConflictException;
import br.com.projetovenda.venda.exception.ResourceNotFoundException;
import br.com.projetovenda.venda.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoResponseDTO cadastrarProduto(ProdutoCreateDTO produtoCreateDTO) {
        if (produtoRepository.existsByCodigoProduto(produtoCreateDTO.getCodigoProduto())) {
            throw new ResourceConflictException(
                    "O código de barras do produto a ser cadastrado já existe em nosso estoque.");
        }

        Produto produto = new Produto();
        produto.setNome(produtoCreateDTO.getNome());
        produto.setCodigoProduto(produtoCreateDTO.getCodigoProduto());
        produto.setPreco(produtoCreateDTO.getPreco());
        produto.setQuantidade(produtoCreateDTO.getQuantidade());

        Produto salvo = produtoRepository.save(produto);

        return new ProdutoResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getPreco(),
                salvo.getCodigoProduto(),
                salvo.getQuantidade());
    }

    public ProdutoResponseDTO buscarProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar o produto do id: " + id));

        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO();

        produtoResponseDTO.setId(produto.getId());
        produtoResponseDTO.setCodigoProduto(produto.getCodigoProduto());
        produtoResponseDTO.setNome(produto.getNome());
        produtoResponseDTO.setPreco(produto.getPreco());
        produtoResponseDTO.setQuantidade(produto.getQuantidade());

        return produtoResponseDTO;

    }

    public List<ProdutoResponseDTO> listarTodos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(prod -> new ProdutoResponseDTO(prod.getId(), prod.getNome(), prod.getPreco(),
                prod.getCodigoProduto(), prod.getQuantidade())).toList();
    }

    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoUpdateDTO produtoUpdateDTO) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("não foi possível encontrar o produto do id: " + id));

        if (produtoUpdateDTO.getPreco() != null)
            produto.setPreco(produtoUpdateDTO.getPreco());
        if (produtoUpdateDTO.getQuantidade() != null)
            produto.setQuantidade(produtoUpdateDTO.getQuantidade());

        Produto atualizado = produtoRepository.save(produto);

        return new ProdutoResponseDTO(
                atualizado.getId(),
                atualizado.getNome(),
                atualizado.getPreco(),
                atualizado.getCodigoProduto(),
                atualizado.getQuantidade());
    }

    public void deletarProduto(Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("O produto do id: " + id + " não consta em nosso estoque."));
        produtoRepository.delete(produto);
    }
}
