package org.bouncycastle.tls.crypto.impl.bc;

import org.bouncycastle.math.ec.rfc7748.X448;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class BcX448 implements TlsAgreement {

    /* renamed from: a  reason: collision with root package name */
    protected final BcTlsCrypto f14992a;

    /* renamed from: b  reason: collision with root package name */
    protected final byte[] f14993b = new byte[56];

    /* renamed from: c  reason: collision with root package name */
    protected final byte[] f14994c = new byte[56];

    public BcX448(BcTlsCrypto bcTlsCrypto) {
        this.f14992a = bcTlsCrypto;
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public TlsSecret calculateSecret() {
        try {
            byte[] bArr = new byte[56];
            if (X448.calculateAgreement(this.f14993b, 0, this.f14994c, 0, bArr, 0)) {
                return this.f14992a.a(bArr);
            }
            throw new TlsFatalAlert((short) 40);
        } finally {
            Arrays.fill(this.f14993b, (byte) 0);
            Arrays.fill(this.f14994c, (byte) 0);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public byte[] generateEphemeral() {
        this.f14992a.getSecureRandom().nextBytes(this.f14993b);
        byte[] bArr = new byte[56];
        X448.scalarMultBase(this.f14993b, 0, bArr, 0);
        return bArr;
    }

    @Override // org.bouncycastle.tls.crypto.TlsAgreement
    public void receivePeerValue(byte[] bArr) {
        if (bArr == null || bArr.length != 56) {
            throw new TlsFatalAlert((short) 47);
        }
        System.arraycopy(bArr, 0, this.f14994c, 0, 56);
    }
}
