package org.bouncycastle.crypto.io;

import java.io.FilterInputStream;
import java.io.InputStream;
import org.bouncycastle.crypto.Digest;
/* loaded from: classes3.dex */
public class DigestInputStream extends FilterInputStream {

    /* renamed from: a  reason: collision with root package name */
    protected Digest f13429a;

    public DigestInputStream(InputStream inputStream, Digest digest) {
        super(inputStream);
        this.f13429a = digest;
    }

    public Digest getDigest() {
        return this.f13429a;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        int read = ((FilterInputStream) this).in.read();
        if (read >= 0) {
            this.f13429a.update((byte) read);
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        int read = ((FilterInputStream) this).in.read(bArr, i2, i3);
        if (read > 0) {
            this.f13429a.update(bArr, i2, read);
        }
        return read;
    }
}
