package org.bouncycastle.jcajce.provider.asymmetric.rsa;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.SignatureSpi;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.RIPEMD128Digest;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.WhirlpoolDigest;
import org.bouncycastle.crypto.engines.RSABlindedEngine;
import org.bouncycastle.crypto.signers.X931Signer;
import org.bouncycastle.crypto.util.DigestFactory;
/* loaded from: classes3.dex */
public class X931SignatureSpi extends SignatureSpi {
    private X931Signer signer;

    /* loaded from: classes3.dex */
    public static class RIPEMD128WithRSAEncryption extends X931SignatureSpi {
        public RIPEMD128WithRSAEncryption() {
            super(new RIPEMD128Digest(), new RSABlindedEngine());
        }
    }

    /* loaded from: classes3.dex */
    public static class RIPEMD160WithRSAEncryption extends X931SignatureSpi {
        public RIPEMD160WithRSAEncryption() {
            super(new RIPEMD160Digest(), new RSABlindedEngine());
        }
    }

    /* loaded from: classes3.dex */
    public static class SHA1WithRSAEncryption extends X931SignatureSpi {
        public SHA1WithRSAEncryption() {
            super(DigestFactory.createSHA1(), new RSABlindedEngine());
        }
    }

    /* loaded from: classes3.dex */
    public static class SHA224WithRSAEncryption extends X931SignatureSpi {
        public SHA224WithRSAEncryption() {
            super(DigestFactory.createSHA224(), new RSABlindedEngine());
        }
    }

    /* loaded from: classes3.dex */
    public static class SHA256WithRSAEncryption extends X931SignatureSpi {
        public SHA256WithRSAEncryption() {
            super(DigestFactory.createSHA256(), new RSABlindedEngine());
        }
    }

    /* loaded from: classes3.dex */
    public static class SHA384WithRSAEncryption extends X931SignatureSpi {
        public SHA384WithRSAEncryption() {
            super(DigestFactory.createSHA384(), new RSABlindedEngine());
        }
    }

    /* loaded from: classes3.dex */
    public static class SHA512WithRSAEncryption extends X931SignatureSpi {
        public SHA512WithRSAEncryption() {
            super(DigestFactory.createSHA512(), new RSABlindedEngine());
        }
    }

    /* loaded from: classes3.dex */
    public static class SHA512_224WithRSAEncryption extends X931SignatureSpi {
        public SHA512_224WithRSAEncryption() {
            super(DigestFactory.createSHA512_224(), new RSABlindedEngine());
        }
    }

    /* loaded from: classes3.dex */
    public static class SHA512_256WithRSAEncryption extends X931SignatureSpi {
        public SHA512_256WithRSAEncryption() {
            super(DigestFactory.createSHA512_256(), new RSABlindedEngine());
        }
    }

    /* loaded from: classes3.dex */
    public static class WhirlpoolWithRSAEncryption extends X931SignatureSpi {
        public WhirlpoolWithRSAEncryption() {
            super(new WhirlpoolDigest(), new RSABlindedEngine());
        }
    }

    protected X931SignatureSpi(Digest digest, AsymmetricBlockCipher asymmetricBlockCipher) {
        this.signer = new X931Signer(asymmetricBlockCipher, digest);
    }

    @Override // java.security.SignatureSpi
    protected Object engineGetParameter(String str) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    @Override // java.security.SignatureSpi
    protected void engineInitSign(PrivateKey privateKey) {
        this.signer.init(true, RSAUtil.c((RSAPrivateKey) privateKey));
    }

    @Override // java.security.SignatureSpi
    protected void engineInitVerify(PublicKey publicKey) {
        this.signer.init(false, RSAUtil.d((RSAPublicKey) publicKey));
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
        try {
            return this.signer.generateSignature();
        } catch (Exception e2) {
            throw new SignatureException(e2.toString());
        }
    }

    @Override // java.security.SignatureSpi
    protected void engineUpdate(byte b2) {
        this.signer.update(b2);
    }

    @Override // java.security.SignatureSpi
    protected void engineUpdate(byte[] bArr, int i2, int i3) {
        this.signer.update(bArr, i2, i3);
    }

    @Override // java.security.SignatureSpi
    protected boolean engineVerify(byte[] bArr) {
        return this.signer.verifySignature(bArr);
    }
}
