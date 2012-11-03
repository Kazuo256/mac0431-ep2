package ep2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

/**
 * <p>
 * Classe que representa uma medida de desempenho de dano de um personagem.
 * </p>
 * <p>
 * É possível unir várias medidas através do método <code>joinMeasures</code>.
 * </p>
 */
public class Measure implements Writable {
	private long damage;
	private long time;

	public long getDamage() {
		return damage;
	}

	public void setDamage(Long damage) {
		this.damage = damage;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public void joinMeasures(Measure other) {
		this.damage += other.getDamage();
		this.time += other.getTime();
	}

	public double calculateDPS() {
		if (this.time == 0)
			return 0.0;
		return this.damage / this.time;
	}

	@Override
	public void readFields(DataInput input) throws IOException {
		damage = input.readLong();
		time = input.readLong();
	}

	@Override
	public void write(DataOutput output) throws IOException {
		output.writeLong(this.damage);
		output.writeLong(this.time);
	}

}
