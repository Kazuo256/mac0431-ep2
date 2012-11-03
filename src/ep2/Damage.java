package ep2;

/**
 * <p>
 * Classe que representa um dano causado por alguém em um dado momento.
 * </p>
 */
public class Damage {

	private String source;
	private long time;
	private long amount;

	Damage(String source, long time, long amount) {
		this.source = source;
		this.time = time;
		this.amount = amount;
	}

	/**
	 * @return o nome da origem do dano
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @return o momento em que o dano foi causado
	 */
	public long getTime() {
		return time;
	}

	/**
	 * @return a quantidade de dano causada
	 */
	public long getAmount() {
		return amount;
	}

}
