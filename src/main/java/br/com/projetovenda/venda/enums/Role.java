package br.com.projetovenda.venda.enums;

public enum Role {
    ADMINISTRADOR,
    VENDEDOR;

    public String getLabel() {
        switch (this) {
            case ADMINISTRADOR:
                return "Administrador";
            case VENDEDOR:
                return "Vendedor";
            default:
                return this.name();
        }
    }
}
