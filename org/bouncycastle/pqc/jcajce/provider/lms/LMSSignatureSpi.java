package org.bouncycastle.pqc.jcajce.provider.lms;

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.NullDigest;
import org.bouncycastle.pqc.crypto.ExhaustedPrivateKeyException;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.pqc.crypto.lms.LMSContext;
import org.bouncycastle.pqc.crypto.lms.LMSContextBasedSigner;
import org.bouncycastle.pqc.crypto.lms.LMSContextBasedVerifier;
/* loaded from: classes4.dex */
public class LMSSignatureSpi extends Signature {
    private Digest digest;
    private LMSContextBasedSigner lmOtsSigner;
    private LMSContextBasedVerifier lmOtsVerifier;
    private SecureRandom random;
    private MessageSigner signer;

    /* loaded from: classes4.dex */
    public static class generic extends LMSSignatureSpi {
        public generic() {
            super("LMS", new NullDigest());
        }
    }

    protected LMSSignatureSpi(String str, Digest digest) {
        super(str);
        this.digest = digest;
    }

    private Digest getSigner() {
        try {
            return this.lmOtsSigner.generateLMSContext();
        } catch (ExhaustedPrivateKeyException e2) {
            throw new SignatureException(e2.getMessage(), e2);
        }
    }

    @Override // java.security.SignatureSpi
    protected Object engineGetParameter(String str) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    @Override // java.security.SignatureSpi
    protected void engineInitSign(PrivateKey privateKey) {
        if (!(privateKey instanceof BCLMSPrivateKey)) {
            throw new InvalidKeyException("unknown private key passed to LMS");
        }
        LMSContextBasedSigner lMSContextBasedSigner = (LMSContextBasedSigner) ((BCLMSPrivateKey) privateKey).a();
        this.lmOtsSigner = lMSContextBasedSigner;
        if (lMSContextBasedSigner.getUsagesRemaining() == 0) {
            throw new InvalidKeyException("private key exhausted");
        }
        this.digest = null;
    }

    @Override // java.security.SignatureSpi
    protected void engineInitSign(PrivateKey privateKey, SecureRandom secureRandom) {
        this.random = secureRandom;
        engineInitSign(privateKey);
    }

    @Override // java.security.SignatureSpi
    protected void engineInitVerify(PublicKey publicKey) {
        if (!(publicKey instanceof BCLMSPublicKey)) {
            throw new InvalidKeyException("unknown public key passed to XMSS");
        }
        NullDigest nullDigest = new NullDigest();
        this.digest = nullDigest;
        nullDigest.reset();
        this.lmOtsVerifier = (LMSContextBasedVerifier) ((BCLMSPublicKey) publicKey).a();
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
        if (this.digest == null) {
            this.digest = getSigner();
        }
        try {
            byte[] generateSignature = this.lmOtsSigner.generateSignature((LMSContext) this.digest);
            this.digest = null;
            return generateSignature;
        } catch (Exception e2) {
            if (e2 instanceof IllegalStateException) {
                throw new SignatureException(e2.getMessage(), e2);
            }
            throw new SignatureException(e2.toString(), e2);
        }
    }

    @Override // java.security.SignatureSpi
    protected void engineUpdate(byte b2) {
        if (this.digest == null) {
            this.digest = getSigner();
        }
        this.digest.update(b2);
    }

    @Override // java.security.SignatureSpi
    protected void engineUpdate(byte[] bArr, int i2, int i3) {
        if (this.digest == null) {
            this.digest = getSigner();
        }
        this.digest.update(bArr, i2, i3);
    }

    @Override // java.security.SignatureSpi
    protected boolean engineVerify(byte[] bArr) {
        LMSContext generateLMSContext = this.lmOtsVerifier.generateLMSContext(bArr);
        byte[] digestResult = DigestUtil.getDigestResult(this.digest);
        generateLMSContext.update(digestResult, 0, digestResult.length);
        return this.lmOtsVerifier.verify(generateLMSContext);
    }
}
