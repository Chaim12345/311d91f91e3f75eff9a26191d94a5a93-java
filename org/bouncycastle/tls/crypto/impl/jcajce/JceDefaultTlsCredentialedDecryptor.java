package org.bouncycastle.tls.crypto.impl.jcajce;

import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import javax.crypto.Cipher;
import org.bouncycastle.tls.Certificate;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.TlsCredentialedDecryptor;
import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class JceDefaultTlsCredentialedDecryptor implements TlsCredentialedDecryptor {

    /* renamed from: a  reason: collision with root package name */
    protected JcaTlsCrypto f15024a;

    /* renamed from: b  reason: collision with root package name */
    protected Certificate f15025b;

    /* renamed from: c  reason: collision with root package name */
    protected PrivateKey f15026c;

    public JceDefaultTlsCredentialedDecryptor(JcaTlsCrypto jcaTlsCrypto, Certificate certificate, PrivateKey privateKey) {
        if (jcaTlsCrypto == null) {
            throw new IllegalArgumentException("'crypto' cannot be null");
        }
        if (certificate == null) {
            throw new IllegalArgumentException("'certificate' cannot be null");
        }
        if (certificate.isEmpty()) {
            throw new IllegalArgumentException("'certificate' cannot be empty");
        }
        if (privateKey == null) {
            throw new IllegalArgumentException("'privateKey' cannot be null");
        }
        if ((privateKey instanceof RSAPrivateKey) || "RSA".equals(privateKey.getAlgorithm())) {
            this.f15024a = jcaTlsCrypto;
            this.f15025b = certificate;
            this.f15026c = privateKey;
            return;
        }
        throw new IllegalArgumentException("'privateKey' type not supported: " + privateKey.getClass().getName());
    }

    protected TlsSecret a(TlsCryptoParameters tlsCryptoParameters, PrivateKey privateKey, byte[] bArr) {
        SecureRandom secureRandom = this.f15024a.getSecureRandom();
        ProtocolVersion rSAPreMasterSecretVersion = tlsCryptoParameters.getRSAPreMasterSecretVersion();
        byte[] bArr2 = new byte[48];
        secureRandom.nextBytes(bArr2);
        byte[] clone = Arrays.clone(bArr2);
        try {
            Cipher l2 = this.f15024a.l();
            l2.init(2, privateKey, secureRandom);
            byte[] doFinal = l2.doFinal(bArr);
            if (doFinal != null) {
                if (doFinal.length == 48) {
                    clone = doFinal;
                }
            }
        } catch (Exception unused) {
        }
        int minorVersion = (((rSAPreMasterSecretVersion.getMinorVersion() ^ (clone[1] & 255)) | (rSAPreMasterSecretVersion.getMajorVersion() ^ (clone[0] & 255))) - 1) >> 31;
        for (int i2 = 0; i2 < 48; i2++) {
            clone[i2] = (byte) ((clone[i2] & minorVersion) | (bArr2[i2] & (~minorVersion)));
        }
        return this.f15024a.createSecret(clone);
    }

    @Override // org.bouncycastle.tls.TlsCredentialedDecryptor
    public TlsSecret decrypt(TlsCryptoParameters tlsCryptoParameters, byte[] bArr) {
        return a(tlsCryptoParameters, this.f15026c, bArr);
    }

    @Override // org.bouncycastle.tls.TlsCredentials
    public Certificate getCertificate() {
        return this.f15025b;
    }
}
