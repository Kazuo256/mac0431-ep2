package usp.mac0431.ep2;

import java.util.HashMap;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

public class DamagePerSecond {
	HashMap<String, SummaryStatistics> dps = new HashMap<String, SummaryStatistics>();
	HashMap<String, Long> tempo = new HashMap<String, Long>();

	public void adicionaDano(String personagem, long dano, long tempo) {
		dps.get(personagem).addValue(dano);
		this.tempo.put(personagem, this.tempo.get(personagem) + tempo);
	}

	public void revivePersonagem(String personagem, long tempo) {
		this.tempo.put(personagem, this.tempo.get(personagem)
				- (tempo - this.tempo.get(personagem)));
	}

	public double geraEstatistica(String personagem) {
		return dps.get(personagem).getSum() / tempo.get(personagem);
	}
}
