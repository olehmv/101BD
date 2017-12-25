package module1.homework2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.log4j.BasicConfigurator;

public class LogDriver {

	public static void main(String[] args) throws Exception {
		BasicConfigurator.configure();
		Configuration conf = new Configuration();
		conf.set("mapreduce.output.textoutputformat.separator", ",");
		Job job = Job.getInstance(conf, "JobName");
		job.setJarByClass(module1.homework2.LogDriver.class);
		job.setMapperClass(module1.homework2.LogMapper.class);
		job.setCombinerClass(module1.homework2.LogReducer.class);
		job.setReducerClass(module1.homework2.LogReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(CountAverageTuple.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(CountAverageTuple.class);
		TextInputFormat.setInputPaths(job, new Path(args[0]));
		TextOutputFormat.setOutputPath(job, new Path(args[1]));

		if (!job.waitForCompletion(true))
			return;
	}

}