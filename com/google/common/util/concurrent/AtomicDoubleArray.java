package com.google.common.util.concurrent;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.primitives.ImmutableLongArray;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLongArray;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okhttp3.HttpUrl;
import org.apache.http.message.TokenParser;
@GwtIncompatible
/* loaded from: classes2.dex */
public class AtomicDoubleArray implements Serializable {
    private static final long serialVersionUID = 0;
    private transient AtomicLongArray longs;

    public AtomicDoubleArray(int i2) {
        this.longs = new AtomicLongArray(i2);
    }

    public AtomicDoubleArray(double[] dArr) {
        int length = dArr.length;
        long[] jArr = new long[length];
        for (int i2 = 0; i2 < length; i2++) {
            jArr[i2] = Double.doubleToRawLongBits(dArr[i2]);
        }
        this.longs = new AtomicLongArray(jArr);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        ImmutableLongArray.Builder builder = ImmutableLongArray.builder();
        for (int i2 = 0; i2 < readInt; i2++) {
            builder.add(Double.doubleToRawLongBits(objectInputStream.readDouble()));
        }
        this.longs = new AtomicLongArray(builder.build().toArray());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        int length = length();
        objectOutputStream.writeInt(length);
        for (int i2 = 0; i2 < length; i2++) {
            objectOutputStream.writeDouble(get(i2));
        }
    }

    @CanIgnoreReturnValue
    public double addAndGet(int i2, double d2) {
        long j2;
        double longBitsToDouble;
        do {
            j2 = this.longs.get(i2);
            longBitsToDouble = Double.longBitsToDouble(j2) + d2;
        } while (!this.longs.compareAndSet(i2, j2, Double.doubleToRawLongBits(longBitsToDouble)));
        return longBitsToDouble;
    }

    public final boolean compareAndSet(int i2, double d2, double d3) {
        return this.longs.compareAndSet(i2, Double.doubleToRawLongBits(d2), Double.doubleToRawLongBits(d3));
    }

    public final double get(int i2) {
        return Double.longBitsToDouble(this.longs.get(i2));
    }

    @CanIgnoreReturnValue
    public final double getAndAdd(int i2, double d2) {
        long j2;
        double longBitsToDouble;
        do {
            j2 = this.longs.get(i2);
            longBitsToDouble = Double.longBitsToDouble(j2);
        } while (!this.longs.compareAndSet(i2, j2, Double.doubleToRawLongBits(longBitsToDouble + d2)));
        return longBitsToDouble;
    }

    public final double getAndSet(int i2, double d2) {
        return Double.longBitsToDouble(this.longs.getAndSet(i2, Double.doubleToRawLongBits(d2)));
    }

    public final void lazySet(int i2, double d2) {
        this.longs.lazySet(i2, Double.doubleToRawLongBits(d2));
    }

    public final int length() {
        return this.longs.length();
    }

    public final void set(int i2, double d2) {
        this.longs.set(i2, Double.doubleToRawLongBits(d2));
    }

    public String toString() {
        int length = length() - 1;
        if (length == -1) {
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        StringBuilder sb = new StringBuilder((length + 1) * 19);
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        int i2 = 0;
        while (true) {
            sb.append(Double.longBitsToDouble(this.longs.get(i2)));
            if (i2 == length) {
                sb.append(AbstractJsonLexerKt.END_LIST);
                return sb.toString();
            }
            sb.append(AbstractJsonLexerKt.COMMA);
            sb.append(TokenParser.SP);
            i2++;
        }
    }

    public final boolean weakCompareAndSet(int i2, double d2, double d3) {
        return this.longs.weakCompareAndSet(i2, Double.doubleToRawLongBits(d2), Double.doubleToRawLongBits(d3));
    }
}
