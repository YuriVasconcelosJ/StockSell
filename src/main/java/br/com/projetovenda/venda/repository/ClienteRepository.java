package br.com.projetovenda.venda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetovenda.venda.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByCpf(String cpf);

}