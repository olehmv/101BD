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
		int sum = 0;
		int count = 0;
		for (CountAverageTuple val : values) {
			sum += val.getCount() * val.getAverage();
			count += val.getCount();

		}
		result.setCount(count);
		result.setAverage(sum/count);
		context.write(key, result);
	}

}
