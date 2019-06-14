package model;

import java.util.Random;

public class Individuo {
	
	private String cromossomo;
	public static final Integer TAM_INDIVIDUO = 36;
	
	public Individuo() {
		Random r = new Random();
		cromossomo = "";
		for(int i=0; i<TAM_INDIVIDUO; i++) {
			cromossomo += r.nextInt(2) + 0;
		}
	}

	public String getCromossomo() {
		return cromossomo;
	}

	@Override
	public String toString() {
		return cromossomo;
	}

}
