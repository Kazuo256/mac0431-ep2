package ep2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class Medida implements Writable{
	private long dano;
	private long tempo;
	
	public long getDano() {
		return dano;
	}

	public void setDano(Long dano) {
		this.dano = dano;
	}

	public long getTempo() {
		return tempo;
	}

	public void setTempo(long tempo) {
		this.tempo = tempo;
	}

	public void juntaMedidas(Medida arg) {
		this.dano += arg.getDano();
		this.tempo += arg.getTempo();
	}
	
	public double calculaDPS() {
		if(this.tempo == 0)
			return 0.0;
		return this.dano/this.tempo;
	}
	
	@Override
	public void readFields(DataInput arg0) throws IOException {
		dano = arg0.readLong();
		tempo = arg0.readLong();
		
	}

	@Override
	public void write(DataOutput arg0) throws IOException {
		arg0.writeLong(this.dano);
		arg0.writeLong(this.tempo);
	}

}
