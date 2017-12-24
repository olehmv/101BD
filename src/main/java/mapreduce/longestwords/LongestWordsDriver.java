package mapreduce.longestwords;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class LongestWordsDriver {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();		
		Job job = Job.getInstance(conf, "Longest Words");
		job.setJarByClass(mapreduce.longestwords.LongestWordsDriver.class);
		job.setMapperClass(mapreduce.longestwords.LongestWordsMapper.class);
		job.setReducerClass(mapreduce.longestwords.LongestWordsReducer.class);
		job.setMapOutputKeyClass(CustomKey.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(CustomKey.class);
		job.setOutputValueClass(Text.class);
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		if (!job.waitForCompletion(true))
			return;
	}

}
