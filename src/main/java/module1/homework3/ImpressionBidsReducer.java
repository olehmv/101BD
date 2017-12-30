package module1.homework3;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * Counts number if bids per city
 * @author Oleh
 *
 */
public class ImpressionBidsReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

	private final LongWritable outValue = new LongWritable();
	
	@Override
	protected void reduce(Text key, Iterable<LongWritable> values,
			Reducer<Text, LongWritable, Text, LongWritable>.Context context)
			throws IOException, InterruptedException {
		long amount = 0;
		for (@SuppressWarnings("unused") LongWritable bid : values) {
			amount++;
		}
		outValue.set(amount);
		context.write(key, outValue);

	}

}
