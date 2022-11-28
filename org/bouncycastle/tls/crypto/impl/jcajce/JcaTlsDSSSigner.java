package org.bouncycastle.tls.crypto.impl.jcajce;

import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Objects;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.crypto.TlsSigner;
import org.bouncycastle.tls.crypto.TlsStreamSigner;
/* loaded from: classes4.dex */
public abstract class JcaTlsDSSSigner implements TlsSigner {

    /* renamed from: a  reason: collision with root package name */
    protected final JcaTlsCrypto f15003a;

    /* renamed from: b  reason: collision with root package name */
    protected final PrivateKey f15004b;

    /* renamed from: c  reason: collision with root package name */
    protected final short f15005c;

    /* renamed from: d  reason: collision with root package name */
    protected final String f15006d;

    /* JADX INFO: Access modifiers changed from: protected */
    public JcaTlsDSSSigner(JcaTlsCrypto jcaTlsCrypto, PrivateKey privateKey, short s2, String str) {
        Objects.requireNonNull(jcaTlsCrypto, "crypto");
        Objects.requireNonNull(privateKey, "privateKey");
        this.f15003a = jcaTlsCrypto;
        this.f15004b = privateKey;
        this.f15005c = s2;
        this.f15006d = str;
    }

    @Override // org.bouncycastle.tls.crypto.TlsSigner
    public byte[] generateRawSignature(SignatureAndHashAlgorithm signatureAndHashAlgorithm, byte[] bArr) {
        if (signatureAndHashAlgorithm != null && signatureAndHashAlgorithm.getSignature() != this.f15005c) {
            throw new IllegalStateException("Invalid algorithm: " + signatureAndHashAlgorithm);
        }
        try {
            Signature createSignature = this.f15003a.getHelper().createSignature(this.f15006d);
            createSignature.initSign(this.f15004b, this.f15003a.getSecureRandom());
            if (signatureAndHashAlgorithm == null) {
                createSignature.update(bArr, 16, 20);
            } else {
                createSignature.update(bArr, 0, bArr.length);
            }
            return createSignature.sign();
        } catch (GeneralSecurityException e2) {
            throw new TlsFatalAlert((short) 80, (Throwable) e2);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsSigner
    public TlsStreamSigner getStreamSigner(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        return null;
    }
}
