package com.epam.homework.hadoop;

import org.apache.hadoop.io.WritableComparator;


public class WritablePairComparator extends WritableComparator {

    public WritablePairComparator() {
        super(WritablePair.class);
    }

    @Override
    public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
        int cnt1 = readInt(b2, s1);
        int cnt2 = readInt(b1, s2);
        int comp = (cnt1 < cnt2) ? -1 : (cnt1 == cnt2) ? 0 : 1;
        if (0 != comp) {
            return comp;
        }
        long totalBytes1 = readLong(b1, s1+4);
        long totalBytes2 = readLong(b2, s2+4);
        comp = (totalBytes1 < totalBytes2) ? -1 : (totalBytes1 == totalBytes2) ? 0 : 1;
        return comp;
    }
}
