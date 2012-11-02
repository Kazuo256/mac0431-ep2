/**
 * 
 */
package usp.mac0431.ep2;

/** <p> Represents damage dealt.</p>
 *
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
	 * @return the source of the damage
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @return the time when the damage was dealt
	 */
	public long getTime() {
		return time;
	}

	/**
	 * @return the amount of damage dealt
	 */
	public long getAmount() {
		return amount;
	}
	
}
