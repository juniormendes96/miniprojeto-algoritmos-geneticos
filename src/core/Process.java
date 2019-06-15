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
		while(contGeracao < 100) {
			contGeracao++;
			for(Individuo i : populacao) {
				i.setProbSelecao(populacao);
			}
			System.out.println("\nGeração: " + contGeracao);
			listar();
			selecionarMaisAptos();
			crossover();
		}
	}
	
	public static void listar() {
		for(Individuo i : populacao) {
			System.out.println(String.format("Cromossomo: %s - Aptidão: %d - Prob. seleção: %s", i.getCromossomo(), i.getAptidao(), String.format("%.2f", i.getProbSelecao())));
		}
	}
	
	public static void criarPopulacao() {
		for(int i=0; i<TAM_POPULACAO; i++) {
			populacao.add(new Individuo());
		}
	}
	
	public static void selecionarMaisAptos() {
		Collections.sort(populacao); // ordena a lista dos menos prováveis a ser selecionados aos mais prováveis
		populacao.subList(0, TAM_POPULACAO/2).clear(); // remove os menos prováveis
	}
	
	public static void crossover() {
		List<Individuo> populacaoClone = new ArrayList<>(populacao);
		populacao.clear();
		int pontoDeCorte;
		for(int i=0; i<TAM_POPULACAO/2; i+=2) {
			pontoDeCorte = gerarPontoDeCorte();
			String A1 = populacaoClone.get(i).getCromossomo();
			String A2 = populacaoClone.get(i+1).getCromossomo();
			String subA1 = A1.substring(pontoDeCorte, Individuo.TAM_INDIVIDUO);
			String subA2 = A2.substring(pontoDeCorte, Individuo.TAM_INDIVIDUO);
			
			Individuo i1 = new Individuo(populacaoClone.get(i).getCromossomo().substring(0, pontoDeCorte) + subA2);
			Individuo i2 = new Individuo(populacaoClone.get(i+1).getCromossomo().substring(0, pontoDeCorte) + subA1);
			
			i1.mutacao(1);
			i2.mutacao(2);
			
			populacao.add(i1);
			populacao.add(i2);
		}
		for(int i=0; i<TAM_POPULACAO/2; i+=2) {
			pontoDeCorte = gerarPontoDeCorte();
			String A2 = populacaoClone.get(i+1).getCromossomo();
			String A1 = populacaoClone.get(i).getCromossomo();
			String subA2 = A2.substring(pontoDeCorte, Individuo.TAM_INDIVIDUO);
			String subA1 = A1.substring(pontoDeCorte, Individuo.TAM_INDIVIDUO);
			
			Individuo i1 = new Individuo(populacaoClone.get(i+1).getCromossomo().substring(0, pontoDeCorte) + subA2);
			Individuo i2 = new Individuo(populacaoClone.get(i).getCromossomo().substring(0, pontoDeCorte) + subA1);
			
			i1.mutacao(2);
			i2.mutacao(1);
			
			populacao.add(i1);
			populacao.add(i2);
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
