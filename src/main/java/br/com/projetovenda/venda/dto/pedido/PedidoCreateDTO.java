package br.com.projetovenda.venda.dto.pedido;

import java.util.ArrayList;
import java.util.List;

import br.com.projetovenda.venda.dto.itemPedido.ItemPedidoDTO;

public class PedidoCreateDTO {

    private String nomeFuncionario;

    private Long clienteId;

    private List<ItemPedidoDTO> items = new ArrayList<>();

    public PedidoCreateDTO() {
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
    
    public List<ItemPedidoDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemPedidoDTO> items) {
        this.items = items;
    }

}
