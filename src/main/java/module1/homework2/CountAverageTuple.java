package module1.homework2;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class CountAverageTuple implements Writable{
	private float count;
	private float average;
	
	@Override
	public void readFields(DataInput in) throws IOException {
		count=in.readFloat();
		average=in.readFloat();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeFloat(count);
		out.writeFloat(average);
	}

	public float getCount() {
		return count;
	}

	public void setCount(float count) {
		this.count = count;
	}

	public float getAverage() {
		return average;
	}

	public void setAverage(float average) {
		this.average = average;
	}

	@Override
	public String toString() {
		 return count + "\t" + average;
	}
	

	
}
