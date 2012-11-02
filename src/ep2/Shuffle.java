/**
 * 
 */
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
 * 
 *
 */
public class Shuffle extends MapReduceBase implements
		Reducer<Text, ObjectWritable, Text, ObjectWritable> {

	@Override
	public void reduce(Text key, Iterator<ObjectWritable> values,
			OutputCollector<Text, ObjectWritable> output, Reporter reporter)
			throws IOException {
		Medida sum = new Medida();
		while (values.hasNext()) {
			sum.juntaMedidas((Medida) values.next().get());
		}
		output.collect(key, new ObjectWritable(sum));
	}

}
