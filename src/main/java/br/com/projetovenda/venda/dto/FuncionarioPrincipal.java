package br.com.projetovenda.venda.dto;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.projetovenda.venda.entity.Funcionario;

public class FuncionarioPrincipal implements UserDetails {

    private final Funcionario funcionario;
    private final Collection<? extends GrantedAuthority> authorities;

    public FuncionarioPrincipal(Funcionario funcionario) {
        this.funcionario = funcionario;
        // Mapeia o Cargo do Funcionario para a Authority do Spring Security
        this.authorities = Collections.singletonList(
            new SimpleGrantedAuthority("ROLE_" + funcionario.getCargo().name())
        );
    }

    // Método para acessar a entidade Funcionario dentro do Principal
    public Funcionario getFuncionario() {
        return funcionario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Adicione o prefixo ROLE_ ao nome do Enum
        return Collections.singletonList(
            new SimpleGrantedAuthority("ROLE_" + funcionario.getCargo().name())
        );
    }

    @Override
    public String getPassword() {
        return funcionario.getSenha();
    }

    @Override
    public String getUsername() {
        return funcionario.getCpf();
    }

    // Implementações simples dos outros métodos de UserDetails
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}