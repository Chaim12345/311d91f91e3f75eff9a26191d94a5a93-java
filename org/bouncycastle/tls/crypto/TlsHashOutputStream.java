package org.bouncycastle.tls.crypto;

import java.io.OutputStream;
/* loaded from: classes4.dex */
public class TlsHashOutputStream extends OutputStream {

    /* renamed from: a  reason: collision with root package name */
    protected TlsHash f14921a;

    public TlsHashOutputStream(TlsHash tlsHash) {
        this.f14921a = tlsHash;
    }

    @Override // java.io.OutputStream
    public void write(int i2) {
        this.f14921a.update(new byte[]{(byte) i2}, 0, 1);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        this.f14921a.update(bArr, i2, i3);
    }
}
