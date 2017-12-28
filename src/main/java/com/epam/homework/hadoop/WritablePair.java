package com.epam.homework.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class WritablePair implements WritableComparable<WritablePair> {

    private long bytes;
    private int count;

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(bytes);
        dataOutput.writeInt(count);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        bytes = dataInput.readLong();
        count = dataInput.readInt();
    }

    public WritablePair() {
    }

    public WritablePair(long bytes, int count) {
        this.bytes = bytes;
        this.count = count;
    }

    public void set(long bytes, int count) {
        this.bytes = bytes;
        this.count = count;
    }

    public long getBytes() {
        return bytes;
    }

    public void setBytes(long bytes) {
        this.bytes = bytes;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WritablePair that = (WritablePair) o;

        if (bytes != that.bytes) return false;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        int result = (int) (bytes ^ (bytes >>> 32));
        result = 31 * result + count;
        return result;
    }


    @Override
    public int compareTo(WritablePair o) {
        return Long.compare(bytes, o.bytes);
    }
}
