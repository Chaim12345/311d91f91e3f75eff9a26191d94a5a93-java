package org.bouncycastle.operator.bc;

import java.io.OutputStream;
import org.bouncycastle.crypto.Signer;
/* loaded from: classes4.dex */
public class BcSignerOutputStream extends OutputStream {
    private Signer sig;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BcSignerOutputStream(Signer signer) {
        this.sig = signer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] a() {
        return this.sig.generateSignature();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(byte[] bArr) {
        return this.sig.verifySignature(bArr);
    }

    @Override // java.io.OutputStream
    public void write(int i2) {
        this.sig.update((byte) i2);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        this.sig.update(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        this.sig.update(bArr, i2, i3);
    }
}
