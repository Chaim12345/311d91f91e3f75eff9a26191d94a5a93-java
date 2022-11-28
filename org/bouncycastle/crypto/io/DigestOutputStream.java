package org.bouncycastle.crypto.io;

import java.io.OutputStream;
import org.bouncycastle.crypto.Digest;
/* loaded from: classes3.dex */
public class DigestOutputStream extends OutputStream {

    /* renamed from: a  reason: collision with root package name */
    protected Digest f13430a;

    public DigestOutputStream(Digest digest) {
        this.f13430a = digest;
    }

    public byte[] getDigest() {
        byte[] bArr = new byte[this.f13430a.getDigestSize()];
        this.f13430a.doFinal(bArr, 0);
        return bArr;
    }

    @Override // java.io.OutputStream
    public void write(int i2) {
        this.f13430a.update((byte) i2);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        this.f13430a.update(bArr, i2, i3);
    }
}
