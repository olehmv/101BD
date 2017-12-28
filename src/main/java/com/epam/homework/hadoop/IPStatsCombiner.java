package com.epam.homework.hadoop;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class IPStatsCombiner extends Reducer<Text, WritablePair, Text, WritablePair> {

    private WritablePair output = new WritablePair();

    @Override
    protected void reduce(Text key, Iterable<WritablePair> values, Context context) throws IOException, InterruptedException {
        int partialSum = 0, partialCount = 0;
        for (WritablePair value: values) {
            partialCount += value.getCount();
            partialSum += value.getBytes();
        }
        output.set(partialSum, partialCount);
        context.write(key, output);
    }
}
