package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.Individuo;

public class Process {
	
	public static final int TAM_POPULACAO = 4;
	public static List<Individuo> populacao = new ArrayList<>();
	
	public static void iniciar() {
		int contGeracao = 0;
		criarPopulacao();
		while(contGeracao < 300) {
			contGeracao++;
			for(Individuo i : populacao) {
				i.setProbSelecao(populacao);
			}
			System.out.println("\nGera��o: " + contGeracao);
			listar();
			selecionarMaisAptos();
			crossover();
		}
	}

	public static void listar() {
		for(Individuo i : populacao) {
			System.out.println(String.format("Cromossomo: %s - Aptid�o: %d - Prob. sele��o: %s", i.getCromossomo(), i.getAptidao(), String.format("%.2f", i.getProbSelecao())));
		}
	}
	
	public static void criarPopulacao() {
		for(int i=0; i<TAM_POPULACAO; i++) {
			populacao.add(new Individuo());
		}
	}
	
	public static void selecionarMaisAptos() {
		Collections.sort(populacao); // ordena a lista dos menos prov�veis a ser selecionados aos mais prov�veis
		populacao.subList(0, TAM_POPULACAO/2).clear(); // remove os menos prov�veis
	}
	
	public static void crossover() {
		List<Individuo> populacaoClone = new ArrayList<>(populacao);
		populacao.clear();
		int pontoDeCorte;
		for(int i=0; i<TAM_POPULACAO/2; i+=2) {
			pontoDeCorte = gerarPontoDeCorte();
			Individuo A1 = populacaoClone.get(i);
			Individuo A2 = populacaoClone.get(i+1);
			String subA1 = A1.getCromossomo().substring(pontoDeCorte, Individuo.TAM_INDIVIDUO);
			String subA2 = A2.getCromossomo().substring(pontoDeCorte, Individuo.TAM_INDIVIDUO);
			
			Individuo A1CrossA2 = new Individuo(A1.getCromossomo().substring(0, pontoDeCorte) + subA2);
			Individuo A2CrossA1 = new Individuo(A2.getCromossomo().substring(0, pontoDeCorte) + subA1);
			
			A2CrossA1.mutacao(1);
			A2CrossA1.setAptidao();
			
			// Elitismo
			populacao.add(A1CrossA2.getAptidao() > A1.getAptidao() ? A1CrossA2 : A1);
			populacao.add(A2CrossA1.getAptidao() > A2.getAptidao() ? A2CrossA1 : A2);
			
			
			pontoDeCorte = gerarPontoDeCorte();
			A1 = populacaoClone.get(i);
			A2 = populacaoClone.get(i+1);
			subA1 = A1.getCromossomo().substring(pontoDeCorte, Individuo.TAM_INDIVIDUO);
			subA2 = A2.getCromossomo().substring(pontoDeCorte, Individuo.TAM_INDIVIDUO);
			
			A1CrossA2 = new Individuo(A1.getCromossomo().substring(0, pontoDeCorte) + subA2);
			A2CrossA1 = new Individuo(A2.getCromossomo().substring(0, pontoDeCorte) + subA1);
			
			A2CrossA1.mutacao(2);
			A2CrossA1.setAptidao();
			
			// Elitismo
			populacao.add(A1CrossA2.getAptidao() > A1.getAptidao() ? A1CrossA2 : A1);
			populacao.add(A2CrossA1.getAptidao() > A2.getAptidao() ? A2CrossA1 : A2);
		}
	}
	
	public static int gerarPontoDeCorte() {
		Random r = new Random();
		int maximo = Individuo.TAM_INDIVIDUO - 2;
		int minimo = 2;
		int range = maximo - minimo + 1;
		return r.nextInt(range) + minimo;
	}

}
