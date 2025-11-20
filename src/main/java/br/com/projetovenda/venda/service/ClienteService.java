package br.com.projetovenda.venda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetovenda.venda.dto.cliente.ClienteComPedidosDTO;
import br.com.projetovenda.venda.dto.cliente.ClienteCreateDTO;
import br.com.projetovenda.venda.dto.cliente.ClienteResponseDTO;
import br.com.projetovenda.venda.dto.cliente.ClienteUpdateDTO;
import br.com.projetovenda.venda.dto.itemPedido.ItemPedidoDTO;
import br.com.projetovenda.venda.entity.Cliente;
import br.com.projetovenda.venda.exception.ResourceConflictException;
import br.com.projetovenda.venda.exception.ResourceNotFoundException;
import br.com.projetovenda.venda.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteComPedidosDTO<ItemPedidoDTO> buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com o id: " + id));

        List<ItemPedidoDTO> pedidosDTO = cliente.getPedidos().stream()
                .flatMap(pedido -> pedido.getItemPedidos().stream())
                .map(item -> new ItemPedidoDTO(
                        item.getProduto().getId(),
                        item.getProduto().getNome(),
                        item.getProduto().getCodigoProduto(),
                        item.getPrecoUnitario(),
                        item.getQuantidade()))
                .toList();

        return new ClienteComPedidosDTO<ItemPedidoDTO>(cliente, pedidosDTO);
    }

    public ClienteResponseDTO cadastrar(ClienteCreateDTO clienteCreateDTO) {
        if (clienteRepository.existsByCpf(clienteCreateDTO.getCpf())) {
            throw new ResourceConflictException("CPF já cadastrado em nosso sistema: " + clienteCreateDTO.getCpf());
        }
        Cliente cliente = new Cliente();
        cliente.setCpf(clienteCreateDTO.getCpf());
        cliente.setNome(clienteCreateDTO.getNome());
        cliente.setTelefone(clienteCreateDTO.getTelefone());
        cliente.setEmail(clienteCreateDTO.getEmail());
        Cliente clienteSalvo = clienteRepository.save(cliente);

        return new ClienteResponseDTO(clienteSalvo);

    }

    public List<ClienteResponseDTO> listarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(cliente -> new ClienteResponseDTO(cliente)).toList();
    }

    public ClienteResponseDTO atualizarDados(Long id, ClienteUpdateDTO clienteUpdateDTO) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Não foi possível encontrar o usuario com o id: " + id));
        if (clienteUpdateDTO.getNome() != null)
            cliente.setNome(clienteUpdateDTO.getNome());
        if (clienteUpdateDTO.getEmail() != null)
            cliente.setEmail(clienteUpdateDTO.getEmail());
        if (clienteUpdateDTO.getTelefone() != null)
            cliente.setTelefone(clienteUpdateDTO.getTelefone());

        clienteRepository.save(cliente);

        return new ClienteResponseDTO(cliente);
    }

    public void deletar(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi possível deletar o cliente do id: " + id));
        clienteRepository.delete(cliente);
    }

}
