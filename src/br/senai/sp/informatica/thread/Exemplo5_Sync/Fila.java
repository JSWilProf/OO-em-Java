package br.senai.sp.informatica.thread.Exemplo5_Sync;

import java.util.*;
import java.util.concurrent.*;

@SuppressWarnings("ALL")
public class Fila {
	public static void main(String[] args) {
		Queue<Produto> catalogo = new PriorityBlockingQueue<>(100, Comparator.comparing(Produto::getPrioridade));
		List<Future<Produto>> retirada = new ArrayList<>();

		ExecutorService exec = Executors.newCachedThreadPool();
		while (true) {
			exec.execute(() -> catalogo.add(new Produto()));

			synchronized (retirada) {
				retirada.add(exec.submit(catalogo::poll));
			}

			new Thread(() -> {
				synchronized (retirada) {
					for (Future<Produto> tarefa : retirada) {
						new Thread(() -> {
							while (!tarefa.isDone()) ;
							try {
								System.out.println(tarefa.get());
							} catch (InterruptedException | ExecutionException ex) {
								ex.printStackTrace();
							}
						}).start();
					}
				}
			}).start();
		}
	}
}

enum TiposDePrioridade {
	URGENTE, NORMAL, BAIXA;
}

class Produto {
	private final String nome;
	private final TiposDePrioridade prioridade;
	private static int numReg = 1;
	private static final Random rand = new Random();

	public Produto() {
		this.nome = "Prod" + numReg++;
		int tipo = rand.nextInt(3);
		this.prioridade = tipo == 0 ? TiposDePrioridade.BAIXA
				: tipo == 1 ? TiposDePrioridade.NORMAL : TiposDePrioridade.URGENTE;
	}

	public TiposDePrioridade getPrioridade() {
		return prioridade;
	}

	@Override
	public String toString() {
		return nome + " -> " + prioridade;
	}
}