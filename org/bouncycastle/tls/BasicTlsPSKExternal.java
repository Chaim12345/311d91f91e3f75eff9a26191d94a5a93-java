package org.bouncycastle.tls;

import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class BasicTlsPSKExternal implements TlsPSKExternal {

    /* renamed from: a  reason: collision with root package name */
    protected final byte[] f14688a;

    /* renamed from: b  reason: collision with root package name */
    protected final TlsSecret f14689b;

    /* renamed from: c  reason: collision with root package name */
    protected final int f14690c;

    public BasicTlsPSKExternal(byte[] bArr, TlsSecret tlsSecret) {
        this(bArr, tlsSecret, 4);
    }

    public BasicTlsPSKExternal(byte[] bArr, TlsSecret tlsSecret, int i2) {
        this.f14688a = Arrays.clone(bArr);
        this.f14689b = tlsSecret;
        this.f14690c = i2;
    }

    @Override // org.bouncycastle.tls.TlsPSK
    public byte[] getIdentity() {
        return this.f14688a;
    }

    @Override // org.bouncycastle.tls.TlsPSK
    public TlsSecret getKey() {
        return this.f14689b;
    }

    @Override // org.bouncycastle.tls.TlsPSK
    public int getPRFAlgorithm() {
        return this.f14690c;
    }
}
