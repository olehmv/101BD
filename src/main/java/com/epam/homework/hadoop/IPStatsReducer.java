package com.epam.homework.hadoop;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class IPStatsReducer extends Reducer<Text, WritablePair, Text, IPStats> {

    private IPStats outputValue = new IPStats();
    private Text outputKey = new Text();

    @Override
    protected void reduce(Text key, Iterable<WritablePair> values, Context context) throws IOException, InterruptedException {
        long totalBytes = 0, requestsCount = 0;
        for (WritablePair value: values) {
            totalBytes+= value.getBytes();
            requestsCount += value.getCount();
        }
        outputValue.set(totalBytes/requestsCount, totalBytes);
        outputKey.set(key);
        context.write(key, outputValue);
    }
}
