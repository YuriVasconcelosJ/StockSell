package br.com.projetovenda.venda.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projetovenda.venda.dto.produto.ProdutoCreateDTO;
import br.com.projetovenda.venda.dto.produto.ProdutoResponseDTO;
import br.com.projetovenda.venda.dto.produto.ProdutoUpdateDTO;
import br.com.projetovenda.venda.entity.Produto;
import br.com.projetovenda.venda.repository.ProdutoRepository;
import br.com.projetovenda.venda.service.ProdutoService;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<?> cadastrarProduto(@Valid @RequestBody ProdutoCreateDTO produtoCreateDTO) {
        ProdutoResponseDTO produto = produtoService.cadastrarProduto(produtoCreateDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produto.getId())
                .toUri();
        
        return ResponseEntity.created(uri).body(produto);
    }
    

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> buscarProduto(@PathVariable Long id) {
        ProdutoResponseDTO produtoResponseDTO = produtoService.buscarProduto(id);
        return ResponseEntity.ok().body(produtoResponseDTO);

    }

    @GetMapping
    public ResponseEntity<?> listarProdutos() {
        List<ProdutoResponseDTO> produtos = produtoService.listarTodos();
        if (produtos.isEmpty()) {
            return ResponseEntity.ok().body(Map.of(
                    "mensagem", "Nenhum produto encontrado em nosso estoque.",
                    "produtos", produtos));

        }
        return ResponseEntity.ok().body(Map.of(
                "mensagem", "Produtos encontrados com sucesso.",
                "produtos", produtos));

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> atualizarProduto(@Valid @PathVariable Long id, @RequestBody ProdutoUpdateDTO produtoUpdateDTO) {
        ProdutoResponseDTO produto = produtoService.atualizarProduto(id, produtoUpdateDTO);
        return ResponseEntity.ok().body(Map.of("mensagem", "Os dados do produto foram atualizados com sucesso!",
                "produto", produto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

}
