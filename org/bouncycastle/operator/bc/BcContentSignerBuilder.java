package org.bouncycastle.operator.bc;

import java.io.OutputStream;
import java.security.SecureRandom;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.RuntimeOperatorException;
/* loaded from: classes4.dex */
public abstract class BcContentSignerBuilder {

    /* renamed from: a  reason: collision with root package name */
    protected BcDigestProvider f14417a = BcDefaultDigestProvider.INSTANCE;
    private AlgorithmIdentifier digAlgId;
    private SecureRandom random;
    private AlgorithmIdentifier sigAlgId;

    public BcContentSignerBuilder(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2) {
        this.sigAlgId = algorithmIdentifier;
        this.digAlgId = algorithmIdentifier2;
    }

    protected abstract Signer b(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2);

    public ContentSigner build(AsymmetricKeyParameter asymmetricKeyParameter) {
        Signer b2 = b(this.sigAlgId, this.digAlgId);
        SecureRandom secureRandom = this.random;
        if (secureRandom != null) {
            b2.init(true, new ParametersWithRandom(asymmetricKeyParameter, secureRandom));
        } else {
            b2.init(true, asymmetricKeyParameter);
        }
        return new ContentSigner(b2) { // from class: org.bouncycastle.operator.bc.BcContentSignerBuilder.1

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ Signer f14418a;
            private BcSignerOutputStream stream;

            {
                this.f14418a = b2;
                this.stream = new BcSignerOutputStream(b2);
            }

            @Override // org.bouncycastle.operator.ContentSigner
            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return BcContentSignerBuilder.this.sigAlgId;
            }

            @Override // org.bouncycastle.operator.ContentSigner
            public OutputStream getOutputStream() {
                return this.stream;
            }

            @Override // org.bouncycastle.operator.ContentSigner
            public byte[] getSignature() {
                try {
                    return this.stream.a();
                } catch (CryptoException e2) {
                    throw new RuntimeOperatorException("exception obtaining signature: " + e2.getMessage(), e2);
                }
            }
        };
    }

    public BcContentSignerBuilder setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }
}
