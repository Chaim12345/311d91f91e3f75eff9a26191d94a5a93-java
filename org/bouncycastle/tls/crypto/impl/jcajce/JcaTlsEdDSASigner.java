package org.bouncycastle.tls.crypto.impl.jcajce;

import java.security.PrivateKey;
import java.util.Objects;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.crypto.TlsSigner;
import org.bouncycastle.tls.crypto.TlsStreamSigner;
/* loaded from: classes4.dex */
public abstract class JcaTlsEdDSASigner implements TlsSigner {

    /* renamed from: a  reason: collision with root package name */
    protected final JcaTlsCrypto f15011a;

    /* renamed from: b  reason: collision with root package name */
    protected final PrivateKey f15012b;

    /* renamed from: c  reason: collision with root package name */
    protected final short f15013c;

    /* renamed from: d  reason: collision with root package name */
    protected final String f15014d;

    public JcaTlsEdDSASigner(JcaTlsCrypto jcaTlsCrypto, PrivateKey privateKey, short s2, String str) {
        Objects.requireNonNull(jcaTlsCrypto, "crypto");
        Objects.requireNonNull(privateKey, "privateKey");
        this.f15011a = jcaTlsCrypto;
        this.f15012b = privateKey;
        this.f15013c = s2;
        this.f15014d = str;
    }

    @Override // org.bouncycastle.tls.crypto.TlsSigner
    public byte[] generateRawSignature(SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] bArr) {
        throw new UnsupportedOperationException();
    }

    @Override // org.bouncycastle.tls.crypto.TlsSigner
    public TlsStreamSigner getStreamSigner(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        if (signatureAndHashAlgorithm != null && signatureAndHashAlgorithm.getSignature() == this.f15013c && signatureAndHashAlgorithm.getHash() == 8) {
            return this.f15011a.m(this.f15014d, null, this.f15012b, false);
        }
        throw new IllegalStateException("Invalid algorithm: " + signatureAndHashAlgorithm);
    }
}
