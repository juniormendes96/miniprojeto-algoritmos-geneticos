package core;

import java.util.ArrayList;
import java.util.List;

import model.Individuo;

public class Process {
	
	public static final int TAM_POPULACAO = 4;
	public static List<Individuo> populacao = new ArrayList<>();
	
	public static void iniciar() {
		criarPopulacao();
		for(Individuo i : populacao) {
			System.out.println(i.getAptidao());
		}
	}
	
	public static void criarPopulacao() {
		for(int i=0; i<TAM_POPULACAO; i++) {
			populacao.add(new Individuo());
		}
	}

}
