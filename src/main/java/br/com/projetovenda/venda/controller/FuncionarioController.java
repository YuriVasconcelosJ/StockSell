package br.com.projetovenda.venda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetovenda.venda.dto.funcionario.FuncionarioCreateDTO;
import br.com.projetovenda.venda.dto.funcionario.FuncionarioResponseDTO;
import br.com.projetovenda.venda.dto.funcionario.FuncionarioUpdateDTO;
import br.com.projetovenda.venda.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping(path = "funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping(path = "/")
    public ResponseEntity<List<FuncionarioResponseDTO>> listarTodos() {
        List<FuncionarioResponseDTO> funcionarios = funcionarioService.listarTodos();
        if (funcionarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(funcionarios);
    }

    @PostMapping(path = "/cadastrar")
    public ResponseEntity<FuncionarioResponseDTO> cadastrarFuncionario(
            @RequestBody @Valid FuncionarioCreateDTO funcionarioCreateDTO) {
        FuncionarioResponseDTO funcionarioSalvo = funcionarioService.cadastrar(funcionarioCreateDTO);
        return ResponseEntity.ok(funcionarioSalvo);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<FuncionarioResponseDTO> atualizarDados(@PathVariable Long id, @RequestBody FuncionarioUpdateDTO funcionario) {
        FuncionarioResponseDTO funcionarioResponseDTO = funcionarioService.atualizarDados(id, funcionario);
        return ResponseEntity.ok(funcionarioResponseDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deletarFuncionario(@PathVariable Long id) {
        funcionarioService.deletarFuncionario(id);
        return ResponseEntity.noContent().build();
    }

}
