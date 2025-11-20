package br.com.projetovenda.venda.dto.funcionario;

import br.com.projetovenda.venda.enums.Role;

public class FuncionarioUpdateDTO {

    private String nome;
    private String telefone;
    private String email;
    private Role cargo;

    public FuncionarioUpdateDTO() {
    }

    public FuncionarioUpdateDTO(String nome, String telefone, String email, Role cargo) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public Role getCargo() {
        return cargo;
    }
}
