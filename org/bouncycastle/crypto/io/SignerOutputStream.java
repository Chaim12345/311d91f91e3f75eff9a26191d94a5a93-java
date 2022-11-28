package org.bouncycastle.crypto.io;

import java.io.OutputStream;
import org.bouncycastle.crypto.Signer;
/* loaded from: classes3.dex */
public class SignerOutputStream extends OutputStream {

    /* renamed from: a  reason: collision with root package name */
    protected Signer f13434a;

    public SignerOutputStream(Signer signer) {
        this.f13434a = signer;
    }

    public Signer getSigner() {
        return this.f13434a;
    }

    @Override // java.io.OutputStream
    public void write(int i2) {
        this.f13434a.update((byte) i2);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        this.f13434a.update(bArr, i2, i3);
    }
}
