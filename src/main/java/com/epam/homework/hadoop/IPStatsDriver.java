package com.epam.homework.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.SnappyCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.log4j.BasicConfigurator;


public class IPStatsDriver extends Configured implements Tool {


    public static void main(String[] args) throws Exception {
    	BasicConfigurator.configure();
        IPStatsDriver jobDriver = new IPStatsDriver();
        jobDriver.run(args);
    }

    @Override
    public int run(String[] strings) throws Exception {
        Configuration conf = new Configuration();
        conf.set(TextOutputFormat.SEPERATOR, ",");
        Job job = Job.getInstance(conf, "IP stats");
        job.setJarByClass(IPStatsDriver.class);
        job.setMapperClass(IPStatsMapper.class);
        job.setReducerClass(IPStatsReducer.class);
        job.setCombinerClass(IPStatsCombiner.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(WritablePair.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IPStats.class);
        FileInputFormat.addInputPath(job, new Path(strings[0]));
        if (strings.length == 3 && strings[2].trim().equals("compress")) {
            FileOutputFormat.setOutputPath(job, new Path(strings[1]));
            FileOutputFormat.setOutputCompressorClass(job, SnappyCodec.class);
            SequenceFileOutputFormat.setCompressOutput(job, true);
            SequenceFileOutputFormat.setOutputCompressionType(job, SequenceFile.CompressionType.BLOCK);
            conf.set("mapred.output.compression.codec", "org.apache.hadoop.io.compress.SnappyCodec");
        } else {
            TextOutputFormat.setOutputPath(job, new Path(strings[1]));
        }
        return job.waitForCompletion(true) ? 0 : 1;
    }
}
