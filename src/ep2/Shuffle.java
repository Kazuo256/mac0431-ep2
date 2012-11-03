package ep2;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.ObjectWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

/**
 * <p>
 * Classe que junta várias medidas de um mesmo personagem em uma única medida.
 * </p>
 */
public class Shuffle extends MapReduceBase implements
		Reducer<Text, ObjectWritable, Text, ObjectWritable> {

	// Medida de dano unificada
	private ObjectWritable reducedMeasure = new ObjectWritable();

	@Override
	public void reduce(Text name, Iterator<ObjectWritable> measures,
			OutputCollector<Text, ObjectWritable> output, Reporter reporter)
			throws IOException {
		Measure measure = new Measure();
		while (measures.hasNext()) {
			measure.joinMeasures((Measure) measures.next().get());
		}
		reducedMeasure.set(measure);
		output.collect(name, reducedMeasure);
	}

}
