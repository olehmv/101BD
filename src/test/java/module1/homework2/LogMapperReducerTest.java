package module1.homework2;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

import module1.homework1.CustomKey;

public class LogMapperReducerTest {
	MapDriver<LongWritable, Text, Text, CountAverageTuple> mapDriver;
	ReduceDriver<Text, CountAverageTuple, Text, CountAverageTuple> reduceDriver;
	MapReduceDriver<LongWritable, Text,Text,CountAverageTuple, Text, CountAverageTuple> mapReduceDriver;
	private String logLine="ip1 - - [24/Apr/2011:04:06:01 -0400] \"GET /~strabal/grease/photo9/927-3.jpg HTTP/1.1\" 200 40028 \"-\" \"Mozilla/5.0 (compatible; YandexImages/3.0; +http://yandex.com/bots)\"\n" + 
			"";
	private ApacheLog log=LogRexExp.parseApacheLog(logLine);
	private CountAverageTuple tuple=new CountAverageTuple(1,Float.parseFloat(log.getBytesSent()));

	@Before
	public void setUp() throws Exception {
		LogMapper mapper = new LogMapper();
		mapDriver = MapDriver.newMapDriver(mapper);
		LogReducer reducer = new LogReducer();
		reduceDriver = ReduceDriver.newReduceDriver(reducer);
		mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
		countAverageTuple=LogRexExp.parseApacheLog(logLine);
	}

	@Test
	public void logMapperTest() throws IOException {
		mapDriver.withInput(new LongWritable(), new Text(logLine));
		mapDriver.withOutput(new Text("ip1"), new Text());
		mapDriver.runTest();
	}

	@Test
	public void longestWordsReducerTest() throws IOException {
		ArrayList<Text> values = new ArrayList<>();
		values.add(new Text("Hi"));
		values.add(new Text("Hello"));
		reduceDriver.withInput(new CustomKey(3), values);
		reduceDriver.withOutput(new CustomKey(3), new Text("Hi\tHello\t"));
		reduceDriver.runTest();
	}

	@Test
	public void mapReduceTest() throws IOException {
		mapReduceDriver.withInput(new LongWritable(), new Text("Hi, Hello"));
		mapReduceDriver.addOutput(new CustomKey(5), new Text("Hello\t"));
		mapReduceDriver.addOutput(new CustomKey(2), new Text("Hi\t"));
		mapReduceDriver.runTest();
	}

}
