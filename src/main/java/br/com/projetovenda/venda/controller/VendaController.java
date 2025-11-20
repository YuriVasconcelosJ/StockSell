package br.com.projetovenda.venda.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.projetovenda.venda.dto.pedido.PedidoCreateDTO;
import br.com.projetovenda.venda.dto.pedido.PedidoResumoDTO;
import br.com.projetovenda.venda.service.VendaService;
import jakarta.validation.Valid;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<PedidoResumoDTO> postMethodName(@Valid @RequestBody PedidoCreateDTO pedidoCreateDTO) {
        PedidoResumoDTO pedidoResumoDTO = vendaService.realizarVenda(pedidoCreateDTO);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pedidoResumoDTO.getId())
                .toUri();
        
        return ResponseEntity.created(uri).body(pedidoResumoDTO);
    }
    
}
