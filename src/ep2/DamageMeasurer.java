package ep2;

import java.util.HashMap;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

/**
 * <p>
 * Classe que recebe informações de dano dos personagens e gera medidas desses
 * danos.
 * </p>
 */
public class DamageMeasurer {

	private HashMap<String, SummaryStatistics> damages = new HashMap<String, SummaryStatistics>();
	private HashMap<String, Long> lastDmgTime = new HashMap<String, Long>();
	private HashMap<String, Long> combatTime = new HashMap<String, Long>();
	private Long initialTime;

	public DamageMeasurer(Long initialTime) {
		this.initialTime = initialTime;
	}

	public void addDamage(String source, long time, long amount) {
		this.getTotalDmg(source).addValue(amount);
		long timeSinceLast = time - this.getLastDmgTime(source);
		this.lastDmgTime.put(source, time);
		this.combatTime.put(source, this.getCombatTime(source) + timeSinceLast);
	}

	public void ressurrectCharacter(String character, long time) {
		long timeSinceLast = time - this.getLastDmgTime(character);
		this.combatTime.put(character, this.getCombatTime(character)
				- (timeSinceLast - this.getCombatTime(character)));
	}

	public Medida generateMeasure(String character) {
		Medida measure = new Medida();
		measure.setDano((long) damages.get(character).getSum());
		measure.setTempo(combatTime.get(character));
		return measure;
	}

	private SummaryStatistics getTotalDmg(String character) {
		if (!this.damages.containsKey(character)) {
			this.damages.put(character, new SummaryStatistics());
		}
		return this.damages.get(character);
	}

	private Long getLastDmgTime(String character) {
		if (!this.lastDmgTime.containsKey(character)) {
			this.lastDmgTime.put(character, new Long(this.initialTime));
		}
		return this.lastDmgTime.get(character);
	}

	private Long getCombatTime(String character) {
		if (!this.combatTime.containsKey(character)) {
			this.combatTime.put(character, new Long(0L));
		}
		return this.combatTime.get(character);
	}
}
