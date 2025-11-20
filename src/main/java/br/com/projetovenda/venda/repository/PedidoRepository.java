package br.com.projetovenda.venda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetovenda.venda.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
   
} 
