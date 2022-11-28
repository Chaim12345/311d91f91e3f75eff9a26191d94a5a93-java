package org.bouncycastle.tls.crypto.impl;

import org.bouncycastle.tls.crypto.TlsEncryptor;
import org.bouncycastle.tls.crypto.TlsHMAC;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public abstract class AbstractTlsSecret implements TlsSecret {

    /* renamed from: a  reason: collision with root package name */
    protected byte[] f14924a;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractTlsSecret(byte[] bArr) {
        this.f14924a = bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static byte[] c(AbstractTlsSecret abstractTlsSecret) {
        return abstractTlsSecret.b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a() {
        if (this.f14924a == null) {
            throw new IllegalStateException("Secret has already been extracted or destroyed");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized byte[] b() {
        return Arrays.clone(this.f14924a);
    }

    @Override // org.bouncycastle.tls.crypto.TlsSecret
    public synchronized byte[] calculateHMAC(int i2, byte[] bArr, int i3, int i4) {
        TlsHMAC createHMACForHash;
        a();
        createHMACForHash = d().createHMACForHash(i2);
        byte[] bArr2 = this.f14924a;
        createHMACForHash.setKey(bArr2, 0, bArr2.length);
        createHMACForHash.update(bArr, i3, i4);
        return createHMACForHash.calculateMAC();
    }

    protected abstract AbstractTlsCrypto d();

    @Override // org.bouncycastle.tls.crypto.TlsSecret
    public synchronized void destroy() {
        byte[] bArr = this.f14924a;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
            this.f14924a = null;
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsSecret
    public synchronized byte[] encrypt(TlsEncryptor tlsEncryptor) {
        byte[] bArr;
        a();
        bArr = this.f14924a;
        return tlsEncryptor.encrypt(bArr, 0, bArr.length);
    }

    @Override // org.bouncycastle.tls.crypto.TlsSecret
    public synchronized byte[] extract() {
        byte[] bArr;
        a();
        bArr = this.f14924a;
        this.f14924a = null;
        return bArr;
    }

    @Override // org.bouncycastle.tls.crypto.TlsSecret
    public synchronized boolean isAlive() {
        return this.f14924a != null;
    }
}
