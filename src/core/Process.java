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
		while(contGeracao < 2000) { // critério de parada - número de gerações
			contGeracao++;
			for(Individuo i : populacao) {
				i.setProbSelecao(populacao);
			}
			System.out.println("\nGeração: " + contGeracao);
			listar();
			selecionarMaisAptos();
			crossover();
		}
		printResultado();
	}
	
	public static void printResultado() {
		System.out.println("\n---------- RESULTADO ----------");
		System.out.println(String.format("Cromossomo: %s - Aptidão (fitness): %d", getMaisApto().getCromossomo(), getMaisApto().getAptidao()));
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
	
	public static Individuo getMenosApto() {
		Individuo menosApto = populacao.get(0);
		for(Individuo i : populacao) {
			if(i.getAptidao() < menosApto.getAptidao()) {
				menosApto = i;
			}
		}
		return menosApto;
	}
	
	public static Individuo getMaisApto() {
		Individuo maisApto = populacao.get(0);
		for(Individuo i : populacao) {
			if(i.getAptidao() > maisApto.getAptidao()) {
				maisApto = i;
			}
		}
		return maisApto;
	}
	
	public static void crossover() {
		Individuo maisApto = populacao.get(populacao.size()-1);
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
			
			populacao.add(A1CrossA2);
			populacao.add(A2CrossA1);
			
			
			pontoDeCorte = gerarPontoDeCorte();
			A1 = populacaoClone.get(i);
			A2 = populacaoClone.get(i+1);
			subA1 = A1.getCromossomo().substring(pontoDeCorte, Individuo.TAM_INDIVIDUO);
			subA2 = A2.getCromossomo().substring(pontoDeCorte, Individuo.TAM_INDIVIDUO);
			
			A1CrossA2 = new Individuo(A1.getCromossomo().substring(0, pontoDeCorte) + subA2);
			A2CrossA1 = new Individuo(A2.getCromossomo().substring(0, pontoDeCorte) + subA1);
			
			A2CrossA1.mutacao(2);
			A2CrossA1.setAptidao();
			
			populacao.add(A1CrossA2);
			populacao.add(A2CrossA1);
			
			// elitismo - o mais apto da população anterior vai para a próxima população
			Individuo menosApto = getMenosApto();
			if(menosApto.getAptidao() < maisApto.getAptidao()) {
				populacao.remove(menosApto);
				populacao.add(maisApto);
			}
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
