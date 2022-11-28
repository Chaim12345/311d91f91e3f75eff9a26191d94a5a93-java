package org.bouncycastle.crypto.io;

import java.io.FilterInputStream;
import java.io.InputStream;
import org.bouncycastle.crypto.Mac;
/* loaded from: classes3.dex */
public class MacInputStream extends FilterInputStream {

    /* renamed from: a  reason: collision with root package name */
    protected Mac f13431a;

    public MacInputStream(InputStream inputStream, Mac mac) {
        super(inputStream);
        this.f13431a = mac;
    }

    public Mac getMac() {
        return this.f13431a;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        int read = ((FilterInputStream) this).in.read();
        if (read >= 0) {
            this.f13431a.update((byte) read);
        }
        return read;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        int read = ((FilterInputStream) this).in.read(bArr, i2, i3);
        if (read >= 0) {
            this.f13431a.update(bArr, i2, read);
        }
        return read;
    }
}
