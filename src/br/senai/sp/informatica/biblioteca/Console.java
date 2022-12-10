package br.senai.sp.informatica.biblioteca;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class Console {
    private static final Scanner console = new Scanner(System.in);
    private static final NumberFormat formatador = NumberFormat.getNumberInstance();

    private static Number readNumber(String mensagem) {
        System.out.print(mensagem + ": ");
        var posicao = new ParsePosition(0);
        var resultado = console.nextLine();
        var numero = formatador.parse(resultado, posicao);

        if (posicao.getErrorIndex() >= 0 || posicao.getIndex() != resultado.length() || numero == null) {
            throw new InputMismatchException("Número Inválido");
        }

        return numero;
    }

    public static int readInt(String mensagem) {
        return readNumber(mensagem).intValue();
    }

    public static long readLong(String mensagem) {
        return readNumber(mensagem).longValue();
    }

    public static double readDouble(String mensagem) {
        return readNumber(mensagem).doubleValue();
    }

    public static String readString(String mensagem) {
        System.out.print(mensagem + ": ");
        return console.nextLine();
    }

    public static LocalDate readDate(String mensagem) {
        System.out.print(mensagem + ": ");
        return LocalDate.parse(console.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    private static void print(boolean newLine, String formato, Object... args) {
        System.out.format(formato + (newLine ? "\n" : "" ), args);
    }

    public static void print(String formato, Object... args) {
        print(false, formato, args);
    }

    public static void println(String formato, Object... args) {
        print(true, formato, args);
    }

    public static void print(Object... args) {
        print(false, "%s", args);
    }

    public static void println(Object... args) {
        print(true, "%s", args);
    }
}
