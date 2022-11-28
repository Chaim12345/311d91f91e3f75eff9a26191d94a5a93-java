package com.google.android.gms.common.data;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;
@KeepForSdk
/* loaded from: classes.dex */
public class DataBufferIterator<T> implements Iterator<T> {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    protected final DataBuffer f5728a;

    /* renamed from: b  reason: collision with root package name */
    protected int f5729b = -1;

    public DataBufferIterator(@NonNull DataBuffer<T> dataBuffer) {
        this.f5728a = (DataBuffer) Preconditions.checkNotNull(dataBuffer);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f5729b < this.f5728a.getCount() + (-1);
    }

    @Override // java.util.Iterator
    @NonNull
    public T next() {
        if (hasNext()) {
            DataBuffer dataBuffer = this.f5728a;
            int i2 = this.f5729b + 1;
            this.f5729b = i2;
            return (T) dataBuffer.get(i2);
        }
        int i3 = this.f5729b;
        StringBuilder sb = new StringBuilder(46);
        sb.append("Cannot advance the iterator beyond ");
        sb.append(i3);
        throw new NoSuchElementException(sb.toString());
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
