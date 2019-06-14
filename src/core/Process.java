package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Individuo;

public class Process {
	
	public static final int TAM_POPULACAO = 4;
	public static List<Individuo> populacao = new ArrayList<>();
	
	public static void iniciar() {
		criarPopulacao();
		System.out.println(calcularFitness(populacao.get(0)));
	}
	
	public static void criarPopulacao() {
		for(int i=0; i<TAM_POPULACAO; i++) {
			populacao.add(new Individuo());
		}
	}
	
	public static int calcularFitness(Individuo individuo) {
		Map<String, Integer> binarios = new HashMap<String, Integer>();
		for(int i=0; i<Individuo.TAM_INDIVIDUO; i++) {
			binarios.put("b" + (i+1), Character.getNumericValue(individuo.getCromossomo().charAt(i)));
		}
		return 9 + (binarios.get("b2") * binarios.get("b5")) - (binarios.get("b23") * binarios.get("b14")) 
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

}
