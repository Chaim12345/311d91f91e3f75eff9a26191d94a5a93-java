package org.bouncycastle.tls;

import java.io.OutputStream;
/* loaded from: classes4.dex */
class NullOutputStream extends OutputStream {

    /* renamed from: a  reason: collision with root package name */
    static final NullOutputStream f14767a = new NullOutputStream();

    private NullOutputStream() {
    }

    @Override // java.io.OutputStream
    public void write(int i2) {
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
    }
}
