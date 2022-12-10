package br.senai.sp.informatica.produtos;

import lombok.Data;

@Data
public class Eletrodomestico extends Produto {
    private String voltagem;
    private String potencia;

    @Override
    public String toString() {
        return String.format("%s Voltagem: %s Potência: %s",
            super.toString(), voltagem, potencia);
    }
}
