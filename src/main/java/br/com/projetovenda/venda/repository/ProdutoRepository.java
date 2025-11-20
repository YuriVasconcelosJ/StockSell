package br.com.projetovenda.venda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetovenda.venda.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Boolean existsByCodigoProduto(Integer codigoProduto);

    Optional<Produto> findByCodigoProduto(Integer codigoProduto);

}
