import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.GenericOptionsParser;

import ep2.Map;
import ep2.Reduce;
import ep2.Shuffle;

/**
 * <p>
 * Classe principal do programa, responsável pela função main().
 * </p>
 * <p>
 * Os argumentos da linha de comando são o arquivo ou diretório de entrada e o
 * diretório de saída.
 * </p>
 */
public class WoWDPSMeter {

	/**	<p>Função principal do programa.</p>
	 * 
	 * @param args argumentos da linha de comando.
	 * @throws Exception jogada pelo Hadoop em caso de erro.
	 */
	public static void main(String[] args) throws Exception {
		JobConf conf = new JobConf(WoWDPSMeter.class);
		String[] otherArgs = new GenericOptionsParser(conf, args)
				.getRemainingArgs();
		
		conf.setJobName("dpsmeter");

		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(ObjectWritable.class);

		conf.setMapperClass(Map.class);
		conf.setCombinerClass(Shuffle.class);
		conf.setReducerClass(Reduce.class);

		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);

		FileInputFormat.setInputPaths(conf, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(conf, new Path(otherArgs[1]));

		JobClient.runJob(conf);
	}

}
