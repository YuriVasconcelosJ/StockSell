package br.com.projetovenda.venda.dto.funcionario;

import java.time.LocalDate;

import br.com.projetovenda.venda.enums.Role;

/**
 * Concluído!
 */

public class FuncionarioResponseDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private LocalDate dataAdmissao;
    private Role cargo;
                                                   
    public FuncionarioResponseDTO() {
    }

    public FuncionarioResponseDTO(Long id, String nome, String cpf, String email, String telefone,
            LocalDate dataAdmissao, Role cargo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.dataAdmissao = dataAdmissao;
        this.cargo = cargo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public Role getCargo() {
        return cargo;
    }
    
    

}

