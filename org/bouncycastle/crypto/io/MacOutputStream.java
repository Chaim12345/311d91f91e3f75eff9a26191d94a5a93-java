package org.bouncycastle.crypto.io;

import java.io.OutputStream;
import org.bouncycastle.crypto.Mac;
/* loaded from: classes3.dex */
public class MacOutputStream extends OutputStream {

    /* renamed from: a  reason: collision with root package name */
    protected Mac f13432a;

    public MacOutputStream(Mac mac) {
        this.f13432a = mac;
    }

    public byte[] getMac() {
        byte[] bArr = new byte[this.f13432a.getMacSize()];
        this.f13432a.doFinal(bArr, 0);
        return bArr;
    }

    @Override // java.io.OutputStream
    public void write(int i2) {
        this.f13432a.update((byte) i2);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        this.f13432a.update(bArr, i2, i3);
    }
}
