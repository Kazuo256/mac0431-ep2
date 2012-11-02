import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

import ep2.Map;
import ep2.Reduce;


public class WoWDPSMeter {


	public static void main(String[] args) throws Exception {
		JobConf conf = new JobConf(WoWDPSMeter.class);
		conf.setJobName("dpsmeter");

		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(ObjectWritable.class);

		conf.setMapperClass(Map.class);
		conf.setCombinerClass(Reduce.class);
		conf.setReducerClass(Reduce.class);

		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);

		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));

		JobClient.runJob(conf);
	}

}
