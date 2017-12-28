package com.epam.homework.hadoop;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.bitwalker.useragentutils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IPStatsMapper extends Mapper<Object, Text, Text, WritablePair> {

    private Text outputKey = new Text();
    private WritablePair outputValue = new WritablePair();

    private static final Logger LOGGER = LoggerFactory.getLogger(IPStatsMapper.class);
    private static final String logEntryPattern = "(ip\\d+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        Pattern p = Pattern.compile(logEntryPattern);
        Matcher matcher = p.matcher(value.toString());
        if (!matcher.matches()) {
            LOGGER.error("Unable to parse {}, skipping", value.toString());
            return;
        }
        String ip = matcher.group(1);
        int bytes = Integer.valueOf(matcher.group(7));
        String agent = matcher.group(9);
        outputKey.set(ip);
        outputValue.set(bytes, 1);
        context.write(outputKey, outputValue);
        //Incrementing user agent browser counter
        try {
            UserAgent userAgent = new UserAgent(agent);
            context.getCounter("Browsers", userAgent.getBrowser().getName()).increment(1);
        } catch (Exception ex) {
            LOGGER.error("Unable to parse user agent", ex);
            context.getCounter("Browsers", "Unknown");
        }
    }


}
