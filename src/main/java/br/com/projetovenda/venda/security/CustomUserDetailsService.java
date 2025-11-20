package br.com.projetovenda.venda.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetovenda.venda.dto.FuncionarioPrincipal;
import br.com.projetovenda.venda.entity.Funcionario;
import br.com.projetovenda.venda.repository.FuncionarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        Funcionario funcionario = funcionarioRepository.findByCpf(cpf)
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o CPF: " + cpf));
        System.out.println(">>> USUÁRIO CARREGADO: " + funcionario.getCpf() + " - SENHA HASH: " + funcionario.getSenha());
        return new FuncionarioPrincipal(funcionario);
        
    }
}

