package module1.homework2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import static module1.homework2.LogRexExp.parseApacheLog;

/**
 * Maps ip address to CountAverageTuple(ONE,Bytes Sent), counts and filters zero
 * bytes sent log line, writes out zero bytes counter
 * 
 * @author Oleh
 * @param LongWritable
 *            input key -> line byte offset
 * @param Text
 *            input value -> content of the line
 * @param Text
 *            output key -> ip address
 * @param CountAverageTuple
 *            output value -> tuple with count 1 and bytes sent from log line
 */
public class LogMapper extends Mapper<LongWritable, Text, Text, CountAverageTuple> {
	private Text ipAdress;
	private IntWritable bytesSent;
	private CountAverageTuple outCountAverage = new CountAverageTuple();
	private final int ONE = 1;

	public void map(LongWritable ikey, Text ivalue, Context context) throws IOException, InterruptedException {
		try {
			ApacheLog apacheLog = parseApacheLog(ivalue.toString());
			ipAdress = new Text(apacheLog.getIdAddress());
			bytesSent = new IntWritable(Integer.parseInt(apacheLog.getBytesSent()));
		} catch (IllegalArgumentException e) {
			context.getCounter("Zero Bytes", "count").increment(1);
		}
		outCountAverage.setCount(ONE);
		outCountAverage.setAverage(bytesSent.get());
		context.write(ipAdress, outCountAverage);
	}

}
