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
 * <p>
 * Classe que mapeia medidas de dano dos personagens a partir dos relatórios de
 * combate gerados pelo jogo.
 * </p>
 */
public class Map extends MapReduceBase implements
		Mapper<Object, Text, Text, ObjectWritable> {

	// Medida de dano devolvida
	private ObjectWritable measure = new ObjectWritable();
	// Nome do personagem associado a uma medida de dano
	private Text name = new Text();
	// Conjunto de personagens
	private Set<String> characters = new HashSet<String>();
	// Analisador léxico dos relatórios gerados
	private Parser parser = new Parser();
	// Gerador de medidas de dano
	private DamageMeasurer dps = null;

	@Override
	public void map(Object key, Text value,
			OutputCollector<Text, ObjectWritable> output, Reporter reporter)
			throws IOException {
		StringTokenizer lines = new StringTokenizer(value.toString(), "\n\r");
		while (lines.hasMoreTokens()) {
			Damage dmg = parser.parseDamage(lines.nextToken());
			if (dmg == null)
				continue;
			if (dps == null)
				dps = new DamageMeasurer(dmg.getTime());
			characters.add(dmg.getSource());
			if (dmg.getAmount() >= 0)
				dps.addDamage(dmg.getSource(), dmg.getTime(), dmg.getAmount());
			else
				dps.ressurrectCharacter(dmg.getSource(), dmg.getTime());
		}
		if (dps != null)
			for (String character : characters) {
				name.set(character);
				measure.set(dps.generateMeasure(character));
				output.collect(name, measure);
			}
	}
}
