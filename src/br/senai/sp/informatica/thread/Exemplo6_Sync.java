package br.senai.sp.informatica.thread;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("ALL")
public class Exemplo6_Sync extends JFrame {
	private final JLabel lbDaConta = new JLabel("");
	private final JLabel lbParaConta = new JLabel("");
	private final JLabel lbMontante = new JLabel("");
	private final JLabel lbNumTransacoes = new JLabel("");
	private final JLabel lbTotal = new JLabel("");
	private final ExecutorService exec = Executors.newCachedThreadPool();
	private final int[ ] contas = new int[10];
	private long numTransacoes = 0;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Exemplo6_Sync::new);
	}
	public Exemplo6_Sync() {
		JPanel p0 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		p0.add(new JLabel("Transferência da conta:"));
		p0.add(lbDaConta);
		p0.add(new JLabel("para:"));
		p0.add(lbParaConta);
		p0.add(new JLabel("R$"));
		p0.add(lbMontante);
		add(p0, BorderLayout.NORTH);

		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		p1.add(new JLabel("Transações:"));
		p1.add(lbNumTransacoes);
		p1.add(new JLabel("Soma R$"));
		p1.add(lbTotal);
		add(p1, BorderLayout.CENTER);

		JButton fim = new JButton("Parar");
		fim.addActionListener((ev) -> System.exit(0));

		JPanel p2 = new JPanel();
		p2.add(fim);
		add(p2, BorderLayout.SOUTH);

		setTitle("Novo Banco");
		setPreferredSize(new Dimension(320, 130));
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		abreOBanco();
	}
	public void abreOBanco() {
		Arrays.fill(contas, 10000);

		for (int i = 0; i < contas.length; i++) {
			int numConta = i;
			exec.execute(() -> {
				try {
					while (!exec.isShutdown()) {
						int paraConta = (int) (contas.length * Math.random());
						int montante = (int) (10000 * Math.random());

						transfere(numConta, paraConta, montante);

						lbDaConta.setText(String.valueOf(numConta));
						lbParaConta.setText(String.valueOf(paraConta));
						lbMontante.setText(String.format("%,.2f", (double)montante));

						Thread.sleep(2);
					}
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			});
		}
	}
	public void transfere(int de, int para, int montante) {
		if (contas[de] < montante)
			return;

		synchronized (contas) {
			contas[de] -= montante;
			contas[para] += montante;
		}

		if (++numTransacoes % 10000 == 0) {
			int somaTotal = 0;

			for (int i = 0; i < contas.length; i++)
				somaTotal += contas[i];

			lbNumTransacoes.setText(String.valueOf(numTransacoes));
			lbTotal.setText(String.format("%,.2f", (double)somaTotal));
		}
	}
}