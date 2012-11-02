/**
 * 
 */
package ep2;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

/**
 * 
 * 
 */
public class Map extends MapReduceBase implements
		Mapper<Object, Text, Text, DoubleWritable> {

	private final static DoubleWritable one = new DoubleWritable(1);
	private Text word = new Text();

	@Override
	public void map(Object key, Text value,
			OutputCollector<Text, DoubleWritable> output, Reporter reporter)
			throws IOException {
		StringTokenizer lines = new StringTokenizer(value.toString(), "\n\r");
		Parser parser = new Parser();
		DamagePerSecond dps = null;
		while (lines.hasMoreTokens()) {
			Damage dmg = parser.parseDamage(lines.nextToken());
			if (dmg == null) continue;
			if (dps == null)
				dps = new DamagePerSecond(dmg.getTime());
			dps.adicionaDano(dmg.getSource(), dmg.getAmount(), dmg.getTime());
		}
		word.set("");
		output.collect(word, new DoubleWritable(dps.geraEstatistica("")));
	}

}
