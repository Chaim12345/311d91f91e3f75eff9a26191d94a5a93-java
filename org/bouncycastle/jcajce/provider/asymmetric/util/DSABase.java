package org.bouncycastle.jcajce.provider.asymmetric.util;

import java.math.BigInteger;
import java.security.SignatureException;
import java.security.SignatureSpi;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.crypto.DSAExt;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.signers.DSAEncoding;
/* loaded from: classes3.dex */
public abstract class DSABase extends SignatureSpi implements PKCSObjectIdentifiers, X509ObjectIdentifiers {

    /* renamed from: a  reason: collision with root package name */
    protected Digest f13717a;

    /* renamed from: b  reason: collision with root package name */
    protected DSAExt f13718b;

    /* renamed from: c  reason: collision with root package name */
    protected DSAEncoding f13719c;

    /* JADX INFO: Access modifiers changed from: protected */
    public DSABase(Digest digest, DSAExt dSAExt, DSAEncoding dSAEncoding) {
        this.f13717a = digest;
        this.f13718b = dSAExt;
        this.f13719c = dSAEncoding;
    }

    @Override // java.security.SignatureSpi
    protected Object engineGetParameter(String str) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    @Override // java.security.SignatureSpi
    protected void engineSetParameter(String str, Object obj) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    @Override // java.security.SignatureSpi
    protected void engineSetParameter(AlgorithmParameterSpec algorithmParameterSpec) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    @Override // java.security.SignatureSpi
    protected byte[] engineSign() {
        byte[] bArr = new byte[this.f13717a.getDigestSize()];
        this.f13717a.doFinal(bArr, 0);
        try {
            BigInteger[] generateSignature = this.f13718b.generateSignature(bArr);
            return this.f13719c.encode(this.f13718b.getOrder(), generateSignature[0], generateSignature[1]);
        } catch (Exception e2) {
            throw new SignatureException(e2.toString());
        }
    }

    @Override // java.security.SignatureSpi
    protected void engineUpdate(byte b2) {
        this.f13717a.update(b2);
    }

    @Override // java.security.SignatureSpi
    protected void engineUpdate(byte[] bArr, int i2, int i3) {
        this.f13717a.update(bArr, i2, i3);
    }

    @Override // java.security.SignatureSpi
    protected boolean engineVerify(byte[] bArr) {
        byte[] bArr2 = new byte[this.f13717a.getDigestSize()];
        this.f13717a.doFinal(bArr2, 0);
        try {
            BigInteger[] decode = this.f13719c.decode(this.f13718b.getOrder(), bArr);
            return this.f13718b.verifySignature(bArr2, decode[0], decode[1]);
        } catch (Exception unused) {
            throw new SignatureException("error decoding signature bytes.");
        }
    }
}
