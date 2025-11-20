package br.com.projetovenda.venda.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projetovenda.venda.dto.cliente.ClienteComPedidosDTO;
import br.com.projetovenda.venda.dto.cliente.ClienteCreateDTO;
import br.com.projetovenda.venda.dto.cliente.ClienteResponseDTO;
import br.com.projetovenda.venda.dto.cliente.ClienteUpdateDTO;
import br.com.projetovenda.venda.dto.itemPedido.ItemPedidoDTO;
import br.com.projetovenda.venda.entity.Cliente;
import br.com.projetovenda.venda.service.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos() {
        List<ClienteResponseDTO> clientes = clienteService.listarTodos();
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClienteComPedidosDTO<ItemPedidoDTO>> cliente(@PathVariable Long id) {
        ClienteComPedidosDTO<ItemPedidoDTO> cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping()
    public ResponseEntity<ClienteResponseDTO> cadastrar(@Valid @RequestBody ClienteCreateDTO clienteCreateDTO) {
        ClienteResponseDTO cliente = clienteService.cadastrar(clienteCreateDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();

        return ResponseEntity.created(uri).body(cliente);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id,
            @RequestBody ClienteUpdateDTO clienteUpdateDTO) {
        ClienteResponseDTO cliente = clienteService.atualizarDados(id, clienteUpdateDTO);
        return ResponseEntity.ok().body(cliente);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
