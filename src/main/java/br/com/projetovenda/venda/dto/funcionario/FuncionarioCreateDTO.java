package br.com.projetovenda.venda.dto.funcionario;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
/**
 * CONCLUÍDA!
 */
public class FuncionarioCreateDTO {

    @NotBlank
    @Size(max = 150)
    private String nome;

    @Pattern(regexp = "\\d{14}", message = "CPF deve conter 14 dígitos")
    private String cpf;

    @NotBlank
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(min = 8, max = 20, message = "A senha deve ter no mínimo 8 e no máximo 20 caracteres")
    private String senha;

    @NotBlank
    private String telefone;

    @NotNull(message = "A data de admissão é obrigatória")
    @PastOrPresent(message = "A data não pode ser futura.")
    private LocalDate dataAdmissao;

    public FuncionarioCreateDTO() {
    }

    public FuncionarioCreateDTO(String nome,
            String cpf,
            String email,
            String senha,
            String telefone,
            LocalDate dataAdmissao) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.dataAdmissao = dataAdmissao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(LocalDate dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

}
