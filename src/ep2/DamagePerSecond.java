package ep2;

import java.util.HashMap;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

public class DamagePerSecond {

	private HashMap<String, SummaryStatistics> dps = new HashMap<String, SummaryStatistics>();
	private HashMap<String, Long> tempoNoJogo = new HashMap<String, Long>();
	private HashMap<String, Long> tempoReal = new HashMap<String, Long>();
	private Long tempoInicial;

	public DamagePerSecond(Long tempoInicial) {
		this.tempoInicial = tempoInicial;
	}

	public void adicionaDano(String personagem, long dano, long tempo) {
		this.getDps(personagem).addValue(dano);
		long tempoReal = tempo - this.getTempoNoJogo(personagem);
		this.tempoNoJogo.put(personagem, tempo);
		this.tempoReal.put(personagem, this.getTempoReal(personagem)
				+ tempoReal);
	}

	public void revivePersonagem(String personagem, long tempo) {
		long tempoReal = tempo - this.getTempoNoJogo(personagem);
		this.tempoReal.put(personagem, this.getTempoReal(personagem)
				- (tempoReal - this.getTempoReal(personagem)));
	}

	public Medida geraMedida(String personagem) {
		Medida medida = new Medida();
		medida.setDano((long) dps.get(personagem).getSum());
		medida.setTempo(tempoReal.get(personagem));
		return medida;
	}

	private SummaryStatistics getDps(String personagem) {
		if (!this.dps.containsKey(personagem)) {
			this.dps.put(personagem, new SummaryStatistics());
		}
		return this.dps.get(personagem);
	}

	private Long getTempoNoJogo(String personagem) {
		if (!this.tempoNoJogo.containsKey(personagem)) {
			this.tempoNoJogo.put(personagem, new Long(this.tempoInicial));
		}
		return this.tempoNoJogo.get(personagem);
	}

	private Long getTempoReal(String personagem) {
		if (!this.tempoReal.containsKey(personagem)) {
			this.tempoReal.put(personagem, new Long(0L));
		}
		return this.tempoReal.get(personagem);
	}
}
