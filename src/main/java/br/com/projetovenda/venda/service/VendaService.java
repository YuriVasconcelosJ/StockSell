package br.com.projetovenda.venda.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetovenda.venda.dto.itemPedido.ItemPedidoDTO;
import br.com.projetovenda.venda.dto.pedido.PedidoCreateDTO;
import br.com.projetovenda.venda.dto.pedido.PedidoResumoDTO;
import br.com.projetovenda.venda.entity.Cliente;
import br.com.projetovenda.venda.entity.ItemPedido;
import br.com.projetovenda.venda.entity.Pedido;
import br.com.projetovenda.venda.entity.Produto;
import br.com.projetovenda.venda.exception.ResourceConflictException;
import br.com.projetovenda.venda.exception.ResourceNotFoundException;
import br.com.projetovenda.venda.repository.ClienteRepository;
import br.com.projetovenda.venda.repository.PedidoRepository;
import br.com.projetovenda.venda.repository.ProdutoRepository;

@Service
public class VendaService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public PedidoResumoDTO realizarVenda(PedidoCreateDTO pedidoCreateDTO) {
        
        Cliente cliente = clienteRepository.findById(pedidoCreateDTO.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setNomeFuncionario(pedidoCreateDTO.getNomeFuncionario());

        List<ItemPedido> listaDeItens = new ArrayList<>();
        BigDecimal totalDaVenda = BigDecimal.ZERO;

        for (ItemPedidoDTO itemDTO : pedidoCreateDTO.getItems()) {
            
            Produto produto = produtoRepository.findByCodigoProduto(itemDTO.getCodgioProduto())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Produto não encontrado: " + itemDTO.getCodgioProduto()));
            produto.decrementarEstoque(itemDTO.getQuantidade()); 
            
            BigDecimal subtotal = produto.getPreco().multiply(BigDecimal.valueOf(itemDTO.getQuantidade()));
            
            ItemPedido item = new ItemPedido();
            item.setPedido(pedido); 
            item.setProduto(produto);
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPrecoUnitario(produto.getPreco()); 
            item.setSubtotal(subtotal);

            listaDeItens.add(item);
            totalDaVenda = totalDaVenda.add(subtotal);
        }

        pedido.setItemPedidos(listaDeItens);
        pedido.setValorTotal(totalDaVenda);

        Pedido pedidoSalvo = pedidoRepository.save(pedido); 

        return new PedidoResumoDTO(pedidoSalvo.getId(), pedido.getValorTotal());
    }
}