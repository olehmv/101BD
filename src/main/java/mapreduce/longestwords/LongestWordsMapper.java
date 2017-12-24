package mapreduce.longestwords;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import static mapreduce.longestwords.StringUtils.breakLineToWords;
/**
 * Map word length to word
 * @author oleh
 *
 */
public class LongestWordsMapper extends Mapper<LongWritable, Text, CustomKey, Text> {
	private CustomKey wordLength;
	private Text word;

	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, CustomKey, Text>.Context context)
			throws IOException, InterruptedException {
		String textLine = value.toString();
		String[] wordsArr = breakLineToWords(textLine);
		for (String w : wordsArr) {
			wordLength = new CustomKey();
			wordLength.set(w.length());
			word = new Text();
			word.set(w);
			context.write(wordLength, word);
		}

	}
}
