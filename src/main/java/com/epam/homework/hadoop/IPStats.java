package com.epam.homework.hadoop;


import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class IPStats implements WritableComparable<IPStats> {

    private float avgBytes;
    private long totalBytes;


    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(totalBytes);
        dataOutput.writeFloat(avgBytes);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        totalBytes = dataInput.readLong();
        avgBytes = dataInput.readFloat();
    }

    @Override
    public String toString() {
        return String.format("%.3f", avgBytes) + "," + totalBytes;
    }

    public IPStats() {
    }

    public IPStats(float avgBytes, long totalBytes) {
        this.avgBytes = avgBytes;
        this.totalBytes = totalBytes;
    }

    public void set(float avgBytes, long totalBytes) {
        this.avgBytes = avgBytes;
        this.totalBytes = totalBytes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IPStats ipStats = (IPStats) o;

        if (Float.compare(ipStats.avgBytes, avgBytes) != 0) return false;
        return totalBytes == ipStats.totalBytes;
    }

    @Override
    public int hashCode() {
        int result = (avgBytes != +0.0f ? Float.floatToIntBits(avgBytes) : 0);
        result = 31 * result + (int) (totalBytes ^ (totalBytes >>> 32));
        return result;
    }

    @Override
    public int compareTo(IPStats o) {
        return Float.compare(avgBytes, o.avgBytes);
    }
}
