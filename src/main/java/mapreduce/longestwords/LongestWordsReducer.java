package mapreduce.longestwords;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class LongestWordsReducer extends Reducer<CustomKey, Text, CustomKey, Text> {
	private CustomKey wordLength;
	private Text words;
	private final String WORDSEPARATOR = "\t";
	@Override
	protected void reduce(CustomKey key, Iterable<Text> values,
			Reducer<CustomKey, Text, CustomKey, Text>.Context context) throws IOException, InterruptedException {
		wordLength=key;
		words = new Text();
		words.set(stringBuilder(values, WORDSEPARATOR));
		context.write(wordLength, words);
	}

	private String stringBuilder(Iterable<Text> values, String separator) {
		StringBuffer buffer=new StringBuffer();
		for (Text string : values) {
			buffer.append(string.toString()).append(separator);
		}
		return buffer.toString();

	}

}
