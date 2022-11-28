package org.bouncycastle.pqc.jcajce.provider.mceliece;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.pqc.crypto.mceliece.McElieceCCA2KeyParameters;
import org.bouncycastle.pqc.crypto.mceliece.McElieceFujisakiCipher;
import org.bouncycastle.pqc.jcajce.provider.util.AsymmetricHybridCipher;
/* loaded from: classes4.dex */
public class McElieceFujisakiCipherSpi extends AsymmetricHybridCipher implements PKCSObjectIdentifiers, X509ObjectIdentifiers {
    private ByteArrayOutputStream buf = new ByteArrayOutputStream();
    private McElieceFujisakiCipher cipher;
    private Digest digest;

    /* loaded from: classes4.dex */
    public static class McElieceFujisaki extends McElieceFujisakiCipherSpi {
        public McElieceFujisaki() {
            super(DigestFactory.createSHA1(), new McElieceFujisakiCipher());
        }
    }

    protected McElieceFujisakiCipherSpi(Digest digest, McElieceFujisakiCipher mcElieceFujisakiCipher) {
        this.digest = digest;
        this.cipher = mcElieceFujisakiCipher;
    }

    @Override // org.bouncycastle.pqc.jcajce.provider.util.AsymmetricHybridCipher
    protected int c(int i2) {
        return 0;
    }

    @Override // org.bouncycastle.pqc.jcajce.provider.util.AsymmetricHybridCipher
    protected int d(int i2) {
        return 0;
    }

    @Override // org.bouncycastle.pqc.jcajce.provider.util.AsymmetricHybridCipher, org.bouncycastle.pqc.jcajce.provider.util.CipherSpiExt
    public byte[] doFinal(byte[] bArr, int i2, int i3) {
        update(bArr, i2, i3);
        byte[] byteArray = this.buf.toByteArray();
        this.buf.reset();
        int i4 = this.f14637a;
        if (i4 == 1) {
            return this.cipher.messageEncrypt(byteArray);
        }
        if (i4 == 2) {
            try {
                return this.cipher.messageDecrypt(byteArray);
            } catch (InvalidCipherTextException e2) {
                throw new BadPaddingException(e2.getMessage());
            }
        }
        throw new IllegalStateException("unknown mode in doFinal");
    }

    @Override // org.bouncycastle.pqc.jcajce.provider.util.AsymmetricHybridCipher
    protected void e(Key key, AlgorithmParameterSpec algorithmParameterSpec) {
        AsymmetricKeyParameter generatePrivateKeyParameter = McElieceCCA2KeysToParams.generatePrivateKeyParameter((PrivateKey) key);
        this.digest.reset();
        this.cipher.init(false, generatePrivateKeyParameter);
    }

    @Override // org.bouncycastle.pqc.jcajce.provider.util.AsymmetricHybridCipher
    protected void f(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        ParametersWithRandom parametersWithRandom = new ParametersWithRandom(McElieceCCA2KeysToParams.generatePublicKeyParameter((PublicKey) key), secureRandom);
        this.digest.reset();
        this.cipher.init(true, parametersWithRandom);
    }

    @Override // org.bouncycastle.pqc.jcajce.provider.util.CipherSpiExt
    public int getKeySize(Key key) {
        return this.cipher.getKeySize((McElieceCCA2KeyParameters) (key instanceof PublicKey ? McElieceCCA2KeysToParams.generatePublicKeyParameter((PublicKey) key) : McElieceCCA2KeysToParams.generatePrivateKeyParameter((PrivateKey) key)));
    }

    @Override // org.bouncycastle.pqc.jcajce.provider.util.CipherSpiExt
    public String getName() {
        return "McElieceFujisakiCipher";
    }

    @Override // org.bouncycastle.pqc.jcajce.provider.util.AsymmetricHybridCipher, org.bouncycastle.pqc.jcajce.provider.util.CipherSpiExt
    public byte[] update(byte[] bArr, int i2, int i3) {
        this.buf.write(bArr, i2, i3);
        return new byte[0];
    }
}
