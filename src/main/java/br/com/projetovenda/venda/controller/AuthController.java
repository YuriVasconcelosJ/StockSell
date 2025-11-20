package br.com.projetovenda.venda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import br.com.projetovenda.venda.dto.FuncionarioPrincipal;
import br.com.projetovenda.venda.dto.autenticacao.AuthRequestDTO;
import br.com.projetovenda.venda.dto.autenticacao.AuthResponseDTO;
import br.com.projetovenda.venda.entity.Funcionario;
import br.com.projetovenda.venda.security.TokenService;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequest) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(authRequest.getCpf(), authRequest.getSenha());
        try {
            var authentication = authenticationManager.authenticate(authenticationToken);
            FuncionarioPrincipal principal = (FuncionarioPrincipal) authentication.getPrincipal();
            Funcionario funcionario = principal.getFuncionario();
            var token = tokenService.generateToken(funcionario);
            return ResponseEntity.ok(new AuthResponseDTO(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

    }
}