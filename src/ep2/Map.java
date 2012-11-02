/**
 * 
 */
package ep2;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.hadoop.io.ObjectWritable;
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
		Mapper<Object, Text, Text, ObjectWritable> {

	private ObjectWritable number = new ObjectWritable(0.0);
	private Text name = new Text();
	private Set<String> characters = new HashSet<String>();
	private Parser parser = new Parser();

	@Override
	public void map(Object key, Text value,
			OutputCollector<Text, ObjectWritable> output, Reporter reporter)
			throws IOException {
		StringTokenizer lines = new StringTokenizer(value.toString(), "\n\r");
		Parser parser = new Parser();
		DamagePerSecond dps = null;
		while (lines.hasMoreTokens()) {
			Damage dmg = parser.parseDamage(lines.nextToken());
			if (dmg == null)
				continue;
			if (dps == null)
				dps = new DamagePerSecond(dmg.getTime());
			characters.add(dmg.getSource());
			dps.adicionaDano(dmg.getSource(), dmg.getAmount(), dmg.getTime());
		}
		if (dps != null)
			for (String character : characters) {
				name.set(character);
				number.set(dps.geraMedida(character));
				output.collect(name, number);
			}
	}

}
