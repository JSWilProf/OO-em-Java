package br.senai.sp.informatica.thread;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class Exemplo4_Ball extends JFrame {
    private final JPanel painel = new JPanel();
    private final JPanel botoes = new JPanel();
    private final JButton btIniciar = new JButton("Iniciar");
    private final JButton btParar = new JButton("Parar");
    private ExecutorService exec;

    public Exemplo4_Ball() {
        painel.setPreferredSize(new Dimension(300, 200));
        botoes.add(btIniciar);
        botoes.add(btParar);
        btParar.setEnabled(false);
        add(painel, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);

        btIniciar.addActionListener((ev) -> {
            if (exec == null)
                exec = Executors.newCachedThreadPool();

            exec.execute(new SimpleBall(exec, painel));

            if (!btParar.isEnabled())
                btParar.setEnabled(true);
        });

        btParar.addActionListener((ev) -> {
            exec.shutdown();
            try {
                exec.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException ex) {
            }
            repaint();
            exec = null;
            btParar.setEnabled(false);
        });

        setTitle("Pool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Exemplo4_Ball::new);
    }
}

@SuppressWarnings("ALL")
class SimpleBall extends Ball implements Runnable {
    private ExecutorService exec;
    public SimpleBall(ExecutorService exec, Container b) {
        super(b);
        this.exec = exec;
    }

    @Override
    public void run() {
        try {
            while (!exec.isShutdown()) {
                move();
                Thread.sleep(15);
            }
        } catch (InterruptedException ignored) {
        }
    }
}
