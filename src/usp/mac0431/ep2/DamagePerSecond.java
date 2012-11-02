package usp.mac0431.ep2;

import java.util.HashMap;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

public class DamagePerSecond {
	HashMap<String, SummaryStatistics> dps = new HashMap<String, SummaryStatistics>();
	HashMap<String, Long> tempoVivo = new HashMap<String, Long>();
	HashMap<String, Boolean> estaVivo = new HashMap<String, Boolean>();
	
	public void adicionaDano (String personagem, long dano, long tempo) {
		if (estaVivo.get(personagem) == true) {
			dps.get(personagem).addValue(dano);
			tempoVivo.put(personagem, tempoVivo.get(personagem) + tempo);
		}
	}
	
	public void mataPersonagem (String personagem, long tempo) {
		estaVivo.put(personagem, false);
		tempoVivo.put(personagem, tempoVivo.get(personagem) + tempo);
	}
	
	public void revivePersonagem (String personagem, long tempo) {
		estaVivo.put(personagem, true);
		tempoVivo.put(personagem, tempoVivo.get(personagem) + tempo);
	}
	
	public double geraEstatistica (String personagem) {
		return dps.get(personagem).getSum() / tempoVivo.get(personagem);
	}
}
