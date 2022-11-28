package com.fasterxml.jackson.core.io;

import java.io.DataOutput;
import java.io.OutputStream;
/* loaded from: classes.dex */
public class DataOutputAsStream extends OutputStream {

    /* renamed from: a  reason: collision with root package name */
    protected final DataOutput f5149a;

    public DataOutputAsStream(DataOutput dataOutput) {
        this.f5149a = dataOutput;
    }

    @Override // java.io.OutputStream
    public void write(int i2) {
        this.f5149a.write(i2);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        this.f5149a.write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        this.f5149a.write(bArr, i2, i3);
    }
}
