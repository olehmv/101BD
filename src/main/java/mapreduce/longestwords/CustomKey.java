package mapreduce.longestwords;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;

public class CustomKey implements WritableComparable<CustomKey> {
	private int key;

	public CustomKey(int k) {
		key = k;
	}

	public CustomKey() {

	}

	@Override
	public void readFields(DataInput in) throws IOException {
		key = in.readInt();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(key);
	}

	@Override
	public int compareTo(CustomKey k) {
		if (k.get() > key) {
			return 1;
		}
		if (k.get() < key) {
			return -1;
		}
		return 0;

	}

	public int get() {
		return key;
	}

	public void set(int k) {
		key = k;
	}
	

}
