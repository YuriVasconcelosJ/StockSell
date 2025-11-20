package br.com.projetovenda.venda.dto.autenticacao;

public class AuthResponseDTO {

    private String mensagem;

    public AuthResponseDTO(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
