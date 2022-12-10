package br.senai.sp.informatica.produtos;

import lombok.Data;

@Data
public class Alimenticio extends Produto {
    private String validade;
    private String produzido;

    @Override
    public String toString() {
        return String.format("%s Data de Validade: %s Data de Produção: %s",
            super.toString(), validade, produzido);
    }
}
