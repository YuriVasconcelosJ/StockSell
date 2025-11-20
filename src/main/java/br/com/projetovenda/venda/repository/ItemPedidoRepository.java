package br.com.projetovenda.venda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetovenda.venda.entity.ItemPedido;
import br.com.projetovenda.venda.entity.id.ItemPedidoID;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoID>{
    
} 