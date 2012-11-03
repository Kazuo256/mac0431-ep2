package ep2;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.ObjectWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

/**
 * <p>
 * Classe que reduz várias medidas de um mesmo personagem respectivo DPS dele.
 * </p>
 */
public class Reduce extends MapReduceBase implements
		Reducer<Text, ObjectWritable, Text, DoubleWritable> {

	// DPS do personagem
	private DoubleWritable dps = new DoubleWritable(0.0);

	@Override
	public void reduce(Text name, Iterator<ObjectWritable> measures,
			OutputCollector<Text, DoubleWritable> output, Reporter reporter)
			throws IOException {
		Medida measure = new Medida();
		while (measures.hasNext())
			measure.juntaMedidas((Medida) measures.next().get());
		dps.set(measure.calculaDPS());
		output.collect(name, dps);
	}

}
