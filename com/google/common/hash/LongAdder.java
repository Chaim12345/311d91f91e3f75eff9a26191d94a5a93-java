package com.google.common.hash;

import com.google.common.hash.Striped64;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/* loaded from: classes2.dex */
final class LongAdder extends Striped64 implements LongAddable {
    private static final long serialVersionUID = 7249069246863182397L;

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        this.f9256c = 0;
        this.f9254a = null;
        this.f9255b = objectInputStream.readLong();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeLong(sum());
    }

    @Override // com.google.common.hash.LongAddable
    public void add(long j2) {
        int length;
        Striped64.Cell cell;
        Striped64.Cell[] cellArr = this.f9254a;
        if (cellArr == null) {
            long j3 = this.f9255b;
            if (b(j3, j3 + j2)) {
                return;
            }
        }
        int[] iArr = (int[]) Striped64.f9251d.get();
        boolean z = true;
        if (iArr != null && cellArr != null && (length = cellArr.length) >= 1 && (cell = cellArr[(length - 1) & iArr[0]]) != null) {
            long j4 = cell.f9257a;
            z = cell.a(j4, j4 + j2);
            if (z) {
                return;
            }
        }
        f(j2, iArr, z);
    }

    @Override // com.google.common.hash.Striped64
    final long d(long j2, long j3) {
        return j2 + j3;
    }

    public void decrement() {
        add(-1L);
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return sum();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return (float) sum();
    }

    @Override // com.google.common.hash.LongAddable
    public void increment() {
        add(1L);
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) sum();
    }

    @Override // java.lang.Number
    public long longValue() {
        return sum();
    }

    public void reset() {
        e(0L);
    }

    @Override // com.google.common.hash.LongAddable
    public long sum() {
        long j2 = this.f9255b;
        Striped64.Cell[] cellArr = this.f9254a;
        if (cellArr != null) {
            for (Striped64.Cell cell : cellArr) {
                if (cell != null) {
                    j2 += cell.f9257a;
                }
            }
        }
        return j2;
    }

    public long sumThenReset() {
        long j2 = this.f9255b;
        Striped64.Cell[] cellArr = this.f9254a;
        this.f9255b = 0L;
        if (cellArr != null) {
            for (Striped64.Cell cell : cellArr) {
                if (cell != null) {
                    j2 += cell.f9257a;
                    cell.f9257a = 0L;
                }
            }
        }
        return j2;
    }

    public String toString() {
        return Long.toString(sum());
    }
}
