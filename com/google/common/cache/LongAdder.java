package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.cache.Striped64;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
final class LongAdder extends Striped64 implements LongAddable {
    private static final long serialVersionUID = 7249069246863182397L;

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        this.f8364c = 0;
        this.f8362a = null;
        this.f8363b = objectInputStream.readLong();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeLong(sum());
    }

    @Override // com.google.common.cache.LongAddable
    public void add(long j2) {
        int length;
        Striped64.Cell cell;
        Striped64.Cell[] cellArr = this.f8362a;
        if (cellArr == null) {
            long j3 = this.f8363b;
            if (b(j3, j3 + j2)) {
                return;
            }
        }
        int[] iArr = (int[]) Striped64.f8359d.get();
        boolean z = true;
        if (iArr != null && cellArr != null && (length = cellArr.length) >= 1 && (cell = cellArr[(length - 1) & iArr[0]]) != null) {
            long j4 = cell.f8365a;
            z = cell.a(j4, j4 + j2);
            if (z) {
                return;
            }
        }
        f(j2, iArr, z);
    }

    @Override // com.google.common.cache.Striped64
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

    @Override // com.google.common.cache.LongAddable
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

    @Override // com.google.common.cache.LongAddable
    public long sum() {
        long j2 = this.f8363b;
        Striped64.Cell[] cellArr = this.f8362a;
        if (cellArr != null) {
            for (Striped64.Cell cell : cellArr) {
                if (cell != null) {
                    j2 += cell.f8365a;
                }
            }
        }
        return j2;
    }

    public long sumThenReset() {
        long j2 = this.f8363b;
        Striped64.Cell[] cellArr = this.f8362a;
        this.f8363b = 0L;
        if (cellArr != null) {
            for (Striped64.Cell cell : cellArr) {
                if (cell != null) {
                    j2 += cell.f8365a;
                    cell.f8365a = 0L;
                }
            }
        }
        return j2;
    }

    public String toString() {
        return Long.toString(sum());
    }
}
