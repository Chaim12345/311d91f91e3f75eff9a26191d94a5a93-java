package org.bouncycastle.tls.crypto;

import java.io.OutputStream;
/* loaded from: classes4.dex */
public class TlsMACOutputStream extends OutputStream {

    /* renamed from: a  reason: collision with root package name */
    protected TlsMAC f14922a;

    public TlsMACOutputStream(TlsMAC tlsMAC) {
        this.f14922a = tlsMAC;
    }

    @Override // java.io.OutputStream
    public void write(int i2) {
        this.f14922a.update(new byte[]{(byte) i2}, 0, 1);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        this.f14922a.update(bArr, i2, i3);
    }
}
