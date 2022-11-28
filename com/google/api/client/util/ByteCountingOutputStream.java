package com.google.api.client.util;

import java.io.OutputStream;
/* loaded from: classes2.dex */
final class ByteCountingOutputStream extends OutputStream {

    /* renamed from: a  reason: collision with root package name */
    long f8076a;

    @Override // java.io.OutputStream
    public void write(int i2) {
        this.f8076a++;
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        this.f8076a += i3;
    }
}
