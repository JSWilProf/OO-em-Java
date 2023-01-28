package br.senai.sp.informatica.thread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MinhaThread extends Thread {
	private final JTextField tfNum;
	MinhaThread(JTextField tfNum) {
		this.tfNum = tfNum;
	}

	public void run() {
		int i = 0;
		while (!isInterrupted()) {
			tfNum.setText(String.format("%05d", i++));
		}
	}
}

public class Exemplo1c_Thread extends JFrame implements ActionListener {
	private final JButton btIniciar = new JButton("Iniciar");
	private final JButton btParar = new JButton("Parar");
	private final JTextField tfNum = new JTextField(5);
	private Thread executa;

	public Exemplo1c_Thread() {
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
			executa = new MinhaThread(tfNum);
			executa.start();
			btIniciar.setEnabled(false);
			btParar.setEnabled(true);
		} else {
			executa.interrupt();
			tfNum.setText("");
			btIniciar.setEnabled(true);
			btParar.setEnabled(false);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame tela = new Exemplo1c_Thread();
			tela.setTitle("Contador");
			tela.pack();
			tela.setLocationRelativeTo(null);
			tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			tela.setVisible(true);
		});
	}
}

