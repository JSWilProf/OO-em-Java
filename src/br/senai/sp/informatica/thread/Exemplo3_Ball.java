package br.senai.sp.informatica.thread;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("ALL")
public class Exemplo3_Ball extends JFrame {
    private final JPanel painel = new JPanel();
    private final JPanel botoes = new JPanel();
    private final JButton btIniciar = new JButton("Iniciar");
    private final JButton btParar = new JButton("Parar");
    private Ball ball;

    public Exemplo3_Ball() {
        painel.setPreferredSize(new Dimension(300, 200));
        botoes.add(btIniciar);
        botoes.add(btParar);
        btParar.setEnabled(false);
        add(painel, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);

        btIniciar.addActionListener((ev) -> {
            ball = new Ball(painel);
            ball.run();

            if (!btParar.isEnabled())
                btParar.setEnabled(true);
        });

        btParar.addActionListener((ev) -> {
            ball.fim();

            repaint();
            ball = null;
            btParar.setEnabled(false);
        });

        setTitle("Pool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Exemplo3_Ball::new);
    }
}

