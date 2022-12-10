package br.senai.sp.informatica.produtos;

import lombok.Data;

@Data
public class Fornecedor {
    private String codigo;
    private String fornecedor;

    @Override
    public String toString() {
        return "Código: " + codigo  + " Fornecedor: " + fornecedor;
    }
}
