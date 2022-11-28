package org.bouncycastle.tls.crypto.impl.jcajce;

import java.security.PublicKey;
import java.util.Objects;
import org.bouncycastle.tls.DigitallySigned;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.crypto.TlsStreamVerifier;
import org.bouncycastle.tls.crypto.TlsVerifier;
/* loaded from: classes4.dex */
public class JcaTlsEdDSAVerifier implements TlsVerifier {

    /* renamed from: a  reason: collision with root package name */
    protected final JcaTlsCrypto f15015a;

    /* renamed from: b  reason: collision with root package name */
    protected final PublicKey f15016b;

    /* renamed from: c  reason: collision with root package name */
    protected final short f15017c;

    /* renamed from: d  reason: collision with root package name */
    protected final String f15018d;

    public JcaTlsEdDSAVerifier(JcaTlsCrypto jcaTlsCrypto, PublicKey publicKey, short s2, String str) {
        Objects.requireNonNull(jcaTlsCrypto, "crypto");
        Objects.requireNonNull(publicKey, "publicKey");
        this.f15015a = jcaTlsCrypto;
        this.f15016b = publicKey;
        this.f15017c = s2;
        this.f15018d = str;
    }

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public TlsStreamVerifier getStreamVerifier(DigitallySigned digitallySigned) {
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        if (algorithm != null && algorithm.getSignature() == this.f15017c && algorithm.getHash() == 8) {
            return this.f15015a.o(this.f15018d, null, digitallySigned.getSignature(), this.f15016b);
        }
        throw new IllegalStateException("Invalid algorithm: " + algorithm);
    }

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public boolean verifyRawSignature(DigitallySigned digitallySigned, byte[] bArr) {
        throw new UnsupportedOperationException();
    }
}
