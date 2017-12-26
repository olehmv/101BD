package module1.homework2;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class LogReducer extends Reducer<Text, CountAverageTuple, Text, CountAverageTuple> {
	private CountAverageTuple result = new CountAverageTuple();;

	@Override
	protected void reduce(Text key, Iterable<CountAverageTuple> values,
			Reducer<Text, CountAverageTuple, Text, CountAverageTuple>.Context context)
			throws IOException, InterruptedException {
		float sum = 0;
		float count = 0;
		for (CountAverageTuple val : values) {
			sum += val.getCount() * val.getAverage();
			count += val.getCount();

		}
//		context.getCounter("Filtered Http Status ", "Code 304").increment(1);
		result.setCount(sum);
		result.setAverage(sum/count);
		context.write(key, result);
	}

}
