package org.bouncycastle.tls.crypto.impl.jcajce;

import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Objects;
import org.bouncycastle.tls.DigitallySigned;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.crypto.TlsStreamVerifier;
import org.bouncycastle.tls.crypto.TlsVerifier;
/* loaded from: classes4.dex */
public abstract class JcaTlsDSSVerifier implements TlsVerifier {

    /* renamed from: a  reason: collision with root package name */
    protected final JcaTlsCrypto f15007a;

    /* renamed from: b  reason: collision with root package name */
    protected final PublicKey f15008b;

    /* renamed from: c  reason: collision with root package name */
    protected final short f15009c;

    /* renamed from: d  reason: collision with root package name */
    protected final String f15010d;

    /* JADX INFO: Access modifiers changed from: protected */
    public JcaTlsDSSVerifier(JcaTlsCrypto jcaTlsCrypto, PublicKey publicKey, short s2, String str) {
        Objects.requireNonNull(jcaTlsCrypto, "crypto");
        Objects.requireNonNull(publicKey, "publicKey");
        this.f15007a = jcaTlsCrypto;
        this.f15008b = publicKey;
        this.f15009c = s2;
        this.f15010d = str;
    }

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public TlsStreamVerifier getStreamVerifier(DigitallySigned digitallySigned) {
        return null;
    }

    @Override // org.bouncycastle.tls.crypto.TlsVerifier
    public boolean verifyRawSignature(DigitallySigned digitallySigned, byte[] bArr) {
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        if (algorithm != null && algorithm.getSignature() != this.f15009c) {
            throw new IllegalStateException("Invalid algorithm: " + algorithm);
        }
        try {
            Signature createSignature = this.f15007a.getHelper().createSignature(this.f15010d);
            createSignature.initVerify(this.f15008b);
            if (algorithm == null) {
                createSignature.update(bArr, 16, 20);
            } else {
                createSignature.update(bArr, 0, bArr.length);
            }
            return createSignature.verify(digitallySigned.getSignature());
        } catch (GeneralSecurityException e2) {
            throw Exceptions.b("unable to process signature: " + e2.getMessage(), e2);
        }
    }
}
