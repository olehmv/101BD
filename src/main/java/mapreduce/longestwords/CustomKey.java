package mapreduce.longestwords;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;

public class CustomKey implements WritableComparable<CustomKey> {
	private int keyy; 

	public CustomKey(int k) {
		keyy=k;
	}

	public CustomKey() {

	}

	@Override
	public void readFields(DataInput in) throws IOException {
		set(in.readInt());
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(keyy);
	}

	@Override
	public int compareTo(CustomKey k) {
		if (k.get() > keyy) {
			return 1;
		}
		if (k.get() < keyy) {
			return -1;
		}
		return 0;

	}

	public int get() {
		return keyy;
	}

	public void set(int k) {
		keyy=k;
	}

	public String toString() {

		return Integer.toString(keyy);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + keyy;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomKey other = (CustomKey) obj;
		if (keyy != other.keyy)
			return false;
		return true;
	}
	

	

}
