package br.com.projetovenda.venda.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetovenda.venda.entity.Funcionario;
import br.com.projetovenda.venda.enums.Role;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
  
    Optional<Funcionario> findByEmail(String email);

    Optional<Funcionario> findByCpf(String cpf);

    Optional<Funcionario> findById(Long id);

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    List<Funcionario> findByCargo(Role cargo);
}
