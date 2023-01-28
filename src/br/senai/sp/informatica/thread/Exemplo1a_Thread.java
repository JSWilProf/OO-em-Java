package br.senai.sp.informatica.thread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exemplo1a_Thread extends JFrame  implements ActionListener {
	private final JButton btIniciar = new JButton("Iniciar");
	private final JButton btParar = new JButton("Parar");
	private final JTextField tfNum = new JTextField(5);
	private boolean executa = true;

	public Exemplo1a_Thread() {
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p1.add(tfNum);
		add(p1, BorderLayout.CENTER);
		
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		p2.add(btIniciar);
		p2.add(btParar);
		add(p2, BorderLayout.SOUTH);
	
		tfNum.setEditable(false);
		btIniciar.addActionListener(this);
		btParar.addActionListener(this);
		btParar.setEnabled(false);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		Object botao = ev.getSource();
		
		if(botao.equals(btIniciar)) {
			incrementaNumeros();
			btIniciar.setEnabled(false);
			btParar.setEnabled(true);
		} else {
			executa = false;
			btIniciar.setEnabled(true);
			btParar.setEnabled(false);
		}
	}
	
	public void incrementaNumeros() {
		int i = 0;
		while (executa) {
			tfNum.setText(String.format("%05d", i++));
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame tela = new Exemplo1a_Thread();
			tela.setTitle("Contador");
			tela.pack();
			tela.setLocationRelativeTo(null);
			tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			tela.setVisible(true);
		});
	}
}