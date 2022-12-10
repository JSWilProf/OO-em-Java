package br.senai.sp.informatica.produtos;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static br.senai.sp.informatica.biblioteca.Console.*;

public class CadastraProdutos {
    public static void main(String[] args) {
        var lista = new ArrayList<Produto>();

        String op;
        do {
            op = readString("""
                Cadastro de Produtos
                
                A - Alimentício
                E - Eletrodoméstico
                S - Sair
                
                opção""").toLowerCase();

            if(op.equals("a")) {
                lista.add(cadastra(new Alimenticio()));
            } else if(op.equals("e")) {
                lista.add(cadastra(new Eletrodomestico()));
            }
        } while (!op.equals("s"));

        System.out.println(
            lista.stream().map(Produto::toString).collect(Collectors.joining("\n")));
    }

    static Produto cadastra(Produto produto) {
        produto.setCodigo(readInt("Informe o Código"));
        produto.setDescricao(readString("Informe a Descrição"));
        produto.setValor(readDouble("Informe o Valor"));

        var fornecedor = new Fornecedor();
        fornecedor.setCodigo(readString("Informe o Código do Fornecedor"));
        fornecedor.setFornecedor(readString("Informe o Nome do Fornecedor"));

        produto.setFornecedor(fornecedor);

        if(produto instanceof Alimenticio alimenticio) {
            alimenticio.setValidade(readString("Informe a Data de Validade"));
            alimenticio.setProduzido(readString("Informe a Data de Produção"));
        } else {
            var eletro = (Eletrodomestico)produto;
            eletro.setVoltagem(readString("Informe a Voltagem"));
            eletro.setPotencia(readString("Informe a Potência"));
        }

        return produto;
    }
}
