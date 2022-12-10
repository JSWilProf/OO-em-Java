package br.senai.sp.informatica.produtos;

import lombok.Data;

@Data
public class Produto {
    private int codigo;
    private String descricao;
    private Double valor;
    private Fornecedor fornecedor;

    public String toString() {
        return String.format("Código: %d Descr.: %s Valor: R$ %,.2f %s", codigo, descricao, valor, fornecedor);
    }
}
