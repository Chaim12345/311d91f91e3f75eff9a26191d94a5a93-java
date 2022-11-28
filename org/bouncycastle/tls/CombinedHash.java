package org.bouncycastle.tls;

import org.bouncycastle.tls.crypto.TlsCrypto;
import org.bouncycastle.tls.crypto.TlsHash;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class CombinedHash implements TlsHash {

    /* renamed from: a  reason: collision with root package name */
    protected TlsContext f14712a;

    /* renamed from: b  reason: collision with root package name */
    protected TlsCrypto f14713b;

    /* renamed from: c  reason: collision with root package name */
    protected TlsHash f14714c;

    /* renamed from: d  reason: collision with root package name */
    protected TlsHash f14715d;

    public CombinedHash(CombinedHash combinedHash) {
        this.f14712a = combinedHash.f14712a;
        this.f14713b = combinedHash.f14713b;
        this.f14714c = combinedHash.f14714c.cloneHash();
        this.f14715d = combinedHash.f14715d.cloneHash();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CombinedHash(TlsContext tlsContext, TlsHash tlsHash, TlsHash tlsHash2) {
        this.f14712a = tlsContext;
        this.f14713b = tlsContext.getCrypto();
        this.f14714c = tlsHash;
        this.f14715d = tlsHash2;
    }

    public CombinedHash(TlsCrypto tlsCrypto) {
        this.f14713b = tlsCrypto;
        this.f14714c = tlsCrypto.createHash(1);
        this.f14715d = tlsCrypto.createHash(2);
    }

    @Override // org.bouncycastle.tls.crypto.TlsHash
    public byte[] calculateHash() {
        TlsContext tlsContext = this.f14712a;
        if (tlsContext != null && TlsUtils.isSSL(tlsContext)) {
            SSL3Utils.b(this.f14712a, this.f14714c, this.f14715d);
        }
        return Arrays.concatenate(this.f14714c.calculateHash(), this.f14715d.calculateHash());
    }

    @Override // org.bouncycastle.tls.crypto.TlsHash
    public TlsHash cloneHash() {
        return new CombinedHash(this);
    }

    @Override // org.bouncycastle.tls.crypto.TlsHash
    public void reset() {
        this.f14714c.reset();
        this.f14715d.reset();
    }

    @Override // org.bouncycastle.tls.crypto.TlsHash
    public void update(byte[] bArr, int i2, int i3) {
        this.f14714c.update(bArr, i2, i3);
        this.f14715d.update(bArr, i2, i3);
    }
}
