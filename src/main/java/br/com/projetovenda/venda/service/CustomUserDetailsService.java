package br.com.projetovenda.venda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.projetovenda.venda.entity.Funcionario;
import br.com.projetovenda.venda.repository.FuncionarioRepository;

/**
 * CONCLUÍDO!
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        String cpfLimpo = cpf.trim();
        Funcionario funcionario = funcionarioRepository.findByCpf(cpfLimpo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com CPF: " + cpfLimpo));

        return User.builder().username(funcionario.getCpf()).password(funcionario.getSenha())
                .roles(funcionario.getCargo().name()).build();
    }

}
