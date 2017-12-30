package module1.homework3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 
 * @author Maps City ID to bid price greater then threshold 250
 *
 */
public class ImpressionBidsMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

	private final int threshold = 250;
	private final Text outKey = new Text();
	private final LongWritable outValue = new LongWritable();
	protected static final Map<String, String> cities = new HashMap<>();

	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		URI[] cacheFiles = context.getCacheFiles();
		Path path = new Path(cacheFiles[0]);
		BufferedReader reader = null;
		String line = "";
		FileSystem fs = FileSystem.get(context.getConfiguration());
		reader = new BufferedReader(new InputStreamReader(fs.open(path)));
		while ((line = reader.readLine()) != null) {
			String tokens[];
			if(line.contains("unknown")) {
				tokens = line.split(" ");
			}else {				
				tokens = line.split("\t");
			}
			cities.put(tokens[0].trim(), tokens[1].trim());
		}

	}

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String[] tokens = value.toString().split("\t");
		int cityID;
		int bidPrice;
		try {
			cityID = Integer.valueOf(tokens[6]);
			bidPrice = Integer.valueOf(tokens[18]);
		} catch (NumberFormatException ex) {
			return;
		}

		if (bidPrice > threshold) {
			String id = String.valueOf(cityID);
			if(cities.containsKey(id)) {
				outKey.set(cities.get(id));
				outValue.set(cityID);
				context.write(outKey, outValue);
			}
		}
	}
}