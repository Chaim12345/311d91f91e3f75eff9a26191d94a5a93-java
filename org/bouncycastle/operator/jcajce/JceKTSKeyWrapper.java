package org.bouncycastle.operator.jcajce;

import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import javax.crypto.Cipher;
import org.bouncycastle.asn1.cms.GenericHybridParameters;
import org.bouncycastle.asn1.cms.RsaKemParameters;
import org.bouncycastle.asn1.iso.ISOIECObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.crypto.util.DEROtherInfo;
import org.bouncycastle.jcajce.spec.KTSParameterSpec;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.AsymmetricKeyWrapper;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class JceKTSKeyWrapper extends AsymmetricKeyWrapper {
    private OperatorHelper helper;
    private final int keySizeInBits;
    private final byte[] partyUInfo;
    private final byte[] partyVInfo;
    private PublicKey publicKey;
    private SecureRandom random;
    private final String symmetricWrappingAlg;

    public JceKTSKeyWrapper(PublicKey publicKey, String str, int i2, byte[] bArr, byte[] bArr2) {
        super(new AlgorithmIdentifier(PKCSObjectIdentifiers.id_rsa_KEM, new GenericHybridParameters(new AlgorithmIdentifier(ISOIECObjectIdentifiers.id_kem_rsa, new RsaKemParameters(new AlgorithmIdentifier(X9ObjectIdentifiers.id_kdf_kdf3, new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256)), (i2 + 7) / 8)), JceSymmetricKeyWrapper.a(str, i2))));
        this.helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.publicKey = publicKey;
        this.symmetricWrappingAlg = str;
        this.keySizeInBits = i2;
        this.partyUInfo = Arrays.clone(bArr);
        this.partyVInfo = Arrays.clone(bArr2);
    }

    public JceKTSKeyWrapper(X509Certificate x509Certificate, String str, int i2, byte[] bArr, byte[] bArr2) {
        this(x509Certificate.getPublicKey(), str, i2, bArr, bArr2);
    }

    @Override // org.bouncycastle.operator.KeyWrapper
    public byte[] generateWrappedKey(GenericKey genericKey) {
        Cipher b2 = this.helper.b(getAlgorithmIdentifier().getAlgorithm(), new HashMap());
        try {
            b2.init(3, this.publicKey, new KTSParameterSpec.Builder(this.symmetricWrappingAlg, this.keySizeInBits, new DEROtherInfo.Builder(JceSymmetricKeyWrapper.a(this.symmetricWrappingAlg, this.keySizeInBits), this.partyUInfo, this.partyVInfo).build().getEncoded()).build(), this.random);
            return b2.wrap(OperatorUtils.a(genericKey));
        } catch (Exception e2) {
            throw new OperatorException("Unable to wrap contents key: " + e2.getMessage(), e2);
        }
    }

    public JceKTSKeyWrapper setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }

    public JceKTSKeyWrapper setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceKTSKeyWrapper setSecureRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
        return this;
    }
}
