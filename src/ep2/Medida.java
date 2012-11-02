package ep2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class Medida implements Writable{
	private Long dano;
	private Long tempo;
	
	public Long getDano() {
		return dano;
	}

	public void setDano(Long dano) {
		this.dano = dano;
	}

	public Long getTempo() {
		return tempo;
	}

	public void setTempo(Long tempo) {
		this.tempo = tempo;
	}

	public void juntaMedidas(Medida arg) {
		this.dano += arg.getDano();
		this.tempo += arg.getTempo();
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
