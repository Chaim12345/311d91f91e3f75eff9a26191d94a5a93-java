package org.bouncycastle.crypto.io;

import java.io.FilterInputStream;
import java.io.InputStream;
import org.bouncycastle.crypto.Signer;
/* loaded from: classes3.dex */
public class SignerInputStream extends FilterInputStream {

    /* renamed from: a  reason: collision with root package name */
    protected Signer f13433a;

    public SignerInputStream(InputStream inputStream, Signer signer) {
        super(inputStream);
        this.f13433a = signer;
    }

    public Signer getSigner() {
        return this.f13433a;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        int read = ((FilterInputStream) this).in.read();
        if (read >= 0) {
            this.f13433a.update((byte) read);
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        int read = ((FilterInputStream) this).in.read(bArr, i2, i3);
        if (read > 0) {
            this.f13433a.update(bArr, i2, read);
        }
        return read;
    }
}
