package org.bouncycastle.tls.crypto.impl.bc;

import org.bouncycastle.math.ec.rfc7748.X25519;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class BcX25519 implements TlsAgreement {

    /* renamed from: a  reason: collision with root package name */
    protected final BcTlsCrypto f14988a;

    /* renamed from: b  reason: collision with root package name */
    protected final byte[] f14989b = new byte[32];

    /* renamed from: c  reason: collision with root package name */
    protected final byte[] f14990c = new byte[32];

    public BcX25519(BcTlsCrypto bcTlsCrypto) {
        this.f14988a = bcTlsCrypto;
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public TlsSecret calculateSecret() {
        try {
            byte[] bArr = new byte[32];
            if (X25519.calculateAgreement(this.f14989b, 0, this.f14990c, 0, bArr, 0)) {
                return this.f14988a.a(bArr);
            }
            throw new TlsFatalAlert((short) 40);
        } finally {
            Arrays.fill(this.f14989b, (byte) 0);
            Arrays.fill(this.f14990c, (byte) 0);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public byte[] generateEphemeral() {
        this.f14988a.getSecureRandom().nextBytes(this.f14989b);
        byte[] bArr = new byte[32];
        X25519.scalarMultBase(this.f14989b, 0, bArr, 0);
        return bArr;
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public void receivePeerValue(byte[] bArr) {
        if (bArr == null || bArr.length != 32) {
            throw new TlsFatalAlert((short) 47);
        }
        System.arraycopy(bArr, 0, this.f14990c, 0, 32);
    }
}
