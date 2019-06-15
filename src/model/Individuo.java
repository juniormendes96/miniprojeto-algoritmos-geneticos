package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Individuo implements Comparable<Individuo>{
	
	private String cromossomo;
	private double probSelecao;
	private int aptidao;
	public static final int TAM_INDIVIDUO = 36;
	
	public Individuo() {
		Random r = new Random();
		cromossomo = "";
		for(int i=0; i<TAM_INDIVIDUO; i++) {
			cromossomo += r.nextInt(2) + 0; // gera 0 ou 1
		}
		this.setAptidao();
	}
	
	public Individuo(String cromossomo) {
		this.cromossomo = cromossomo;
		this.setAptidao();
	}

	public String getCromossomo() {
		return cromossomo;
	}

	public void setCromossomo(String cromossomo) {
		this.cromossomo = cromossomo;
	}

	public double getProbSelecao() {
		return probSelecao;
	}

	public void setProbSelecao(List<Individuo> populacao) {
		int somaAptidoes = populacao.stream().mapToInt(pop -> pop.getAptidao()).sum();
		this.probSelecao = ((double)this.getAptidao() / somaAptidoes) * 100;
	}
	
	public int getAptidao() {
		return aptidao;
	}

	protected void setAptidao() {
		Map<String, Integer> binarios = new HashMap<String, Integer>();
		for(int i=0; i<Individuo.TAM_INDIVIDUO; i++) {
			binarios.put("b" + (i+1), Character.getNumericValue(this.getCromossomo().charAt(i)));
		}
		this.aptidao = 9 + (binarios.get("b2") * binarios.get("b5")) - (binarios.get("b23") * binarios.get("b14")) 
				+ (binarios.get("b24") * binarios.get("b4")) - (binarios.get("b21") * binarios.get("b10")) 
				+ (binarios.get("b36") * binarios.get("b15")) - (binarios.get("b11") * binarios.get("b26")) 
				+ (binarios.get("b16") * binarios.get("b17")) + (binarios.get("b3") * binarios.get("b33")) 
				+ (binarios.get("b28") * binarios.get("b19")) + (binarios.get("b12") * binarios.get("b34")) 
				- (binarios.get("b31") * binarios.get("b32")) - (binarios.get("b22") * binarios.get("b25")) 
				+ (binarios.get("b35") * binarios.get("b27")) - (binarios.get("b29") * binarios.get("b7")) 
				+ (binarios.get("b8") * binarios.get("b13")) - (binarios.get("b6") * binarios.get("b9")) 
				+ (binarios.get("b18") * binarios.get("b20")) - (binarios.get("b1") * binarios.get("b30")) 
				+ (binarios.get("b23") * binarios.get("b4")) + (binarios.get("b21") * binarios.get("b15")) 
				+ (binarios.get("b26") * binarios.get("b16")) + (binarios.get("b31") * binarios.get("b12")) 
				+ (binarios.get("b25") * binarios.get("b19")) + (binarios.get("b7") * binarios.get("b8")) 
				+ (binarios.get("b9") * binarios.get("b18")) + (binarios.get("b1") * binarios.get("b33"));
	}
	
	public void mutacao(int qtBits) {
		Random r = new Random();
		int maximo = Individuo.TAM_INDIVIDUO - 1;
		int minimo = 0;
		int range = maximo - minimo + 1;
		StringBuilder sb = new StringBuilder(this.getCromossomo());
		for(int i=0; i<qtBits; i++) {
			int index =  r.nextInt(range) + minimo;
			sb.setCharAt(index, this.getCromossomo().charAt(index) == '0' ? '1' : '0');
		}
		this.setCromossomo(sb.toString());
	}

	@Override
	public int compareTo(Individuo o) {
		if (this.getProbSelecao() < o.getProbSelecao()) {
            return -1;
        }
        if (this.getProbSelecao() > o.getProbSelecao()) {
            return 1;
        }
        return 0;
	}
	
}
